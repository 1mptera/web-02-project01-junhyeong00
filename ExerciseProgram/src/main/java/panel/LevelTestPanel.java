package panel;

import utils.ExerciseRecordWriter;
import utils.InputRecordAverage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LevelTestPanel extends JPanel {
    private JPanel testButtonPanel;
    private final JPanel contentPanel;
    private final JFrame frame;
    private JPanel testCompletePanel;
    private String type;
    private JTextField numberOfSuccesses1;
    private JTextField numberOfSuccesses2;
    private JTextField numberOfSuccesses3;

    private int typeCount = 0;
    private String testButtonName = "테스트 시작";
    private String firstSetNumber;
    private String secondSetNumber;
    private String thirdSetNumber;

    private InputRecordAverage inputRecordAverage;

    public LevelTestPanel(JPanel contentPanel, JFrame frame) {
        inputRecordAverage = new InputRecordAverage();

        this.contentPanel = contentPanel;
        this.frame = frame;

        setLayout(new BorderLayout());

        add(testDescriptionPanel());

        initTestButtonPanel();
    }

    private JPanel testDescriptionPanel() {
        JPanel testDescriptionPanel = new JPanel();
        testDescriptionPanel.setLayout(new GridLayout(7, 1));
        testDescriptionPanel.setPreferredSize(new Dimension(350, 390));

        testDescriptionPanel.add(new JLabel("TEST 진행 방법", SwingConstants.CENTER));
        testDescriptionPanel.add(new JLabel(" 운동을 순서대로 진행합니다"));
        testDescriptionPanel.add(new JLabel(" 모든 운동은 매 세트당 실패지점까지 반복합니다"));
        testDescriptionPanel.add(new JLabel(" *실패지점: 정확한 자세를 더 이상 유지하지 못하는 지점"));
        testDescriptionPanel.add(new JLabel(" 세트당 쉬는 시간은 1분입니다"));
        testDescriptionPanel.add(new JLabel(" 매 세트 반복횟수를 기록하고 테스트완료 버튼을 눌러줍니다"));

        return testDescriptionPanel;
    }

    private JPanel testPanel() {
        JPanel testPanel = new JPanel();
        testPanel.setPreferredSize(new Dimension(350, 390));
        testPanel.setLayout(new GridLayout(5, 1));

        type = "푸쉬업";

        if (typeCount == 1) {
            type = "풀업";
        }

        if (typeCount == 2) {
            type = "스쿼트";
        }

        testPanel.add(new JLabel("TEST" + " - " + type, SwingConstants.CENTER));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JPanel exerciseGoalPanel = new JPanel();
        exerciseGoalPanel.setPreferredSize(new Dimension(200, 75));
        exerciseGoalPanel.setLayout(new GridLayout(3, 1));

        for (int i = 1; i <= 3; i += 1) {
            exerciseGoalPanel.add(new JLabel(" - " + type + " " + i + "세트:          성공횟수: ", SwingConstants.CENTER));

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

        testPanel.add(panel);

        typeCount += 1;

        add(testPanel, BorderLayout.NORTH);

        if (typeCount <= 3) {
            initTestButtonPanel();
        }

        return testPanel;
    }

    private void initTestButtonPanel() {
        testButtonPanel = new JPanel();
        testButtonPanel.setPreferredSize(new Dimension(350, 40));

        JButton button = new JButton(testButtonName);
        button.addActionListener(e -> {
            if (typeCount > 0) {
                firstSetNumber = numberOfSuccesses1.getText();
                secondSetNumber = numberOfSuccesses2.getText();
                thirdSetNumber = numberOfSuccesses3.getText();

                inputRecordAverage.averageProcess(firstSetNumber, secondSetNumber, thirdSetNumber, type);
            }

            this.removeAll();

            testButtonName = "다음 테스트";

            if (typeCount == 3) {
                testButtonName = "테스트 완료";

                initExerciseCompletePanel();

                try {
                    ExerciseRecordWriter exerciseRecordWriter = new ExerciseRecordWriter(inputRecordAverage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if (typeCount < 3) {
                add(testPanel());
            }

            contentPanel.setVisible(false);
            contentPanel.setVisible(true);

            frame.setVisible(true);
        });

        testButtonPanel.add(button);
        add(testButtonPanel, BorderLayout.SOUTH);
    }

    private void initExerciseCompletePanel() {
        testCompletePanel = new JPanel();
        testCompletePanel.setLayout(new GridLayout(7, 1));
        testCompletePanel.setPreferredSize(new Dimension(350, 390));

        testCompletePanel.add(new JLabel("테스트 평균기록", SwingConstants.CENTER));
        testCompletePanel.add(new JLabel(" 푸쉬업: " + inputRecordAverage.pushUpAverage() + "개", SwingConstants.CENTER));
        testCompletePanel.add(new JLabel(" 풀업: " + inputRecordAverage.pullUpAverage() + "개", SwingConstants.CENTER));
        testCompletePanel.add(new JLabel(" 스쿼트: " + inputRecordAverage.squatAverage() + "개", SwingConstants.CENTER));

        add(testCompletePanel);
    }
}
