package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseRecordTest {
    @Test
    void creation() {
        ExerciseRecord exerciseRecord = new ExerciseRecord(4, 4, 4);
    }

    @Test
    void toCsvRow() {
        ExerciseRecord exerciseRecord = new ExerciseRecord(4, 5, 6);

        assertEquals("4,5,6", exerciseRecord.toCsvRow());
    }

    @Test
    void ToString() {
        ExerciseRecord exerciseRecord = new ExerciseRecord(4, 5, 6);

        assertEquals("푸쉬업 평균: 4개,  풀업 평균: 5개,  스쿼트 평균: 6개", exerciseRecord.toString());
    }
}
