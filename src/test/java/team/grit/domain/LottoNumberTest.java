package team.grit.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumberTest {

    @Test
    @DisplayName("1~45 번호 생성 성공")
    void generateValidLottoNumber() {
        LottoNumber lottoNumber = LottoNumber.of(4);

        assertThat(lottoNumber.getValue()).isEqualTo(4);
    }

}
