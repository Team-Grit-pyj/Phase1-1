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

    @Test
    @DisplayName("일반등급 5,000 -> 5,000원 (할인없음)")
    void normal_5000_no_discount() {
        // Given
        int amount = 5_000;

        // When
        int result = policy.calculate(amount, Grade.NORMAL);

        // Then
        assertThat(result).isEqualTo(5_000);
    }

    @Test
    @DisplayName("일반등급 10만원 이상이면 10% 할인.")
    void normal_100000_discount() {
        // Given
        int amount = 100_000;

        // When
        int result = policy.calculate(amount, Grade.NORMAL);

        // Then
        assertThat(result).isEqualTo(90_000);
    }
}
