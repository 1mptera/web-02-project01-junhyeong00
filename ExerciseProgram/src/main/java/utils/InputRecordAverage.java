package utils;

public class InputRecordAverage {

    private int pushUpAverage;
    private int pullUpAverage;
    private int squatAverage;

    public void averageProcess(String firstSetNumber, String secondSetNumber, String thirdSetNumber, String type) {
        int sum = Integer.parseInt(firstSetNumber) + Integer.parseInt(secondSetNumber) + Integer.parseInt(thirdSetNumber);

        switch (type) {
            case "푸쉬업" -> pushUpAverage = sum / 3;
            case "풀업" -> pullUpAverage = sum / 3;
            case "스쿼트" -> squatAverage = sum / 3;
        }
    }

    public String[] values() {
        return new String[]{Integer.toString(pushUpAverage),
                Integer.toString(pullUpAverage),
                Integer.toString(squatAverage)};
    }

    public String toCsvRow() {
        return String.join(",", values());
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
