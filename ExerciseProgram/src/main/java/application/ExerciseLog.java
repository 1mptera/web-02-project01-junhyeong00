package application;

import models.ExerciseRecord;
import panel.DescriptionPanel;
import panel.ExerciseLogPanel;
import panel.ExercisePanel;
import panel.LevelTestPanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseLog {

    private JFrame frame;
    private JPanel contentPanel;
    private JPanel menuPanel;
    private JButton backButton;

    private List<ExerciseRecord> exercisesRecords = new ArrayList<>();

    public static void main(String[] args) {
        ExerciseLog application = new ExerciseLog();
        application.run();
    }

    public void run() {
        initFrame();

        initContentPanel();

        initMenuPanel();

        frame.setVisible(true);
    }

    public void initFrame() {
        frame = new JFrame("Exercise Log");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(350, 500);
        frame.setLocation(400, 100);
    }

    public void initMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(350, 70));
        menuPanel.setBackground(Color.GRAY);

        menuPanel.add(createTestButton());
        menuPanel.add(createExerciseStartButton());
        menuPanel.add(createExerciseLogButton());
        menuPanel.add(createDescriptionButton());

        frame.add(menuPanel);
    }

    private JButton createExerciseStartButton() {
        JButton button = new JButton("운동시작");
        button.addActionListener(e -> {
            JPanel exercisePanel = null;
            try {
                exercisePanel = new ExercisePanel(contentPanel, frame, exercisesRecords);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            createBackButton();

            updateContentPanel(exercisePanel);

            updateMenuPanel(backButton);
        });
        return button;
    }

    private JButton createTestButton() {
        JButton button = new JButton("테스트");
        button.addActionListener(e -> {
            LevelTestPanel levelTestPanel = new LevelTestPanel(contentPanel, frame);

            createBackButton();

            updateContentPanel(levelTestPanel);

            updateMenuPanel(backButton);
        });
        return button;
    }

    private JButton createExerciseLogButton() {
        JButton button = new JButton("운동 일지");
        button.addActionListener(e -> {
            ExerciseLogPanel exerciseLogPanel = null;
            try {
                exerciseLogPanel = new ExerciseLogPanel(exercisesRecords);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            createBackButton();

            updateContentPanel(exerciseLogPanel);

            updateMenuPanel(backButton);
        });
        return button;
    }

    private JButton createDescriptionButton() {
        JButton button = new JButton("도움말");
        button.setPreferredSize(new Dimension(45,20));
        button.addActionListener(e -> {
            DescriptionPanel descriptionPanel = new DescriptionPanel();

            createBackButton();

            updateContentPanel(descriptionPanel);

            updateMenuPanel(backButton);
        });
        return button;
    }

    private void createBackButton() {
        backButton = new JButton("돌아가기");
        backButton.addActionListener(e1 -> {
            contentPanel.removeAll();
            menuPanel.removeAll();

            menuPanel.add(createTestButton());
            menuPanel.add(createExerciseStartButton());
            menuPanel.add(createExerciseLogButton());
            menuPanel.add(createDescriptionButton());

            contentPanel.setVisible(false);
            contentPanel.setVisible(true);

            menuPanel.setVisible(false);
            menuPanel.setVisible(true);

            frame.setVisible(true);
        });
    }

    public void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(350, 430));

        frame.add(contentPanel, BorderLayout.NORTH);
    }

    public void updateContentPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);

        showContentPanel();
    }

    public void showContentPanel() {
        contentPanel.setVisible(false);
        contentPanel.setVisible(true);

        frame.setVisible(true);
    }

    public void updateMenuPanel(JButton button) {
        menuPanel.removeAll();
        menuPanel.add(button);

        menuPanel.setVisible(false);
        menuPanel.setVisible(true);

        frame.setVisible(true);
    }
}
