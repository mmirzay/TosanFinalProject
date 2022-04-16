package ir.tosan.projects.modules.dmm.backend.repositories;

import ir.tosan.projects.modules.cmm.backend.entities.Customer;
import ir.tosan.projects.modules.dmm.backend.entities.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    Optional<Deposit> findByNumber(long number);
    List<Deposit> findAllByOwner(Customer owner);
}
