package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.model.dto.RacingCarRequest;
import racingcar.model.dto.RacingCarResponse;
import racingcar.model.dto.RacingCarResult;
import racingcar.service.RacingCarService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingCarController {
    private final RacingCarService racingCarService;

    // 컨트롤러와 서비스 연결
    @Autowired
    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    // 입력받은 names 쉼표 분리
    // String[] arrayString = carNameList.split(","); -> 이거 나중에 넣기
    // 입력받은 시도 횟수

    // submit 누르면
    @PostMapping(path = "/plays")
    public ResponseEntity<RacingCarResult> play(@RequestBody final RacingCarRequest request) {
        final List<String> names = Arrays.stream(request.getNames().split(DELIMITER))
                .collect(Collectors.toList());
        final int count = request.getCount();

        final RacingCarResult racingCarResult = racingCarService.playRacingCar(names, count);

        return ResponseEntity.ok().body(racingCarResult);
    }

    @GetMapping(path = "/plays")
    public List<RacingCarResponse> getLog() {
        return racingCarService.getRacingCarLog();
    }
}
