package utils;

public class DifficultyControl {
    public int warmUp1(int average) {
        int warmUp1 = (int) (average * 0.4);
        return warmUp1;
    }

    public int warmUp2(int average) {
        int warmUp2 = (int) (average * 0.6);
        return warmUp2;
    }

    public int set(int average) {
        int set = (int) (average * 1.1);
        return set;
    }
}
