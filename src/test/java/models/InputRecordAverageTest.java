package models;

import org.junit.jupiter.api.Test;
import utils.InputRecordAverage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputRecordAverageTest {
    @Test
    void toCsvRow() {
        InputRecordAverage inputRecordAverage = new InputRecordAverage();

        inputRecordAverage.averageProcess("1", "2", "5", "푸쉬업");

        inputRecordAverage.averageProcess("1", "2", "4", "풀업");

        inputRecordAverage.averageProcess("1", "2", "6", "스쿼트");

        assertEquals("2,2,3", inputRecordAverage.toCsvRow());
    }
}
