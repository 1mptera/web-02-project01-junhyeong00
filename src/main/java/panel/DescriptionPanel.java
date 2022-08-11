package panel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DescriptionPanel extends JPanel {
    public DescriptionPanel() {
        setLayout(new GridLayout(5, 1, 2, 30));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

        add(programDescriptionPanel());

        add(exerciseDescriptionPanel());

        add(orderOfProgressPanel());
    }

    private JPanel programDescriptionPanel() {
        JPanel programDescriptionPanel = new JPanel();
        programDescriptionPanel.setLayout(new GridLayout(5, 1));
        programDescriptionPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2)));

        programDescriptionPanel.add(new JLabel("-프로그램 구성-", SwingConstants.CENTER));
        programDescriptionPanel.add(new JLabel("본 프로그램은 시행자의 근력과 지구력을 측정하는 '테스트'와"));
        programDescriptionPanel.add(new JLabel("본격적인 운동을 진행하는 '운동시작'으로 나뉘어져있습니다."));
        programDescriptionPanel.add(new JLabel("'테스트'로 첫 운동을 시작하고, 난이도가 조절되면 본격적인"));
        programDescriptionPanel.add(new JLabel("운동을 시작합니다."));

        return programDescriptionPanel;
    }

    private JPanel exerciseDescriptionPanel() {
        JPanel exerciseDescriptionPanel = new JPanel();
        exerciseDescriptionPanel.setLayout(new GridLayout(5, 1));
        exerciseDescriptionPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2)));

        exerciseDescriptionPanel.add(new JLabel("-운동 구성-", SwingConstants.CENTER));
        exerciseDescriptionPanel.add(new JLabel("푸쉬업(가슴), 풀업(등), 스쿼트(하체)로 구성되어 있는 전신 프로그램", SwingConstants.CENTER));
        exerciseDescriptionPanel.add(new JLabel("3가지 운동이 워밍업 2세트, 본 운동 3세트로 구성", SwingConstants.CENTER));
        exerciseDescriptionPanel.add(new JLabel("워밍업 세트: 본 운동전 가벼운 강도로 몸을 풀어주는 세트", SwingConstants.CENTER));
        exerciseDescriptionPanel.add(new JLabel("본 세트: 점진적으로 강도를 올리며, 본격전으로 운동을 하는 세트", SwingConstants.CENTER));

        return exerciseDescriptionPanel;
    }

    private JPanel orderOfProgressPanel() {
        JPanel orderOfProgressPanel = new JPanel();
        orderOfProgressPanel.setLayout(new GridLayout(4, 1));
        orderOfProgressPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2)));

        orderOfProgressPanel.add(new JLabel("-진행방법-", SwingConstants.CENTER));
        orderOfProgressPanel.add(new JLabel("순차적으로 운동을 진행한다", SwingConstants.CENTER));
        orderOfProgressPanel.add(new JLabel("각 운동의 모든 세트를 완료하고 다음 운동으로 넘어간다", SwingConstants.CENTER));
        orderOfProgressPanel.add(new JLabel("마지막 세트는 실패지점까지 반복한다", SwingConstants.CENTER));

        return orderOfProgressPanel;
    }
}
