package egov.phase11.order;

import egov.phase11.Item.Item;
import egov.phase11.Item.ItemRepository;
import egov.phase11.discount.DiscountPolicy;
import egov.phase11.discount.Grade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private static final long MEMBER_ID = 1L;
    private static final long ITEM_ID = 1L;

    @Mock
    OrderRepository orderRepository;

    @Mock
    ItemRepository itemRepository;

    @Mock
    PaymentGateway paymentGateway;

    // 외부 의존이 없는 순수 계산 로직이라 mock 대신 실제 객체를 사용한다
    DiscountPolicy discountPolicy = new DiscountPolicy();

    OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(discountPolicy, itemRepository, paymentGateway, orderRepository);
    }

    @Test
    @DisplayName("일반회원이 5만원 제품 3개를 주문하면 10% 할인된 금액으로 결제 후 저장한다")
    void normal_order() {
        // Given: 15만원 -> 10만원 이상이므로 기본 10% 할인 -> 135,000원
        Item item = new Item(1L, 50_000, 10);
        given(itemRepository.findById(ITEM_ID)).willReturn(Optional.of(item));
        given(paymentGateway.pay(MEMBER_ID, 135_000)).willReturn(true);

        // When
        orderService.placeOrder(MEMBER_ID, new OrderRequest(ITEM_ID, 3), Grade.NORMAL);

        // Then: 결제가 먼저, 그 다음 저장
        InOrder inOrder = inOrder(paymentGateway, orderRepository);
        inOrder.verify(paymentGateway).pay(MEMBER_ID, 135_000);
        inOrder.verify(orderRepository).save(any(Order.class));
    }

    @Test
    @DisplayName("결제가 거절되면 예외가 발생하고 주문은 저장되지 않는다")
    void payment_rejected_does_not_save_order() {
        // Given
        Item item = new Item(1L, 50_000, 10);
        given(itemRepository.findById(ITEM_ID)).willReturn(Optional.of(item));
        given(paymentGateway.pay(anyLong(), anyInt())).willReturn(false);

        // When & Then
        assertThatIllegalStateException()
                .isThrownBy(() -> orderService.placeOrder(MEMBER_ID, new OrderRequest(ITEM_ID, 3), Grade.NORMAL))
                .withMessageContaining("Payment failed");

        verify(orderRepository, never()).save(any(Order.class));
    }
}
