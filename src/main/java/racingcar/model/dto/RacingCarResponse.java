package racingcar.model.dto;

import racingcar.model.Car;

import java.util.List;

// 실 전송될 응답 데이터 객체
public class RacingCarResponse {
    private final String winners; // 우승자 이름들 (여러 명일수도... 쉼표 분리 전)
    private final List<Car> racingCars; // 경주 참가 Car 리스트

    public RacingCarResponse(final RacingCarResult racingCarResult) {
        this.winners = String.join(",", racingCarResult.getWinners());
        this.racingCars = racingCarResult.getRacingCars();
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
