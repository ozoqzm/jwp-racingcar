package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.model.Car;

import java.sql.PreparedStatement;
import java.util.List;

public interface CarDao {

    int insertResult(final int trialCount, final String winners);

    int insertCar(final Car car, final int gameId);

    String selectWinners(final int gameId);

    List<Car> selectCars(final int gameId);

}
