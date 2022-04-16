package ir.tosan.projects.modules.cmm.backend.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String fullName;
    @Column(nullable = false, unique = true)
    private Long nationalId;
    private Date birthdate;
    private CustomerType type;
    private String phone;
    private CustomerStatus status;
    private Date created;


    private Customer(String fullName, Long nationalId, Date birthdate, CustomerType type, String phone) {
        this.fullName = fullName;
        this.nationalId = nationalId;
        this.birthdate = birthdate;
        this.type = type;
        this.phone = phone;
        this.status = CustomerStatus.ACTIVE;
        this.created = new Date();

    }

    public static Customer of(String fullName, Long nationalId) {
        return of(fullName, nationalId, new Date(), CustomerType.REAL, "09");
    }

    public static Customer of(String fullName, Long nationalId, Date birthdate, CustomerType type, String phone) {
        return new Customer(fullName, nationalId, birthdate, type, phone);
    }
}
