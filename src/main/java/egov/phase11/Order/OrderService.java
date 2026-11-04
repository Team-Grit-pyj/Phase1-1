package egov.phase11.Order;

import egov.phase11.Item.ItemRepository;
import egov.phase11.discount.DiscountPolicy;
import egov.phase11.discount.Grade;

public class OrderService {

    private final DiscountPolicy discountPolicy;
    private final ItemRepository itemRepository;
    private final PaymentGateway paymentGateway;
    private final OrderRepository orderRepository;

    public OrderService(DiscountPolicy discountPolicy,
                        ItemRepository itemRepository,
                        PaymentGateway paymentGateway,
                        OrderRepository orderRepository) {
        this.discountPolicy = discountPolicy;
        this.itemRepository = itemRepository;
        this.paymentGateway = paymentGateway;
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(long memberId, OrderRequest request, Grade grade) {
        throw new UnsupportedOperationException("아직 구현되지 않았습니다.");
    }
}
