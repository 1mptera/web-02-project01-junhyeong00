package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {
    @Test
    void creation() {
        Exercise exercise = new Exercise(0, 0, 0);
    }

    @Test
    void pushUpAverage() {
        Exercise exercise = new Exercise(1, 0, 0);

        assertEquals(1, exercise.pushUpAverage());
    }

    @Test
    void pullUpAverage() {
        Exercise exercise = new Exercise(1, 2, 3);

        assertEquals(2, exercise.pullUpAverage());
    }

    @Test
    void squatAverage() {
        Exercise exercise = new Exercise(1, 0, 4);

        assertEquals(4, exercise.squatAverage());
    }
}
