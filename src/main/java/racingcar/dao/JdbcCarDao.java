package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.model.Car;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class JdbcCarDao implements CarDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // override 하기
    // 경기결과테이블(시도횟수, 우승자), 자동차테이블(이름,포지션) 데이터베이스에 저장
    @Override
    public int insertResult(final int trialCount, final String winners) {
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        final String sql = "INSERT INTO play_result(trial_count, winners) VALUES (?, ?)";
        this.jdbcTemplate.update(con -> {
            final PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setInt(1, trialCount);
            preparedStatement.setString(2, winners);
            return preparedStatement;
        }, keyHolder);


        return keyHolder.getKey().intValue();
    }

    @Override
    public int insertCar(final Car car, final int gameId) {
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        final String sql = "INSERT INTO cars(play_result_id, name,position) VALUES (?,?,?)";
        this.jdbcTemplate.update(con -> {
            final PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setInt(1, gameId);
            preparedStatement.setString(2, car.getName());
            preparedStatement.setInt(3, car.getPosition());
            return preparedStatement;
        },  keyHolder);

        return keyHolder.getKey().intValue();
    }

    // 해당 경주 우승자 조회
    @Override
    public List<String> selectWinners(final int gameId) {

    }

    // 해당 경주 자동차들 조회
    @Override
    public List<Car> selectCars(final int gameId) {

    }
}
