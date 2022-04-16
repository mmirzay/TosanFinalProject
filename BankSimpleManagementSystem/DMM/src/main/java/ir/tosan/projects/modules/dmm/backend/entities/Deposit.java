package ir.tosan.projects.modules.dmm.backend.entities;

import ir.tosan.projects.modules.cmm.backend.entities.Customer;
import ir.tosan.projects.modules.common.utilities.DepositNumberGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private long number;
    private DepositType type;
    private DepositStatus status;
    private DepositCurrency currency;
    private double balance;
    private Date openDate;
    private Date closeDate;
    @ManyToOne(optional = false)
    private Customer owner;

    private Deposit(long number, DepositType type, DepositCurrency currency, double balance, Date closeDate, Customer owner) {
        this.number = number;
        this.status = DepositStatus.OPENED;
        this.type = type;
        this.currency = currency;
        this.balance = balance;
        this.openDate = new Date();
        this.closeDate = closeDate;
        this.owner = owner;
    }

    public static Deposit of(Customer owner, double balance, DepositType type, DepositCurrency currency, Date closeDate) {
        long number = DepositNumberGenerator.getRandomAccountNumber();
        return new Deposit(number, type, currency, balance, closeDate, owner);
    }

    public String getTitle() {
        return owner.getFullName() + " " + type.name();
    }
}
