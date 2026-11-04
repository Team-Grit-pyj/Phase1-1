package egov.phase11.Item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class ItemTest {

    private static final int STOCK = 3;

    private final Item item = new Item(1L, 50_000, STOCK);

    @ParameterizedTest(name = "재고 3개, 주문 {0}개 -> 통과")
    @DisplayName("주문 수량이 재고 이하이면 예외가 발생하지 않는다")
    @ValueSource(ints = {
            1,
            2,
            3     // 경계: 재고와 같은 수량
    })
    void order_within_stock(int orderStock) {
        assertThatCode(() -> item.itemValidateStock(orderStock))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("주문 수량이 재고보다 1개라도 많으면 예외가 발생한다")
    void order_over_stock_throws_exception() {
        // Given: 경계값 - 재고 3개, 주문 4개
        int orderStock = STOCK + 1;

        // When & Then
        assertThatIllegalStateException()
                .isThrownBy(() -> item.itemValidateStock(orderStock))
                .withMessageContaining("Insufficient stock");
    }
}
