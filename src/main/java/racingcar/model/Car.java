package racingcar.model;

import java.util.Random;

public class Car implements MovableStrategy {
    // 상수
    private static final int FORWARD_NUM = 4;
    private int position; // 현재 위치
    public String name;

    public Car(String name, int position) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    @Override
    public boolean isMove(int number) {
        return number >= 4;
    }
}