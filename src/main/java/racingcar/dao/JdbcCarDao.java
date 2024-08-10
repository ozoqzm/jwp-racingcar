package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.model.Car;

import java.util.List;

@Repository
public class JdbcCarDao implements CarDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // override 하기
    @Override
    public int insert(final Car car, final int gameId, final boolean isWinner) {

    }

    @Override
    public List<String> selectWinners(final int gameId) {

    }

    @Override
    public List<Car> selectCarsBy(final int gameId) {

    }
}
