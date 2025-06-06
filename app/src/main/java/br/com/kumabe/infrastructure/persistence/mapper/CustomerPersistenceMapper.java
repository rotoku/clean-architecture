package br.com.kumabe.infrastructure.persistence.mapper;

import br.com.kumabe.domain.entity.Customer;
import br.com.kumabe.infrastructure.persistence.entity.CustomerJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {

    CustomerPersistenceMapper INSTANCE = Mappers.getMapper(CustomerPersistenceMapper.class);

    Customer toDomainEntity(CustomerJpaEntity jpaEntity);
    CustomerJpaEntity toJpaEntity(Customer domainEntity);
}