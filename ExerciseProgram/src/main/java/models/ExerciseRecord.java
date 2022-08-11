package models;

import java.util.ArrayList;
import java.util.List;

public class ExerciseRecord {
    private int pushUpAverage;
    private int pullUpAverage;
    private int squatAverage;
    private List<ExerciseRecord> exerciseRecords;

    public ExerciseRecord(int pushUpAverage, int pullUpAverage, int squatAverage) {

        this.pushUpAverage = pushUpAverage;
        this.pullUpAverage = pullUpAverage;
        this.squatAverage = squatAverage;
    }

    public String[] values() {
        return new String[]{Integer.toString(pushUpAverage),
                Integer.toString(pullUpAverage),
                Integer.toString(squatAverage)};
    }

    public String toCsvRow() {
        return String.join(",", values());
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "푸쉬업 평균: "
                + pushUpAverage + "개,  풀업 평균: "
                + pullUpAverage + "개,  스쿼트 평균: "
                + squatAverage + "개";
    }
}
