package panel;

import utils.DifficultyControl;
import models.Exercise;
import utils.ExerciseRecordLoader;
import utils.ExerciseRecordWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class ExercisePanel extends JPanel {
    private final ExerciseRecordLoader exerciseRecordLoader;
    private Exercise exercise;

    private JPanel subheadingPanel;
    private JPanel exerciseListPanel;
    private JPanel nextButtonPanel;
    private JPanel contentPanel;
    private JFrame frame;
    private JPanel exercisePanel;
    private JPanel exerciseCompletePanel;

    private int count = 0;
    private DifficultyControl difficultyControl;
    private int typeCount = 0;

    public ExercisePanel(JPanel contentPanel, JFrame frame) throws FileNotFoundException {
        difficultyControl = new DifficultyControl();
        exerciseRecordLoader = new ExerciseRecordLoader();

        exercise = exerciseRecordLoader.loadExerciseRecord();

        this.contentPanel = contentPanel;
        this.frame = frame;

        setLayout(new BorderLayout());

        initSubheadingPanel();

        initExerciseListPanel();

        initNextButtonPanel();
    }

    private void initSubheadingPanel() {
        subheadingPanel = new JPanel();
        subheadingPanel.setPreferredSize(new Dimension(350, 60));

        subheadingPanel.add(new JLabel("오늘의 운동 목표"));

        add(subheadingPanel, BorderLayout.NORTH);
    }

    private void initExerciseListPanel() {
        exerciseListPanel = new JPanel();
        exerciseListPanel.setPreferredSize(new Dimension(350, 330));
        exerciseListPanel.setLayout(new GridLayout(3, 1));


        for (int i = 0; i < 3; i += 1) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4, 1));

            String type = "푸쉬업";
            int average = exercise.pushUpAverage();

            if (i == 1) {
                type = "풀업";
                average = exercise.pullUpAverage();
            }

            if (i == 2) {
                type = "스쿼트";
                average = exercise.squatAverage();
            }

            panel.add(new JLabel(" - " + type + " 워밍업1: " + difficultyControl.warmUp1(average) + "개"));
            panel.add(new JLabel(" - " + type + " 워밍업2: " + difficultyControl.warmUp2(average) + "개"));
            panel.add(new JLabel(" - " + type + " 3세트: 각각 " + difficultyControl.set(average) + "개"));

            exerciseListPanel.add(panel);
        }

        add(exerciseListPanel);
    }

    private void initExercisePanel() {
        exercisePanel = new JPanel();
        exercisePanel.setPreferredSize(new Dimension(350, 390));
        exercisePanel.setLayout(new GridLayout(6, 1));

        String type = "푸쉬업";
        int average = exercise.pushUpAverage();

        if (typeCount == 1) {
            type = "풀업";
            average = exercise.pullUpAverage();
        }

        if (typeCount == 2) {
            type = "스쿼트";
            average = exercise.squatAverage();
        }

        if (count % 2 == 1) {
            exercisePanel.add(new JLabel(" - " + type + " 워밍업1: " + difficultyControl.warmUp1(average) + "개"));
            exercisePanel.add(new JLabel(" - " + type + " 워밍업2: " + difficultyControl.warmUp2(average) + "개"));
        }

        if (count % 2 == 0) {
            for (int i = 1; i <= 3; i += 1) {
                JPanel panel = new JPanel();

                panel.add(new JLabel(" - " + type + " " + i + "세트: " + difficultyControl.set(exercise.pushUpAverage()) + "개    실제 개수:"));
                panel.add(new JTextField(10));

                exercisePanel.add(panel);
            }

            typeCount += 1;
        }

        add(exercisePanel, BorderLayout.NORTH);
    }

    private void initExerciseCompletePanel() {
        exerciseCompletePanel = new JPanel();

        exerciseCompletePanel.add(new JLabel("축하합니다!"));
        exerciseCompletePanel.add(new JLabel("오늘의 운동을 완료하셨습니다"));

        add(exerciseCompletePanel);
    }

    private void initNextButtonPanel() {
        nextButtonPanel = new JPanel();
        nextButtonPanel.setPreferredSize(new Dimension(350, 40));

        JButton button = new JButton("다음");
        button.addActionListener(e -> {
            count += 1;

            this.removeAll();

            if (count <= 6) {
                initExercisePanel();

                initNextButtonPanel();
            }

            if (count > 6) {
                initExerciseCompletePanel();

                ExerciseRecordWriter.saveExerciseRecord();
            }

            contentPanel.setVisible(false);
            contentPanel.setVisible(true);

            frame.setVisible(true);
        });

        nextButtonPanel.add(button);
        add(nextButtonPanel, BorderLayout.SOUTH);
    }
}
