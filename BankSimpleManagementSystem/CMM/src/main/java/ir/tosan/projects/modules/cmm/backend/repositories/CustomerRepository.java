package ir.tosan.projects.modules.cmm.backend.repositories;


import ir.tosan.projects.modules.cmm.backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCode(Long code);

    Optional<Customer> findByNationalId(Long code);
}
