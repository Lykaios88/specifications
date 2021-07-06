package com.specification.specifications.repository;

import com.specification.specifications.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findByFirstName(String firstName, Pageable pageable);

    //@Query(value="SELECT first_name, last_name, status, id FROM customer", nativeQuery=true)
    Page<Customer> findAll(Specification<Customer> spec, Pageable pageable);
}
