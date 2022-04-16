package ir.tosan.projects.modules.cmm.backend.services;

import com.example.tosansimplerest.backend.exceptions.CustomerException;
import ir.tosan.projects.modules.cmm.backend.entities.Customer;
import ir.tosan.projects.modules.cmm.backend.repositories.CustomerRepository;
import ir.tosan.projects.modules.common.utilities.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;

    public Customer addCustomer(Customer customer) throws CustomerException {
        if (!Validator.validateNationalId(customer.getNationalId()))
            throw new CustomerException("National Id is NOT valid");

        try {
            return repository.save(customer);
        } catch (DataIntegrityViolationException e) {
            if (e.getRootCause() instanceof SQLIntegrityConstraintViolationException)
                throw new CustomerException("Duplicate national Id");
            throw new CustomerException("Some thing wrong while adding new customer", e);
        }
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }


    public Customer getCustomerByCode(Long code) throws CustomerException {
        return repository.findByCode(code).orElseThrow(() -> new CustomerException("Customer Code is not exists."));
    }

    /*
        public Customer getCustomerByNationalId(Long nationalId) throws CustomerException {
            return repository.findByNationalId(nationalId).orElseThrow(() -> new CustomerException("Customer National Id is not exists."));
        }
        */
    public boolean updateCustomer(Customer customer) {
        if (customer.getCode() == null || repository.findByCode(customer.getCode()).isPresent() == false)
            return false;
        repository.save(customer);
        return true;
    }

    public void deleteCustomerByCode(Long code) throws CustomerException {
        try {
            repository.deleteById(code);
        } catch (DataIntegrityViolationException e) {
            if (e.getRootCause() instanceof SQLIntegrityConstraintViolationException)
                throw new CustomerException("Customer has at least one deposit.");
            throw new CustomerException("Some thing wrong while deleting customer.", e);
        }
    }

}
