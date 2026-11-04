package egov.phase11.order;

public interface PaymentGateway {

    /**
     * @return 결제 승인 여부
     */
    boolean pay(long memberId, int amount);
}
