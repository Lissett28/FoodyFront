package com.seniorproject.foody.dao;

import com.seniorproject.foody.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}