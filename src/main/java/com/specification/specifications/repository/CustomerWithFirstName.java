package com.specification.specifications.repository;

import com.specification.specifications.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWithFirstName implements Specification<Customer>{

        private String firstName;

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery query, CriteriaBuilder cb) {
        if (firstName == null) {
            return cb.isTrue(cb.literal(true));
        }
        return cb.equal(root.get("firstName"), this.firstName);
    }
}
