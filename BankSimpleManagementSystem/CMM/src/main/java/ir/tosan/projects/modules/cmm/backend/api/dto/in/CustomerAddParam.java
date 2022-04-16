package ir.tosan.projects.modules.cmm.backend.api.dto.in;

import ir.tosan.projects.modules.cmm.backend.entities.Customer;
import ir.tosan.projects.modules.cmm.backend.entities.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddParam {
    private String firstName;
    private String lastName;
    private Long nationalId;
    private Date birthdate;
    private CustomerType type;
    private String phone;
}
