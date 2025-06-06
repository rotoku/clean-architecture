package br.com.kumabe.infrastructure.web.mapper;

import br.com.kumabe.domain.entity.Customer;
import br.com.kumabe.infrastructure.web.dto.CreateCustomerRequest;
import br.com.kumabe.infrastructure.web.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerApiMapper {

    CustomerApiMapper INSTANCE = Mappers.getMapper(CustomerApiMapper.class);

    CustomerDto toCustomerDto(Customer customer);
    List<CustomerDto> toCustomerDtoList(List<Customer> customers);
    @Mapping(target = "id", ignore = true)
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);
    Customer toCustomer(CustomerDto customerDto); // For updates
}