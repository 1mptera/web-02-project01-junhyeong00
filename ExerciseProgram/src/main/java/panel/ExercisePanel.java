package panel;

import javax.swing.*;
import java.awt.*;

public class ExercisePanel extends JPanel {

    private JPanel subheadingPanel;
    private JPanel exerciseListPanel;

    public ExercisePanel() {
        setLayout(new BorderLayout());

        initSubheadingPanel();

        initExerciseListPanel();
    }

    private void initSubheadingPanel() {
        subheadingPanel = new JPanel();
        subheadingPanel.setPreferredSize(new Dimension(350, 70));

        subheadingPanel.add(new JLabel("오늘의 운동 목표"));

        add(subheadingPanel, BorderLayout.NORTH);
    }

    private void initExerciseListPanel() {
        exerciseListPanel = new JPanel();
        exerciseListPanel.setPreferredSize(new Dimension(350, 310));
        exerciseListPanel.setLayout(new GridLayout(9, 1));

        exerciseListPanel.add(new JLabel(" - 푸쉬업 1세트: 30개"));
        exerciseListPanel.add(new JLabel(" - 푸쉬업 2세트: 30개"));
        exerciseListPanel.add(new JLabel(" - 푸쉬업 3세트: 30개"));
        exerciseListPanel.add(new JLabel(" - 풀업 1세트: 10개"));
        exerciseListPanel.add(new JLabel(" - 풀업 2세트: 10개"));
        exerciseListPanel.add(new JLabel(" - 풀업 3세트: 10개"));
        exerciseListPanel.add(new JLabel(" - 스쿼트 1세트: 40개"));
        exerciseListPanel.add(new JLabel(" - 스쿼트 2세트: 40개"));
        exerciseListPanel.add(new JLabel(" - 스쿼트 3세트: 40개"));

        add(exerciseListPanel);
    }
}
