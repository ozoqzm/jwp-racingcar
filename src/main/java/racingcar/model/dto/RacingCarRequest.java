package racingcar.model.dto;

public class RacingCarRequest {
    private final String names; // 참가자 입력 이름들
    private final int count; // 시도 횟수

    public RacingCarRequest(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getNames() {
        return names;
    }
}
