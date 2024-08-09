package racingcar;

import racingcar.model.Car;

import java.util.ArrayList;
import java.util.List;

public class RacingGame {
    private List<Car> cars;

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    public List<Car> getCars() {
        return cars;
    }

    public void racing() {
        for (Car c : cars) {
            int num = c.getRandomNo();
            c.move(num);
        }
    }
    public void printCarsDistance() {
        for (Car c : cars) {
            System.out.print(c.getCarName() + ":");
            for (int i = 0; i < c.getPosition(); i++)
                System.out.print("-");
            System.out.println();
        }
        System.out.println();
    }

    public void printWinners(List<Car> cars) {
        int maxPosition = 0;
        for (Car car : cars)
            if (car.getPosition() > maxPosition) {
                maxPosition = car.getPosition();
            }
        List<String> winners = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPosition() == maxPosition) {
                winners.add(car.getCarName());
            }
        }
        System.out.println("우승자: " + winners + "가 최종 우승했습니다.");
    }
}