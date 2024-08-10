package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.model.Car;
import racingcar.model.dto.RacingCarResult;

import java.util.List;

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

    public List<Car> generateRacingCars(String names) {
        // 이름 리스트 토대로 자동차 리스트

        // return racingCarList;
    }

}
