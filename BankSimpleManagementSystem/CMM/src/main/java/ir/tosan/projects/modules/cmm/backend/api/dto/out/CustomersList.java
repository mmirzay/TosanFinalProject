package ir.tosan.projects.modules.cmm.backend.api.dto.out;

import ir.tosan.projects.modules.cmm.backend.entities.Customer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CustomersList {
    private final List<Customer> customers;
}
