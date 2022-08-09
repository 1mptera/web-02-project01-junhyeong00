package panel;

import utils.ExerciseRecordLoader;

import javax.swing.*;
import java.awt.*;

public class ExercisePanel extends JPanel {
    private final ExerciseRecordLoader exerciseRecordLoader;

    private JPanel subheadingPanel;
    private JPanel exerciseListPanel;
    private JPanel nextButtonPanel;
    private JPanel contentPanel;
    private JFrame frame;
    private JPanel exercisePanel;
    private JPanel exerciseCompletePanel;

    private int count = 0;

    public ExercisePanel(JPanel contentPanel, JFrame frame) {
        exerciseRecordLoader = new ExerciseRecordLoader();

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

        exerciseListPanel.add(new JLabel(" - 푸쉬업 워밍업1: 12개"));
        exerciseListPanel.add(new JLabel(" - 푸쉬업 워밍업2: 18개"));
        exerciseListPanel.add(new JLabel(" - 푸쉬업 3세트: 각각 30개"));
        exerciseListPanel.add(new JLabel(" - 풀업 워밍업1: 4개"));
        exerciseListPanel.add(new JLabel(" - 풀업 워밍업2: 6개"));
        exerciseListPanel.add(new JLabel(" - 풀업 3세트: 각각 10개"));
        exerciseListPanel.add(new JLabel(" - 스쿼트 워밍업1: 16개"));
        exerciseListPanel.add(new JLabel(" - 스쿼트 워밍업2: 24개"));
        exerciseListPanel.add(new JLabel(" - 스쿼트 3세트: 각각 40개"));

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
