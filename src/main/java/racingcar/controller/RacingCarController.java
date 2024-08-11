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

@RestController  // @Controller + @ResponseBody
public class RacingCarController {
    private final RacingCarService racingCarService;

    // 컨트롤러와 서비스 연결
    @Autowired
    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping(path = "/plays")
    public ResponseEntity<RacingCarResult> play(@RequestBody final RacingCarRequest request) {
        // 입력받은 이름 ,로 분리 후 리스트 저장
        final List<String> names = Arrays.stream(request.getNames().split(","))
                .collect(Collectors.toList());

        final int count = request.getCount();

        final RacingCarResult racingCarResult = racingCarService.playRacingCar(names, count);

        return ResponseEntity.ok().body(racingCarResult); // HTTP 응답 반환, 200 OK와 함께 RacingCarResult 응답 본문으로 포함
    }

    // 모든 게임 로그 조회
    @GetMapping(path = "/plays")
    public List<RacingCarResponse> getLog() {
        return racingCarService.getRacingCarLog(); // List<RacingCarResponse> 반환
    }
}
