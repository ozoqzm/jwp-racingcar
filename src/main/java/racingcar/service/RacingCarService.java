package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.JdbcCarDao;
import racingcar.model.Car;
import racingcar.model.dto.RacingCarResponse;
import racingcar.model.dto.RacingCarResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {

    private final CarDao carDao;
    private List<String> winners = new ArrayList<>();

    @Autowired
    public RacingCarService(final JdbcCarDao jdbcCarDao) {
        this.carDao = jdbcCarDao;
    }

    // 플레이
    public RacingCarResult playRacingCar(final List<String> names, final int trialCount) {
        final List<Car> racingCars = generateRacingCars(names);

        // 경주하기.. 우승자 판별
        raceGame(racingCars, trialCount);

        // 방금 주행 결과 객체 (수정)
        final RacingCarResult racingCarResult = new RacingCarResult(new ArrayList<>(winners), racingCars); // 우승자 리스트 복사본을 생성해 전달 (반환 이전 초기화 문제 해결)

        saveResult(trialCount, racingCarResult);

        winners.clear(); // 우승자 초기화

        return racingCarResult;
    }

    // 차 리스트 생성 (객체들)
    public List<Car> generateRacingCars(final List<String> names) {
        // 리스트 자동차 객체 생성
        final List<Car> cars = names.stream()
                .map(name -> new Car(name, 0))
                .collect(Collectors.toList());

         return cars;
    }

    public void raceGame(final List<Car> racingCars, final int trialCount) {
        // 경주
        for (int i = 0; i < trialCount; i++) {
            for (Car car : racingCars) {
                car.move(car.getRandomNo());
            }
        }

        // 우승자 가리기
        int maxPosition = 0;
        for (Car car : racingCars)
            if (car.getPosition() > maxPosition) {
                maxPosition = car.getPosition();
            }

        for (Car car : racingCars) {
            if (car.getPosition() == maxPosition) {
                winners.add(car.getName());
            }
        }
    }

    // 데이터 베이스 저장
    public void saveResult(final int trialCount, final RacingCarResult racingCarResult) {
       List<Car> racingCars = racingCarResult.getRacingCars();

        String winners = String.join(",", racingCarResult.getWinners());

       final int gameId = carDao.insertResult(trialCount, winners);

       for (Car car : racingCars) {
           final int carId = carDao.insertCar(car, gameId);
       }
    }

    public List<String> getWinnerByGameId(final int gameId) {
        final String swinners = carDao.selectWinners(gameId);
        final List<String> winners = Arrays.stream(swinners.split(","))
                .collect(Collectors.toList());

        return winners;
    }

    public List<Car> getRacingCarsByGameId(final int gameId) {
        final List<Car> cars = carDao.selectCars(gameId);

        return cars;
    }

    public List<RacingCarResponse> getRacingCarLog() {
        final List<Integer> gameIds = carDao.selectGameIds();

        return gameIds.stream()
                .map(this::generateLog)
                .collect(Collectors.toUnmodifiableList());
    }

    private RacingCarResponse generateLog(final int gameId) {
        final RacingCarResult result = new RacingCarResult(getWinnerByGameId(gameId), getRacingCarsByGameId(gameId));
        return new RacingCarResponse(result);
    }


}
