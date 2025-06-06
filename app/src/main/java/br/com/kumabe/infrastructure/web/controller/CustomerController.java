package br.com.kumabe.infrastructure.web.controller;

import br.com.kumabe.domain.entity.Customer;
import br.com.kumabe.domain.exception.CustomerNotFoundException;
import br.com.kumabe.domain.usecase.*;
import br.com.kumabe.infrastructure.web.dto.CreateCustomerRequest;
import br.com.kumabe.infrastructure.web.dto.CustomerDto;
import br.com.kumabe.infrastructure.web.mapper.CustomerApiMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final GetAllCustomersUseCase getAllCustomersUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final CustomerApiMapper customerApiMapper;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        Customer customerToCreate = customerApiMapper.toCustomer(createCustomerRequest);
        Customer createdCustomer = createCustomerUseCase.createCustomer(customerToCreate);
        CustomerDto customerDto = customerApiMapper.toCustomerDto(createdCustomer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdCustomer.getId()).toUri();
        return ResponseEntity.created(location).body(customerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return getCustomerByIdUseCase.getCustomerById(id)
                .map(customerApiMapper::toCustomerDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<Customer> customers = getAllCustomersUseCase.getAllCustomers();
        return ResponseEntity.ok(customerApiMapper.toCustomerDtoList(customers));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto) {
        Customer customerToUpdate = customerApiMapper.toCustomer(customerDto);
        return updateCustomerUseCase.updateCustomer(id, customerToUpdate)
                .map(customerApiMapper::toCustomerDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id + " for update."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (!deleteCustomerUseCase.deleteCustomer(id)) {
            throw new CustomerNotFoundException("Customer not found with id: " + id + " for deletion.");
        }
        return ResponseEntity.noContent().build();
    }
}