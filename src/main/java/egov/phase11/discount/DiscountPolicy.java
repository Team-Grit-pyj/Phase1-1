package egov.phase11.discount;

public class DiscountPolicy {

    public int calculate(int amount, Grade grade) {

        if(amount < 0) throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");

        if(grade == Grade.VIP && amount >= 100_000) {
            return amount * 85 / 100;
        }else if (grade == Grade.VIP) {
            return amount * 95 / 100;
        }else if(amount >= 100_000) {
            return amount * 90 / 100;
        }
        return amount;
    }
}
