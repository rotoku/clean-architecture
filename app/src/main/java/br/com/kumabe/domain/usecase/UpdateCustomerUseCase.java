package br.com.kumabe.domain.usecase;

import br.com.kumabe.domain.entity.Customer;

import java.util.Optional;

public interface UpdateCustomerUseCase {
    Optional<Customer> updateCustomer(Long id, Customer customer);
}