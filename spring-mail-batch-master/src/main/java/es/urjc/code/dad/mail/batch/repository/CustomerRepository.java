package es.urjc.code.dad.mail.batch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.urjc.code.dad.mail.batch.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Optional<Customer> findById(int customerId);

}
