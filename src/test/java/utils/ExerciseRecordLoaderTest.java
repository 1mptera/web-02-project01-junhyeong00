package utils;

import models.ExerciseRecord;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseRecordLoaderTest {
    @Test
    void loadExerciseLog() throws FileNotFoundException {
        ExerciseRecordLoader exerciseRecordLoader = new ExerciseRecordLoader();

        List<ExerciseRecord> exerciseRecords = exerciseRecordLoader.loadExerciseLog();

        assertNotNull(exerciseRecords);
    }
}
