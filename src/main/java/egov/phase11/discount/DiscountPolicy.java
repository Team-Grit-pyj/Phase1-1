package egov.phase11.discount;

public class DiscountPolicy {

    public int calculate(int amount, Grade grade) {

        if(grade == Grade.VIP) {
            return amount * 95 / 100;
        }

        if(amount >= 100_000){
            return amount * 90 / 100;
        }
        return amount;
    }
}
