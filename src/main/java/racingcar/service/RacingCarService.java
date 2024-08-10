package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.model.Car;
import racingcar.model.dto.RacingCarResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {

    private final CarDao carDao;

    public RacingCarResult playRacingCar(final List<String> names, final int trialCount) {
        final List<Car> racingCars = generateRacingCars(names);
        // final RacingCarResult racingCarResult = new RacingCarResult(우승자 이름들, 경주차들);

        // 경주하기.. 우승자 판별

        //  시도 횟수 + 결과 데이터 저장
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

}
