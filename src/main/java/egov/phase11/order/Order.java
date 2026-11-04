package egov.phase11.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "orders") // order는 SQL 예약어
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long memberId;

    private int paidAmount;

    protected Order() {
    }

    public Order(long memberId, int paidAmount) {
        this.memberId = memberId;
        this.paidAmount = paidAmount;
    }
}
