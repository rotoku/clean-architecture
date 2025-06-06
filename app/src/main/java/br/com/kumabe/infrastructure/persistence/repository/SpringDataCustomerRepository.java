package br.com.kumabe.infrastructure.persistence.repository;


import br.com.kumabe.infrastructure.persistence.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataCustomerRepository extends JpaRepository<CustomerJpaEntity, Long> {
    boolean existsByEmail(String email);
    Optional<CustomerJpaEntity> findByEmail(String email);
}