package utils;

import models.Exercise;
import models.ExerciseRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExerciseRecordWriter {

    public void saveExerciseInformation(InputRecordAverage inputRecordAverage) throws IOException {
        FileWriter fileWriter = new FileWriter("data/currentExerciseRecord.csv");

        String record = inputRecordAverage.toCsvRow();

        fileWriter.write(record);

        fileWriter.close();
    }

    public void saveExerciseRecord(List<ExerciseRecord> exerciseRecords) throws IOException {
        FileWriter fileWriter = new FileWriter("data/ExerciseLog.csv");

        for (ExerciseRecord exerciseRecord : exerciseRecords) {
            String line = exerciseRecord.toCsvRow();

            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }

}
