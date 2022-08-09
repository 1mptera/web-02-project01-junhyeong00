package panel;

import models.Exercise;
import utils.ExerciseRecordLoader;

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

    public ExercisePanel(JPanel contentPanel, JFrame frame) throws FileNotFoundException {
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
        subheadingPanel.setPreferredSize(new Dimension(350, 70));

        subheadingPanel.add(new JLabel("오늘의 운동 목표"));

        add(subheadingPanel, BorderLayout.NORTH);
    }

    private void initExerciseListPanel() {
        exerciseListPanel = new JPanel();
        exerciseListPanel.setPreferredSize(new Dimension(350, 320));
        exerciseListPanel.setLayout(new GridLayout(9, 1));

        exerciseListPanel.add(new JLabel(" - 푸쉬업 워밍업1: " + exercise.pushUpAverage() * 0.4 + "개"));
        exerciseListPanel.add(new JLabel(" - 푸쉬업 워밍업2: " + exercise.pushUpAverage() * 0.6 + "개"));
        exerciseListPanel.add(new JLabel(" - 푸쉬업 3세트: 각각 " + exercise.pushUpAverage() * 1.1 + "개"));
        exerciseListPanel.add(new JLabel(" - 풀업 워밍업1: " + exercise.pullUpAverage() * 0.4 + "개"));
        exerciseListPanel.add(new JLabel(" - 풀업 워밍업2: " + exercise.pullUpAverage() * 0.6 + "개"));
        exerciseListPanel.add(new JLabel(" - 풀업 3세트: 각각 " + exercise.pullUpAverage() * 1.1 + "개"));
        exerciseListPanel.add(new JLabel(" - 스쿼트 워밍업1: " + exercise.squatAverage() * 0.4 + "개"));
        exerciseListPanel.add(new JLabel(" - 스쿼트 워밍업2: " + exercise.squatAverage() * 0.6 + "개"));
        exerciseListPanel.add(new JLabel(" - 스쿼트 3세트: 각각 " + exercise.squatAverage() * 1.1 + "개"));

        add(exerciseListPanel);
    }

    private void initExercisePanel() {
        exercisePanel = new JPanel();
        exercisePanel.setPreferredSize(new Dimension(350, 100));

        exercisePanel.add(new JLabel("푸쉬업 위밍업"));

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

            if (count < 9) {
                initExercisePanel();

                initNextButtonPanel();
            }

            if (count == 9) {
                initExerciseCompletePanel();
            }

            contentPanel.setVisible(false);
            contentPanel.setVisible(true);

            frame.setVisible(true);
        });

        nextButtonPanel.add(button);
        add(nextButtonPanel, BorderLayout.PAGE_END);
    }
}
