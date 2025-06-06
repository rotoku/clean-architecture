package br.com.kumabe.infrastructure.persistence.repository;

import br.com.kumabe.domain.entity.Customer;
import br.com.kumabe.domain.repository.CustomerRepository;
import br.com.kumabe.infrastructure.persistence.entity.CustomerJpaEntity;
import br.com.kumabe.infrastructure.persistence.mapper.CustomerPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final SpringDataCustomerRepository jpaRepository;
    private final CustomerPersistenceMapper mapper;

    @Override
    public Customer save(Customer customer) {
        CustomerJpaEntity customerJpaEntity = mapper.toJpaEntity(customer);
        CustomerJpaEntity savedEntity = jpaRepository.save(customerJpaEntity);
        return mapper.toDomainEntity(savedEntity);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomainEntity);
    }

    @Override
    public List<Customer> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(mapper::toDomainEntity);
    }
}