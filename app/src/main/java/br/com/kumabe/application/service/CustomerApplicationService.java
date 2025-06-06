package br.com.kumabe.application.service;

import br.com.kumabe.domain.entity.Customer;
import br.com.kumabe.domain.exception.CustomerAlreadyExistsException;
import br.com.kumabe.domain.repository.CustomerRepository;
import br.com.kumabe.domain.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerApplicationService implements
        CreateCustomerUseCase,
        GetCustomerByIdUseCase,
        GetAllCustomersUseCase,
        UpdateCustomerUseCase,
        DeleteCustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CustomerAlreadyExistsException("Customer with email " + customer.getEmail() + " already exists.");
        }
        return customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Customer> updateCustomer(Long id, Customer customerDetails) {
        return customerRepository.findById(id)
            .map(existingCustomer -> {
                if (!existingCustomer.getEmail().equals(customerDetails.getEmail()) &&
                    customerRepository.findByEmail(customerDetails.getEmail()).filter(c -> !c.getId().equals(id)).isPresent()) {
                    throw new CustomerAlreadyExistsException("Another customer with email " + customerDetails.getEmail() + " already exists.");
                }
                existingCustomer.setFirstName(customerDetails.getFirstName());
                existingCustomer.setLastName(customerDetails.getLastName());
                existingCustomer.setEmail(customerDetails.getEmail());
                return customerRepository.save(existingCustomer);
            });
    }

    @Override
    @Transactional
    public boolean deleteCustomer(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}