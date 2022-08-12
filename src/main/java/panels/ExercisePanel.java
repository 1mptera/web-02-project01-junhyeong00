package panels;

import models.ExerciseRecord;
import utils.InputRecordAverage;
import utils.DifficultyControl;
import models.Exercise;
import utils.ExerciseRecordLoader;
import utils.ExerciseRecordWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ExercisePanel extends JPanel {
    private final ExerciseRecordLoader exerciseRecordLoader;
    private final ExerciseRecord exerciseRecord;
    private Exercise exercise;
    private DifficultyControl difficultyControl;
    private InputRecordAverage inputRecordAverage;
    private ExerciseRecordWriter exerciseRecordWriter;

    private JPanel currentRecordPanel;
    private JPanel arrowPanel;
    private JPanel subheadingPanel;
    private JPanel exerciseListPanel;
    private JPanel nextButtonPanel;
    private JPanel contentPanel;
    private JPanel compareRecordsPanel;
    private JPanel numberOfSuccessesPanel;
    private JPanel exerciseGoalPanel;
    private JPanel exercisePanel;
    private JPanel exerciseCompletePanel;
    private JPanel exerciseRepsInputPanel;
    private JPanel previousRecordPanel;
    private JTextField numberOfSuccesses1;
    private JTextField numberOfSuccesses2;
    private JTextField numberOfSuccesses3;
    private JFrame frame;

    private int count = 0;
    private int typeCount = 0;
    private int average;
    private String type;
    private String firstSetNumber;
    private String secondSetNumber;
    private String thirdSetNumber;
    private String nextButtonName = "다음";

    private List<ExerciseRecord> exerciseRecords;

    public ExercisePanel(JPanel contentPanel, JFrame frame, List<ExerciseRecord> exerciseRecords)
            throws FileNotFoundException {
        difficultyControl = new DifficultyControl();
        exerciseRecordLoader = new ExerciseRecordLoader();
        exerciseRecordWriter = new ExerciseRecordWriter();
        inputRecordAverage = new InputRecordAverage();
        exerciseRecord = new ExerciseRecord(
                inputRecordAverage.pushUpAverage(),
                inputRecordAverage.pullUpAverage(),
                inputRecordAverage.squatAverage());

        exercise = exerciseRecordLoader.loadExerciseRecord();

        this.exerciseRecords = exerciseRecords;
        this.contentPanel = contentPanel;
        this.frame = frame;

        setLayout(new BorderLayout());
        setOpaque(false);

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

        ListOfExerciseByTypePanel();

        add(exerciseListPanel);
    }

    private void ListOfExerciseByTypePanel() {
        for (int i = 0; i < 3; i += 1) {
            JPanel ListOfExerciseByTypePanel = new JPanel();
            ListOfExerciseByTypePanel.setLayout(new GridLayout(4, 1));

            exerciseTypeProcess(i);

            ListOfExerciseByTypePanel.add(new JLabel(exerciseRepsGuide(" 워밍업1: ", difficultyControl.warmUp1(average))));
            ListOfExerciseByTypePanel.add(new JLabel(exerciseRepsGuide(" 워밍업2: ", difficultyControl.warmUp2(average))));
            ListOfExerciseByTypePanel.add(new JLabel(exerciseRepsGuide(" 3세트: 각각 ", difficultyControl.set(average))));

            exerciseListPanel.add(ListOfExerciseByTypePanel);
        }
    }

    private String exerciseRepsGuide(String x, int difficultyControl) {
        return " - " + type + x
                + difficultyControl + "개";
    }

    private void initExercisePanel() {
        exercisePanel = new JPanel();
        exercisePanel.setPreferredSize(new Dimension(350, 390));
        exercisePanel.setLayout(new GridLayout(5, 1));

        exerciseTypeProcess(typeCount);

        printSetProcess();
    }

    private void printSetProcess() {
        switch (count % 2) {
            case 1 -> printWarmUpSets();
            case 0 -> {
                printWorkSets();

                typeCount += 1;
            }
        }
    }

    private void exerciseTypeProcess(int typeCount) {
        switch (typeCount) {
            case 1 -> {
                type = "풀업";
                average = exercise.pullUpAverage();
            }

            case 2 -> {
                type = "스쿼트";
                average = exercise.squatAverage();
            }

            default -> {
                type = "푸쉬업";
                average = exercise.pushUpAverage();
            }
        }
    }

    private void printWorkSets() {
        exercisePanel.add(new JLabel(type + " 본 세트", SwingConstants.CENTER));

        exercisePanel.add(exerciseRepsInputPanel());

        add(exercisePanel, BorderLayout.NORTH);

        exercisePanel.add(new JLabel(" # 목표 개수만큼 운동을 한 후, 성공 개수를 적습니다"
                , SwingConstants.CENTER));
        exercisePanel.add(new JLabel(" 단, 마지막 세트는 실패지점까지 반복합니다"
                , SwingConstants.CENTER));
        exercisePanel.add(new JLabel(" # 세트간 쉬는 시간은 1분입니다"
                , SwingConstants.CENTER));
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
            exerciseGoalPanel.add(new JLabel(exerciseRepsGuideAndInput(i)));

            exerciseRepsInputPanel.add(exerciseGoalPanel);
        }
    }

    private String exerciseRepsGuideAndInput(int i) {
        return " - " + type + " " + i
                + "세트: " + difficultyControl.set(average) + "개    성공 횟수 :";
    }

    private void printWarmUpSets() {
        exercisePanel.add(new JLabel(type + " 워밍업 세트", SwingConstants.CENTER));
        exercisePanel.add(new JLabel(exerciseRepsGuide(" 워밍업 1세트: ", difficultyControl.warmUp1(average))));
        exercisePanel.add(new JLabel(exerciseRepsGuide(" 워밍업 2세트: ", difficultyControl.warmUp2(average))));
        exercisePanel.add(new JLabel(" # 본 운동 전 가벼운 강도의 운동으로 몸을 풀어줍니다"));
        exercisePanel.add(new JLabel(" # 세트간 쉬는 시간은 1분입니다"));

        add(exercisePanel, BorderLayout.NORTH);
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

    private void initNextButtonPanel() {
        nextButtonPanel = new JPanel();
        nextButtonPanel.setPreferredSize(new Dimension(350, 40));

        JButton button = new JButton(nextButtonName);
        button.addActionListener(e -> {

            nextScreenProcess();
        });

        nextButtonPanel.add(button);
        add(nextButtonPanel, BorderLayout.SOUTH);
    }

    private void nextScreenProcess() {
        count += 1;

        avoidEnteringBlanks();

        this.removeAll();

        if (count == 3 || count == 5 || count == 7) {
            getExerciseSuccessReps();
        }

        if (count <= 6) {
            initExercisePanel();

            initNextButtonPanel();
        }

        if (count == 7) {
            initExerciseCompletePanel();

            exerciseRecords.add(exerciseRecord);

            saveRecords();
        }

        updateContentPanel();
    }

    private void updateContentPanel() {
        contentPanel.setVisible(false);
        contentPanel.setVisible(true);

        frame.setVisible(true);
    }

    private void getExerciseSuccessReps() {
        nextButtonName = "다음";

        firstSetNumber = numberOfSuccesses1.getText();
        secondSetNumber = numberOfSuccesses2.getText();
        thirdSetNumber = numberOfSuccesses3.getText();

        inputRecordAverage.averageProcess(firstSetNumber, secondSetNumber, thirdSetNumber, type);
    }

    private void saveRecords() {
        try {
            exerciseRecordWriter.saveExerciseInformation(inputRecordAverage);

            exerciseRecordWriter.saveExerciseRecord(exerciseRecords);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void avoidEnteringBlanks() {
        if (count == 3 || count == 5 || count == 7) {
            if (numberOfSuccesses1.getText().equals("") ||
                    numberOfSuccesses2.getText().equals("") ||
                    numberOfSuccesses3.getText().equals("")) {
                count -= 1;
                typeCount -= 1;
                nextButtonName = "다음 - 모두 입력해주세요!";
            }
        }
    }

    private void initExerciseCompletePanel() {
        exerciseCompletePanel = new JPanel();
        exerciseCompletePanel.setLayout(new BorderLayout());

        exerciseCompletePanel.add(new JLabel("축하합니다! 오늘의 운동을 완료하셨습니다!"), BorderLayout.NORTH);

        exerciseCompletePanel.add(compareRecordsPanel());

        add(exerciseCompletePanel);
    }

    private JPanel compareRecordsPanel() {
        compareRecordsPanel = new JPanel();
        compareRecordsPanel.setLayout(new GridLayout(1, 3));

        compareRecordsPanel.add(previousRecordPanel());

        compareRecordsPanel.add(arrowPanel());

        compareRecordsPanel.add(currentRecordPanel());

        return compareRecordsPanel;
    }

    private JPanel arrowPanel() {
        arrowPanel = new JPanel();
        arrowPanel.setLayout(new GridLayout(4, 1));
        arrowPanel.setPreferredSize(new Dimension(80, 90));

        for (int i = 0; i < 4; i += 1) {
            arrowPanel.add(new JLabel(" -->"));
        }

        return arrowPanel;
    }

    private JPanel currentRecordPanel() {
        currentRecordPanel = new JPanel();
        currentRecordPanel.setLayout(new GridLayout(4, 1));
        currentRecordPanel.setPreferredSize(new Dimension(110, 90));

        currentRecordPanel.add(new JLabel("현재 평균기록"));
        currentRecordPanel.add(new JLabel("푸쉬업: " + inputRecordAverage.pushUpAverage() + "개"));
        currentRecordPanel.add(new JLabel("풀업: " + inputRecordAverage.pullUpAverage() + "개"));
        currentRecordPanel.add(new JLabel("스쿼트: " + inputRecordAverage.squatAverage() + "개"));

        return currentRecordPanel;
    }

    private JPanel previousRecordPanel() {
        previousRecordPanel = new JPanel();
        previousRecordPanel.setLayout(new GridLayout(4, 1));
        previousRecordPanel.setPreferredSize(new Dimension(110, 90));

        previousRecordPanel.add(new JLabel("이전 평균기록"));
        previousRecordPanel.add(new JLabel("푸쉬업: " + exercise.pushUpAverage() + "개"));
        previousRecordPanel.add(new JLabel("풀업: " + exercise.pullUpAverage() + "개"));
        previousRecordPanel.add(new JLabel("스쿼트: " + exercise.squatAverage() + "개"));

        return previousRecordPanel;
    }
}
