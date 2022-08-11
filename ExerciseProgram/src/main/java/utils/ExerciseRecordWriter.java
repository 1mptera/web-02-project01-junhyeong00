package utils;

import java.io.FileWriter;
import java.io.IOException;

public class ExerciseRecordWriter {
    private InputRecordAverage inputRecordAverage;

    public ExerciseRecordWriter(InputRecordAverage inputRecordAverage) throws IOException {
        this.inputRecordAverage = inputRecordAverage;

        saveExerciseRecord();
    }

    private void saveExerciseRecord() throws IOException {
        FileWriter fileWriter = new FileWriter("data/exerciseRecord.csv");

        String record = inputRecordAverage.toCsvRow();

        fileWriter.write(record);

        fileWriter.close();
    }
}
