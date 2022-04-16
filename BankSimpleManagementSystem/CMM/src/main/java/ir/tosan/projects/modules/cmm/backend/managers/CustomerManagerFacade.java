package ir.tosan.projects.modules.cmm.backend.managers;

import com.example.tosansimplerest.backend.exceptions.CustomerException;
import com.example.tosansimplerest.backend.exceptions.ManagerException;
import ir.tosan.projects.modules.cmm.backend.api.CustomerManagerInterface;
import ir.tosan.projects.modules.cmm.backend.api.dto.in.CustomerAddParam;
import ir.tosan.projects.modules.cmm.backend.api.dto.in.CustomerChangeStatusParam;
import ir.tosan.projects.modules.cmm.backend.api.dto.out.*;
import ir.tosan.projects.modules.cmm.backend.entities.Customer;
import ir.tosan.projects.modules.cmm.backend.entities.CustomerStatus;
import ir.tosan.projects.modules.cmm.backend.services.CustomerService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class CustomerManagerFacade implements CustomerManagerInterface {

    private final CustomerService customerService;

    @Override
    public CustomerAddResult addCustomer(CustomerAddParam addParam) {
        try {
            Customer toRegister = createCustomer(addParam);
            Customer registered = customerService.addCustomer(toRegister);
            return new CustomerAddResult(registered.getCode());
        } catch (CustomerException e) {
            throw new ManagerException(e);
        }
    }

    @Override
    public CustomerGetResult getCustomer(Long code) {
        try {
            Customer customer = customerService.getCustomerByCode(code);
            return CustomerGetResult.builder()
                    .fullName(customer.getFullName())
                    .nationalId(customer.getNationalId())
                    .build();
        } catch (CustomerException e) {
            throw new ManagerException(e);
        }
    }

    @Override
    public CustomerDeleteResult deleteCustomer(Long code) {
        try {
            Customer customer = customerService.getCustomerByCode(code);
            customerService.deleteCustomerByCode(customer.getCode());
            return new CustomerDeleteResult(code, customer.getFullName());
        } catch (CustomerException e) {
            throw new ManagerException(e);
        }
    }

    @Override
    public CustomerUpdateResult changeStatus(Long code, CustomerChangeStatusParam param) {
        try {
            Customer customer = customerService.getCustomerByCode(code);
            CustomerStatus status = CustomerStatus.valueOf(param.getStatusName());
            customer.setStatus(status);
            String message = customerService.updateCustomer(customer) ? "updated successfully." : "updating failed!";
            return new CustomerUpdateResult(code, message);
        } catch (IllegalArgumentException | CustomerException e) {
            throw new ManagerException(e);
        }
    }


    @Override
    public CustomersList getAllCustomersList() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            if (customers.isEmpty())
                throw new CustomerException("customer list is empty.");
            return new CustomersList(customers);
        } catch (CustomerException e) {
            throw new ManagerException(e);
        }
    }

    private Customer createCustomer(CustomerAddParam addParam) {
        String fullName = addParam.getFirstName() + " " + addParam.getLastName();
        return Customer.of(fullName, addParam.getNationalId());
    }
}
