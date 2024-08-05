package racingcar;

import java.util.Random;

public class Car implements MovableStrategy {
    // 상수
    private static final int FORWARD_NUM = 4;
    private int position; // 현재 위치
    public String carName;

    public Car(String carName, int position) {
        this.carName = carName;
        this.position = position;
    }
    public void move(int number) {
        if (isMove(number)) {
            position++;
        }
    }
    public int getRandomNo() {
        Random random = new Random();
        return random.nextInt(10);
    }

    public int getPosition() {
        return position;
    }

    public String getCarName() {
        return carName;
    }

    @Override
    public boolean isMove(int number) {
        return number >= 4;
    }
}