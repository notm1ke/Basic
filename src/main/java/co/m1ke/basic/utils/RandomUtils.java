package co.m1ke.basic.utils;

import java.util.List;
import java.util.Random;

public class RandomUtils {

    private static final char[] ALPHABET = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static <T> T getRandom(List<T> array) {
        return array.get(new Random().nextInt(array.size() - 1));
    }

    public static int getRandomInt() {
        return new Random().nextInt();
    }

    public static int getRandomInt(int i1, int i2) {
        return (int) (Math.random() * ((i2 - i1) + 1)) + i1;
    }

    public static long getNextLong() {
        return new Random().nextLong();
    }

    public static double getRandomDouble() {
        return new Random().nextDouble();
    }

    public static double getRandomDouble(int i1, int i2) {
        return (Math.random() * ((i2 - i1) + 1)) + i1;
    }

    public static float getRandomFloat() {
        return new Random().nextFloat();
    }

    public static boolean getRandomBoolean() {
        return new Random().nextBoolean();
    }

    public static char getRandomLetter() {
        return ALPHABET[new Random().nextInt(ALPHABET.length) - 1];
    }

}
