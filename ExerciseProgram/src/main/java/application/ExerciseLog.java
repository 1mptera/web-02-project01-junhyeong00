package application;

import panel.ExercisePanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class ExerciseLog {

    private JFrame frame;
    private JPanel contentPanel;
    private JPanel menuPanel;
    private JButton backButton;

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
        frame = new JFrame("운동");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(350, 500);
        frame.setLocation(400, 100);
    }

    public void initMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(350, 70));
        menuPanel.setBackground(Color.GRAY);

        menuPanel.add(createExerciseStartButton());

        frame.add(menuPanel);
    }

    private JButton createExerciseStartButton() {
        JButton button = new JButton("운동시작");
        button.addActionListener(e -> {
            JPanel exercisePanel = null;
            try {
                exercisePanel = new ExercisePanel(contentPanel, frame);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            createBackButton();

            updateContentPanel(exercisePanel);

            updateMenuPanel(backButton);

        });
        return button;
    }

    private void createBackButton() {
        backButton = new JButton("돌아가기");
        backButton.addActionListener(e1 -> {
            contentPanel.removeAll();
            menuPanel.removeAll();

            menuPanel.add(createExerciseStartButton());

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
//        contentPanel.setBackground(Color.blue);

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