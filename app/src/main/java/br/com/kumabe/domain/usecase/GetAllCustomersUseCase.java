package br.com.kumabe.domain.usecase;

import br.com.kumabe.domain.entity.Customer;
import java.util.List;

public interface GetAllCustomersUseCase {
    List<Customer> getAllCustomers();
}
