package org.example.finalexam.repositories;

import org.example.finalexam.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    int countCustomerBySeat(String seat);
    Customer findCustomerBySeat(String seat);
}
