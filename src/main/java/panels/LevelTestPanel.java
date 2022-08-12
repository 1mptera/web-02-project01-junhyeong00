package panels;

import utils.ExerciseRecordWriter;
import utils.InputRecordAverage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LevelTestPanel extends JPanel {
    private final JFrame frame;
    private final JPanel contentPanel;
    private JPanel testPanel;
    private JPanel testButtonPanel;
    private JPanel exerciseGoalPanel;
    private JPanel testResultPanel;
    private JPanel numberOfSuccessesPanel;
    private JPanel testDescriptionPanel;
    private JPanel exerciseRepsInputPanel;
    private JTextField numberOfSuccesses1;
    private JTextField numberOfSuccesses2;
    private JTextField numberOfSuccesses3;

    private int typeCount = 0;
    private String testButtonName = "테스트 시작";
    private String firstSetNumber;
    private String secondSetNumber;
    private String thirdSetNumber;
    private String type;

    private InputRecordAverage inputRecordAverage;
    private ExerciseRecordWriter exerciseRecordWriter;

    public LevelTestPanel(JPanel contentPanel, JFrame frame) {
        inputRecordAverage = new InputRecordAverage();
        exerciseRecordWriter = new ExerciseRecordWriter();

        this.contentPanel = contentPanel;
        this.frame = frame;

        setLayout(new BorderLayout());
        setOpaque(false);

        add(testDescriptionPanel());

        initTestButtonPanel();
    }

    private JPanel testDescriptionPanel() {
        testDescriptionPanel = new JPanel();
        testDescriptionPanel.setLayout(new GridLayout(7, 1));
        testDescriptionPanel.setPreferredSize(new Dimension(350, 390));

        testDescriptionLabel();

        return testDescriptionPanel;
    }

    private void testDescriptionLabel() {
        testDescriptionPanel.add(new JLabel("TEST 진행 방법", SwingConstants.CENTER));
        testDescriptionPanel.add(new JLabel(" 운동을 순서대로 진행합니다"));
        testDescriptionPanel.add(new JLabel(" 모든 운동은 매 세트당 실패지점까지 반복합니다"));
        testDescriptionPanel.add(new JLabel(" *실패지점: 정확한 자세를 더 이상 유지하지 못하는 지점"));
        testDescriptionPanel.add(new JLabel(" 세트당 쉬는 시간은 1분입니다"));
        testDescriptionPanel.add(new JLabel(" 매 세트 반복횟수를 기록하고 테스트완료 버튼을 눌러줍니다"));
    }

    private JPanel testPanel() {
        testPanel = new JPanel();
        testPanel.setPreferredSize(new Dimension(350, 390));
        testPanel.setLayout(new GridLayout(5, 1));

        exerciseTypeProcess();

        testPanel.add(new JLabel(testTypeTitle(), SwingConstants.CENTER));

        testPanel.add(exerciseRepsInputPanel());

        typeCount += 1;

        add(testPanel, BorderLayout.NORTH);

        if (typeCount <= 3) {
            initTestButtonPanel();
        }

        return testPanel;
    }

    private JPanel exerciseRepsInputPanel() {
        exerciseRepsInputPanel = new JPanel();
        exerciseRepsInputPanel.setLayout(new FlowLayout());

        exerciseGoalPanel();

        exerciseRepsInputPanel.add(NumberOfSuccessesInputPanel());

        return exerciseRepsInputPanel;
    }

    private void exerciseGoalPanel() {
        exerciseGoalPanel = new JPanel();
        exerciseGoalPanel.setPreferredSize(new Dimension(200, 75));
        exerciseGoalPanel.setLayout(new GridLayout(3, 1));

        for (int i = 1; i <= 3; i += 1) {
            exerciseGoalPanel.add(new JLabel(exerciseRepsInputRequest(i), SwingConstants.CENTER));

            exerciseRepsInputPanel.add(exerciseGoalPanel);
        }
    }

    private String exerciseRepsInputRequest(int i) {
        return " - " + type + " " + i
                + "세트:          성공횟수: ";
    }

    private String testTypeTitle() {
        return "TEST" + " - " + type;
    }

    private void exerciseTypeProcess() {
        switch (typeCount) {
            case 1 -> type = "풀업";
            case 2 -> type = "스쿼트";
            default -> type = "푸쉬업";
        }
    }

    private JPanel NumberOfSuccessesInputPanel() {
        numberOfSuccessesPanel = new JPanel();
        numberOfSuccessesPanel.setPreferredSize(new Dimension(140, 75));
        numberOfSuccessesPanel.setLayout(new GridLayout(3, 1));

        numberOfSuccesses1 = new JTextField(10);
        numberOfSuccessesPanel.add(numberOfSuccesses1);

        numberOfSuccesses2 = new JTextField(10);
        numberOfSuccessesPanel.add(numberOfSuccesses2);

        numberOfSuccesses3 = new JTextField(10);
        numberOfSuccessesPanel.add(numberOfSuccesses3);

        return numberOfSuccessesPanel;
    }

    private void initTestButtonPanel() {
        testButtonPanel = new JPanel();
        testButtonPanel.setPreferredSize(new Dimension(350, 40));

        JButton button = new JButton(testButtonName);
        button.addActionListener(e -> {
            testScreenProcess();
        });

        testButtonPanel.add(button);
        add(testButtonPanel, BorderLayout.SOUTH);
    }

    private void testScreenProcess() {
        this.removeAll();

        if (typeCount > 0) {
            getExerciseSuccessReps();
        }

        testButtonName = "다음 테스트";

        if (typeCount == 3) {
            testButtonName = "테스트 완료";

            initExerciseResultPanel();

            saveRecords();
        }

        if (typeCount < 3) {
            add(testPanel());
        }

        updateContentPanel();
    }

    private void getExerciseSuccessReps() {
        firstSetNumber = numberOfSuccesses1.getText();
        secondSetNumber = numberOfSuccesses2.getText();
        thirdSetNumber = numberOfSuccesses3.getText();

        inputRecordAverage.averageProcess(
                firstSetNumber, secondSetNumber, thirdSetNumber, type);
    }

    private void updateContentPanel() {
        contentPanel.setVisible(false);
        contentPanel.setVisible(true);

        frame.setVisible(true);
    }

    private void saveRecords() {
        try {
            exerciseRecordWriter.saveExerciseInformation(inputRecordAverage);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void initExerciseResultPanel() {
        testResultPanel = new JPanel();
        testResultPanel.setLayout(new GridLayout(7, 1));
        testResultPanel.setPreferredSize(new Dimension(350, 390));

        testResultLabel();

        add(testResultPanel);
    }

    private void testResultLabel() {
        testResultPanel.add(new JLabel("테스트 평균기록", SwingConstants.CENTER));
        testResultPanel.add(new JLabel(" 푸쉬업: " + inputRecordAverage.pushUpAverage()
                + "개", SwingConstants.CENTER));
        testResultPanel.add(new JLabel(" 풀업: " + inputRecordAverage.pullUpAverage()
                + "개", SwingConstants.CENTER));
        testResultPanel.add(new JLabel(" 스쿼트: " + inputRecordAverage.squatAverage()
                + "개", SwingConstants.CENTER));
    }
}
