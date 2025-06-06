package br.com.kumabe.domain.repository;


import br.com.kumabe.domain.entity.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    void deleteById(Long id);
    boolean existsByEmail(String email);
    Optional<Customer> findByEmail(String email);
}
