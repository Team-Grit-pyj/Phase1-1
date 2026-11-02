package egov.phase11.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


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

    @Test
    @DisplayName("일반등급 99,999원 결제시 할인없음")
    void normal_99999_no_discount(){
        // Given
        int amount = 99_999;

        // When
        int result = policy.calculate(amount, Grade.NORMAL);

        // Then
        assertThat(result).isEqualTo(99_999);
    }

    @Test
    @DisplayName("VIP 등급 10만원 미만 5% 할인")
    void vip_under_threshold_5_discount() {
        // Given
        int amount = 10_000;

        // When
        int result = policy.calculate(amount, Grade.VIP);

        // Then
        assertThat(result).isEqualTo(9_500);
    }

    @Test
    @DisplayName("VIP 등급 10만원 이상 15% 할인")
    void vip_over_threshold_15_discount() {
        // Given
        int amount = 100_000;

        // When
        int result = policy.calculate(amount, Grade.VIP);

        // Then
        assertThat(result).isEqualTo(85_000);
    }

    @Test
    @DisplayName("음수 금액이면 예외 발생")
    void negative_amount_throws_exception() {
        // Given
        int amount = -1;

        // When & Then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> policy.calculate(amount, Grade.NORMAL))
                .withMessageContaining("금액");
    }

    @Test
    @DisplayName("0원이면 예외발생 안함")
    void zero_amount_no_exception() {
        // Given
        int amount = 0;

        // When
        int result = policy.calculate(amount, Grade.NORMAL);

        // Then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Grade 가 null이면 예외 발생")
    void null_grade_throws_exception() {
        // Given
        int amount = 100_000;

        // When & Then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> policy.calculate(amount, null))
                .withMessageContaining("등급");
    }
}
