package models;

public class Exercise {

    private int pushUpAverage;
    private int pullUpAverage;
    private int squatAverage;

    public Exercise(int pushUpAverage, int pullUpAverage, int squatAverage) {

        this.pushUpAverage = pushUpAverage;
        this.pullUpAverage = pullUpAverage;
        this.squatAverage = squatAverage;
    }

    public int pushUpAverage() {
        return pushUpAverage;
    }

    public int pullUpAverage() {
        return pullUpAverage;
    }

    public int squatAverage() {
        return squatAverage;
    }
}
