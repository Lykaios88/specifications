package com.specification.specifications.api.controller;

import com.specification.specifications.entity.Customer;
import com.specification.specifications.repository.CustomerRepository;
import com.specification.specifications.repository.CustomerWithFirstName;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepo;

    @GetMapping
    public Page<Customer> findCustomersByFirstName(@Nullable @RequestParam("firstName") String firstName, Pageable pageable) {

        if (firstName == null) {
            return customerRepo.findAll(pageable);
        } else {
            return customerRepo.findByFirstName(firstName, pageable);
        }
    }

    @GetMapping("/specification")
    public Page<Customer> findCustomers(
            @And({
                    @Spec(path = "firstName", spec = Equal.class),
                    @Spec(path = "lastName", spec = Equal.class),
                    @Spec(path = "status", spec = Equal.class)
            }) Specification<Customer> customerSpec, Pageable pageable) {

        return customerRepo.findAll(customerSpec, pageable);
    }

    @RequestMapping("/orders")
    public Object findByOrderedItem(
            @Join(path= "orders", alias = "o") // alias specified for joined path
            @Spec(path="o.itemName", params="orderedItem", spec= Like.class) // alias used in regular spec definition
                    Specification<Customer> customersByOrderedItemSpec, Pageable pageable) {

        return customerRepo.findAll(customersByOrderedItemSpec, pageable);
    }
}