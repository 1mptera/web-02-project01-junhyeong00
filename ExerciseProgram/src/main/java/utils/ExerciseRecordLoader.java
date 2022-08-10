package utils;

import models.Exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExerciseRecordLoader {

    private int pushUpAverage;
    private int pullUpAverage;
    private int squatAverage;

    public Exercise loadExerciseRecord() throws FileNotFoundException {
        File file = new File("data/exerciseRecord.csv");

        Scanner scanner = new Scanner(file);

        String line = scanner.nextLine();

        String[] words = line.split(",");
        pushUpAverage = Integer.parseInt(words[0]);
        pullUpAverage = Integer.parseInt(words[1]);
        squatAverage = Integer.parseInt(words[2]);

        return new Exercise(pushUpAverage, pullUpAverage,squatAverage);
    }
}
