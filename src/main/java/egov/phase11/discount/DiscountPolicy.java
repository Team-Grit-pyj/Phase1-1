package egov.phase11.discount;

public class DiscountPolicy {

    private static final int DISCOUNT_THRESHOLD = 100_000;
    private static final int VIP_EXTRA_DISCOUNT_PERCENT = 5;
    private static final int BASIC_DISCOUNT_PERCENT = 10;

    public int calculate(int amount, Grade grade) {

        validate(amount, grade);

        int totalDiscount = 0;

        if(grade == Grade.VIP) totalDiscount += VIP_EXTRA_DISCOUNT_PERCENT;
        if(amount >= DISCOUNT_THRESHOLD) totalDiscount += BASIC_DISCOUNT_PERCENT;

        return amount * (100 - totalDiscount) / 100;
    }

    private void validate(int amount, Grade grade) {
        if(grade == null) throw new IllegalArgumentException("등급은 반드시 필요합니다.");
        if(amount < 0) throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
    }
}
