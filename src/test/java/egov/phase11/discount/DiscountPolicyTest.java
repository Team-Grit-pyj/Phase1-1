package egov.phase11.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DiscountPolicyTest {

    private final DiscountPolicy policy = new DiscountPolicy();

    @ParameterizedTest(name = "일반 등급 {0}원 -> 할인 없이 {0}원")
    @DisplayName("일반 등급은 10만원 미만이면 할인이 없다")
    @ValueSource(ints = {
            0,        // 경계: 0원은 예외가 아닌 정상 입력
            5_000,
            10_000,
            99_999    // 경계: 10만원 바로 아래
    })
    void normal_under_threshold_no_discount(int amount) {
        int result = policy.calculate(amount, Grade.NORMAL);

        assertThat(result).isEqualTo(amount);
    }

    @ParameterizedTest(name = "{1} 등급 {0}원 -> {2}원")
    @DisplayName("할인이 적용되는 경우 등급과 금액에 맞는 할인율이 적용된다")
    @CsvSource({
            "100000, NORMAL, 90000",  // 경계: 10만원 정확히 -> 기본 10%
            "10000,  VIP,    9500",   // VIP는 금액과 무관하게 5%
            "100000, VIP,    85000"   // VIP + 10만원 이상 -> 10% + 5% = 15%
    })
    void discount_applied(int amount, Grade grade, int expected) {
        int result = policy.calculate(amount, grade);

        assertThat(result).isEqualTo(expected);
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
