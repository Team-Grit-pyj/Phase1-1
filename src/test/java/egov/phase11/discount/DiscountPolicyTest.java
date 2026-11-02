package egov.phase11.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DiscountPolicyTest {

    private final DiscountPolicy policy = new DiscountPolicy();

    @Test
    @DisplayName("일반 등급은 10만원 미만이면 할인이 없다")
    void normal_under_threshold_no_discount() {
        // Given
        int amount = 10_000;

        // When
        int result = policy.calculate(amount, Grade.NORMAL);

        // Then
        assertThat(result).isEqualTo(10_000);
    }

    @Test
    @DisplayName("일반 등급은 5천원이어도 할인이 없다")
    void normal_5000_no_discount() {
        // Given
        int amount = 5_000;

        // When
        int result = policy.calculate(amount, Grade.NORMAL);

        // Then
        assertThat(result).isEqualTo(5_000);
    }

    @Test
    @DisplayName("일반 등급은 10만원 이상이면 10% 할인된다")
    void normal_over_threshold_10_percent_discount() {
        // Given
        int amount = 100_000;

        // When
        int result = policy.calculate(amount, Grade.NORMAL);

        // Then
        assertThat(result).isEqualTo(90_000);
    }

    @Test
    @DisplayName("일반 등급은 99,999원이면 할인이 없다")
    void normal_99999_no_discount() {
        // Given
        int amount = 99_999;

        // When
        int result = policy.calculate(amount, Grade.NORMAL);

        // Then
        assertThat(result).isEqualTo(99_999);
    }

    @Test
    @DisplayName("VIP 등급은 10만원 미만이면 5% 할인된다")
    void vip_under_threshold_5_discount() {
        // Given
        int amount = 10_000;

        // When
        int result = policy.calculate(amount, Grade.VIP);

        // Then
        assertThat(result).isEqualTo(9_500);
    }

    @Test
    @DisplayName("VIP 등급은 10만원 이상이면 15% 할인된다")
    void vip_over_threshold_15_discount() {
        // Given
        int amount = 100_000;

        // When
        int result = policy.calculate(amount, Grade.VIP);

        // Then
        assertThat(result).isEqualTo(85_000);
    }

    @Test
    @DisplayName("음수 금액이면 예외가 발생한다")
    void negative_amount_throws_exception() {
        // Given
        int amount = -1;

        // When & Then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> policy.calculate(amount, Grade.NORMAL))
                .withMessageContaining("금액");
    }

    @Test
    @DisplayName("0원이면 예외 없이 0원을 반환한다")
    void zero_amount_no_exception() {
        // Given
        int amount = 0;

        // When
        int result = policy.calculate(amount, Grade.NORMAL);

        // Then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("등급이 null이면 예외가 발생한다")
    void null_grade_throws_exception() {
        // Given
        int amount = 100_000;

        // When & Then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> policy.calculate(amount, null))
                .withMessageContaining("등급");
    }
}
