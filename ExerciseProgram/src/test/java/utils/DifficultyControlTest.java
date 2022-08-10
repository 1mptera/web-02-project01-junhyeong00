package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DifficultyControlTest {
    @Test
    void warmUp1() {
        DifficultyControl difficultyControl = new DifficultyControl();

        assertEquals(4, difficultyControl.warmUp1(10));
        assertEquals(4, difficultyControl.warmUp1(11));
    }

    @Test
    void warmUp2() {
        DifficultyControl difficultyControl = new DifficultyControl();

        assertEquals(6, difficultyControl.warmUp2(10));
        assertEquals(6, difficultyControl.warmUp2(11));
    }

    @Test
    void set() {
        DifficultyControl difficultyControl = new DifficultyControl();

        assertEquals(9, difficultyControl.set(9));
        assertEquals(11, difficultyControl.set(10));
        assertEquals(12, difficultyControl.set(11));
    }
}
