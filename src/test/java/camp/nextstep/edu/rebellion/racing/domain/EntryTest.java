package camp.nextstep.edu.rebellion.racing.domain;

import camp.nextstep.edu.rebellion.racing.domain.Entry;
import camp.nextstep.edu.rebellion.racing.domain.RacingCar;
import camp.nextstep.edu.rebellion.racing.domain.SnapShotEntry;
import camp.nextstep.edu.rebellion.racing.rule.RacingRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EntryTest {
    private List<RacingCar> cars;

    @BeforeEach
    void setUp() {
        cars = new ArrayList<>();
        cars.add(new RacingCar("1"));
        cars.add(new RacingCar("2"));
        cars.add(new RacingCar("3"));
    }

    @DisplayName("Entry에 있는 자동차들이 잘 움직이는지 확인")
    @Test
    public void moveTest() {
        // given
        Entry entry = new Entry(cars);
        RacingRule alwaysGoRule = () -> true;

        // when
        entry.move(alwaysGoRule);

        // then
        SnapShotEntry snapShotEntry = entry.getSnapshot();
        assertThat(snapShotEntry.getCars().stream()
                .mapToInt(car -> car.getPosition())
                .sum())
                .isEqualTo(3);
    }
}
