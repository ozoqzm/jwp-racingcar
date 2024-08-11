package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.model.Car;
import racingcar.model.dto.RacingCarResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {

    private final CarDao carDao;

    // 플레이
    public RacingCarResult playRacingCar(final List<String> names, final int trialCount) {
        final List<Car> racingCars = generateRacingCars(names);

        // 주행 결과 객체
        // 우승자들을 어떻게 가져오면 좋을 지??
        final RacingCarResult racingCarResult = new RacingCarResult(우승자 이름들, 경주차들);

        // 경주하기.. 우승자 판별
        raceGame(racingCars, trialCount);

        // 경기결과테이블(시도횟수, 우승자), 자동차테이블(이름,포지션) 데이터베이스에 저장
        saveResult(trialCount, racingCarResult);


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

        // 우승자 가리지
        int maxPosition = 0;
        for (Car car : racingCars)
            if (car.getPosition() > maxPosition) {
                maxPosition = car.getPosition();
            }
        List<String> winners = new ArrayList<>();
        for (Car car : racingCars) {
            if (car.getPosition() == maxPosition) {
                winners.add(car.getName());
            }
        }
    }

    // 데이터 베이스 저장
    public void saveResult(final int trialCount, final RacingCarResult racingCarResult) {

    }
}
