package racingcar.model.dto;

import racingcar.model.Car;

import java.util.List;

public class RacingCarResult {
    private final List<String> winners; // 우승자 리스트
    private final List<Car> racingCars; // 경기 참여 자동차들

    public RacingCarResult(final List<String> winners, final List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
