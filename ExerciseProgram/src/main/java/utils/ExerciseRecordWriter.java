package utils;

import java.io.FileWriter;
import java.io.IOException;

public class ExerciseRecordWriter {
    public ExerciseRecordWriter(InputRecordAverage inputRecordAverage) throws IOException {
        FileWriter fileWriter = new FileWriter("data/exerciseRecord.csv");

        String record = inputRecordAverage.toCsvRow();

        fileWriter.write(record);

        fileWriter.close();
    }
}
