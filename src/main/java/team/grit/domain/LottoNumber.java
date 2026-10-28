package team.grit.domain;

public record LottoNumber(
        int number
) {

    public static LottoNumber of(int number) {
        return new LottoNumber(number);
    }

    public int getValue() {
        return this.number;
    }
}
