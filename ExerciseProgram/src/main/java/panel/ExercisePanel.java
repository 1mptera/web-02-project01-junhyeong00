package panel;

import utils.InputRecordAverage;
import utils.DifficultyControl;
import models.Exercise;
import utils.ExerciseRecordLoader;
import utils.ExerciseRecordWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExercisePanel extends JPanel {
    private final ExerciseRecordLoader exerciseRecordLoader;
    private Exercise exercise;
    private DifficultyControl difficultyControl;
    private InputRecordAverage inputRecordAverage;

    private JPanel subheadingPanel;
    private JPanel exerciseListPanel;
    private JPanel nextButtonPanel;
    private JPanel contentPanel;
    private JFrame frame;
    private JPanel exercisePanel;
    private JPanel exerciseCompletePanel;
    private JTextField numberOfSuccesses1;
    private JTextField numberOfSuccesses2;
    private JTextField numberOfSuccesses3;

    private int count = 0;
    private int typeCount = 0;
    private String type;
    private String number1;
    private String number2;
    private String number3;
    private int average;

    public ExercisePanel(JPanel contentPanel, JFrame frame) throws FileNotFoundException {
        difficultyControl = new DifficultyControl();
        exerciseRecordLoader = new ExerciseRecordLoader();
        inputRecordAverage = new InputRecordAverage();

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
        exercisePanel.setLayout(new GridLayout(4, 1));

        type = "푸쉬업";
        average = exercise.pushUpAverage();

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
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());

            JPanel exerciseGoalPanel = new JPanel();
            exerciseGoalPanel.setPreferredSize(new Dimension(200, 75));
            exerciseGoalPanel.setLayout(new GridLayout(3, 1));

            for (int i = 1; i <= 3; i += 1) {
                exerciseGoalPanel.add(new JLabel(" - " + type + " " + i + "세트: " + difficultyControl.set(average) + "개    성공 횟수 :"));

                panel.add(exerciseGoalPanel);
            }

            JPanel numberOfSuccessesPanel = new JPanel();
            numberOfSuccessesPanel.setPreferredSize(new Dimension(140, 75));
            numberOfSuccessesPanel.setLayout(new GridLayout(3, 1));

            numberOfSuccesses1 = new JTextField(10);
            numberOfSuccessesPanel.add(numberOfSuccesses1);

            numberOfSuccesses2 = new JTextField(10);
            numberOfSuccessesPanel.add(numberOfSuccesses2);

            numberOfSuccesses3 = new JTextField(10);
            numberOfSuccessesPanel.add(numberOfSuccesses3);

            panel.add(numberOfSuccessesPanel);

            exercisePanel.add(panel);

            typeCount += 1;
        }

        add(exercisePanel, BorderLayout.NORTH);
    }

    private void initNextButtonPanel() {
        nextButtonPanel = new JPanel();
        nextButtonPanel.setPreferredSize(new Dimension(350, 40));

        JButton button = new JButton("다음");
        button.addActionListener(e -> {
            count += 1;

            this.removeAll();

            if (count == 3 || count == 5 || count == 7) {
                number1 = numberOfSuccesses1.getText();
                number2 = numberOfSuccesses2.getText();
                number3 = numberOfSuccesses3.getText();

                inputRecordAverage.averageProcess(number1, number2, number3, type);
            }

            if (count <= 6) {
                initExercisePanel();

                initNextButtonPanel();
            }

            if (count == 7) {
                initExerciseCompletePanel();

                try {
                    ExerciseRecordWriter exerciseRecordWriter = new ExerciseRecordWriter(inputRecordAverage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }

            contentPanel.setVisible(false);
            contentPanel.setVisible(true);

            frame.setVisible(true);
        });

        nextButtonPanel.add(button);
        add(nextButtonPanel, BorderLayout.SOUTH);
    }

    private void initExerciseCompletePanel() {
        exerciseCompletePanel = new JPanel();
        exerciseCompletePanel.setLayout(new BorderLayout());

        exerciseCompletePanel.add(new JLabel("축하합니다! 오늘의 운동을 완료하셨습니다!"), BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));

        panel.add(previousRecordPanel());

        JPanel arrowPanel = new JPanel();
        arrowPanel.setLayout(new GridLayout(4, 1));
        arrowPanel.setPreferredSize(new Dimension(80, 90));

        for (int i = 0; i < 4; i += 1) {
            arrowPanel.add(new JLabel(" -->"));
        }
        panel.add(arrowPanel);

        JPanel currentRecordPanel = new JPanel();
        currentRecordPanel.setLayout(new GridLayout(4, 1));
        currentRecordPanel.setPreferredSize(new Dimension(110, 90));
        currentRecordPanel.add(new JLabel("현재 평균기록"));
        currentRecordPanel.add(new JLabel("푸쉬업: " + inputRecordAverage.pushUpAverage() + "개"));
        currentRecordPanel.add(new JLabel("풀업: " + inputRecordAverage.pullUpAverage() + "개"));
        currentRecordPanel.add(new JLabel("스쿼트: " + inputRecordAverage.squatAverage() + "개"));

        panel.add(currentRecordPanel);

        exerciseCompletePanel.add(panel);

        add(exerciseCompletePanel);
    }

    private JPanel previousRecordPanel() {
        JPanel previousRecordPanel = new JPanel();
        previousRecordPanel.setLayout(new GridLayout(4, 1));
        previousRecordPanel.setPreferredSize(new Dimension(110, 90));
        previousRecordPanel.add(new JLabel("이전 평균기록"));
        previousRecordPanel.add(new JLabel("푸쉬업: " + exercise.pushUpAverage() + "개"));
        previousRecordPanel.add(new JLabel("풀업: " + exercise.pullUpAverage() + "개"));
        previousRecordPanel.add(new JLabel("스쿼트: " + exercise.squatAverage() + "개"));

        return previousRecordPanel;
    }
}
