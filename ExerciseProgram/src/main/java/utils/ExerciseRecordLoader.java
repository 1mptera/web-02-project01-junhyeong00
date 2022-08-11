package utils;

import models.Exercise;
import models.ExerciseRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExerciseRecordLoader {

    private int pushUpAverage;
    private int pullUpAverage;
    private int squatAverage;

    public Exercise loadExerciseRecord() throws FileNotFoundException {
        File file = new File("data/currentExerciseRecord.csv");

        Scanner scanner = new Scanner(file);

        String line = scanner.nextLine();

        return parseExercise(line);
    }

    public List<ExerciseRecord> loadExerciseLog() throws FileNotFoundException {
        List<ExerciseRecord> exerciseRecords = new ArrayList<>();

        File file = new File("data/exerciseLog.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            ExerciseRecord exerciseRecord = parseExerciseRecord(line);

            exerciseRecords.add(exerciseRecord);
        }

        return exerciseRecords;
    }

    private ExerciseRecord parseExerciseRecord(String text) {
        String[] words = text.split(",");

        pushUpAverage = Integer.parseInt(words[0]);
        pullUpAverage = Integer.parseInt(words[1]);
        squatAverage = Integer.parseInt(words[2]);

        return new ExerciseRecord(pushUpAverage, pullUpAverage,squatAverage);
    }

    private Exercise parseExercise(String text) {
        String[] words = text.split(",");

        pushUpAverage = Integer.parseInt(words[0]);
        pullUpAverage = Integer.parseInt(words[1]);
        squatAverage = Integer.parseInt(words[2]);

        return new Exercise(pushUpAverage, pullUpAverage,squatAverage);
    }
}
