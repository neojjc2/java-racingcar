package camp.nextstep.edu.rebellion.racing.domain;

import camp.nextstep.edu.rebellion.racing.domain.RacingCar;
import camp.nextstep.edu.rebellion.racing.rule.RacingRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RacingCarTest {
    @DisplayName("자동차가 움직이는지 확인")
    @Test
    public void moveTest() {
        // given
        RacingRule alwaysGoRule = () -> true;
        RacingCar car = new RacingCar("GO");

        // when
        car.move(alwaysGoRule);

        // then
        assertThat(car.getPosition()).isPositive();
    }

    @DisplayName("자동차가 안 움직이는지 확인")
    @Test
    public void notMoveTest() {
        // given
        RacingRule alwaysStopRule = () -> false;
        RacingCar car = new RacingCar("STOP");

        // when
        car.move(alwaysStopRule);

        // then
        assertThat(car.getPosition()).isZero();
    }

    @DisplayName("자동차 이름이 다섯글자를 초과할 경우 예외 발생")
    @Test
    public void nameTest() {
        // when & then
        assertThatThrownBy(
                () -> {
                    RacingCar car = new RacingCar("123456");
                }
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름이 최대 길이를 초과했습니다 6");
    }
}
