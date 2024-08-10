package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.model.Car;

import java.sql.PreparedStatement;
import java.util.List;

public interface CarDao {

    int insert(final Car car, final int gameId, final boolean isWinner);

    List<String> selectWinners(final int gameId);

    List<Car> selectCarsBy(final int gameId);

}
