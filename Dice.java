package tiei.ajp.lab1;

import java.util.Random;

public class Dice {

    Random random = new Random();

    private int genNumber() {
        return random.nextInt(6) + 1;
    }

    public int getNumber(int times) {
        int cnt = 0;
        for (int i = 0; i < times; i++) {
            cnt += genNumber();
        }
        return cnt;
    }

}