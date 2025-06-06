package br.com.kumabe.domain.usecase;

import br.com.kumabe.domain.entity.Customer;

public interface CreateCustomerUseCase {
    Customer createCustomer(Customer customer);
}
