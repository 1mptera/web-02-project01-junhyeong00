package panel;

import models.ExerciseRecord;
import utils.ExerciseRecordLoader;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;

public class ExerciseLogPanel extends JPanel {

    private List<ExerciseRecord> exerciseRecords;

    public ExerciseLogPanel(List<ExerciseRecord> exerciseRecords) throws FileNotFoundException {
        ExerciseRecordLoader exerciseRecordLoader = new ExerciseRecordLoader();

        this.exerciseRecords = exerciseRecords;

        this.exerciseRecords = exerciseRecordLoader.loadExerciseLog();

        setLayout(new GridLayout(this.exerciseRecords.size(), 1));

        int day = 1;

        for (ExerciseRecord exercisesRecord : this.exerciseRecords) {
            add(new JLabel(day + "일차 - " + exercisesRecord.toString()));

            day += 1;
        }

    }
}
