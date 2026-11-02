package egov.phase11.discount;

public class DiscountPolicy {

    private static final int DISCOUNT_THRESHOLD = 100_000;
    private static final int VIP_EXTRA_DISCOUNT_PERCENT = 5;
    private static final int BASIC_DISCOUNT_PERCENT = 10;

    public int calculate(int amount, Grade grade) {

        if(grade == null) throw new IllegalArgumentException("등급은 반드시 필요합니다.");

        if(amount < 0) throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");

        if(grade == Grade.VIP && amount >= DISCOUNT_THRESHOLD) {
            return amount * (100 - VIP_EXTRA_DISCOUNT_PERCENT - BASIC_DISCOUNT_PERCENT) / 100;
        }else if (grade == Grade.VIP) {
            return amount * (100 - VIP_EXTRA_DISCOUNT_PERCENT) / 100;
        }else if(amount >= 100_000) {
            return amount * (100 - BASIC_DISCOUNT_PERCENT) / 100;
        }
        return amount;
    }
}
