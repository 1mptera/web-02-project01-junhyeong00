package utils;

public class InputRecordAverage {

    private int pushUpAverage;
    private int pullUpAverage;
    private int squatAverage;

    public void averageProcess(String number1, String number2, String number3, String type) {
        int sum = Integer.parseInt(number1) + Integer.parseInt(number2) + Integer.parseInt(number3);

        if (type.equals("푸쉬업")) {
            pushUpAverage = sum / 3;
        }

        if (type.equals("풀업")) {
            pullUpAverage = sum / 3;
        }

        if (type.equals("스쿼트")) {
            squatAverage = sum / 3;
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
