package egov.phase11.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class DiscountPolicyTest {

    private final DiscountPolicy policy = new DiscountPolicy();

    @Test
    @DisplayName("일반 등급은 10만원 미만이면 할인이 없다.")
    void normal_under_threshold_no_discount() {
        // Given
        int amount = 10_000;

        // When
        int result = policy.calculate(amount, Grade.NORMAL);

        // Then
        assertThat(result).isEqualTo(10_000);
    }
}
