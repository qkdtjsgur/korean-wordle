package ui;

import game.BaseballGame;
import game.RecordManager;
import model.Result;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTextArea resultArea;
    private JTextField inputField;
    private JLabel countLabel;

    private BaseballGame game;
    private RecordManager recordManager;

    public MainFrame() {

        recordManager = new RecordManager();

        setTitle("숫자 야구 게임");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeGame();

        setVisible(true);
    }

    private void initializeGame() {

        String[] options = {"3자리", "4자리"};

        int choice = JOptionPane.showOptionDialog(
                null,
                "난이도 선택",
                "게임 시작",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        int digit = choice == 1 ? 4 : 3;

        game = new BaseballGame(digit);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        countLabel = new JLabel("시도 횟수 : 0");

        JButton recordButton = new JButton("기록 보기");

        recordButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        recordManager.loadRecords()
                )
        );

        topPanel.add(countLabel);
        topPanel.add(recordButton);

        add(topPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(new JScrollPane(resultArea),
                BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        inputField = new JTextField(10);

        JButton submitButton =
                new JButton("입력");

        submitButton.addActionListener(e -> play());

        bottomPanel.add(inputField);
        bottomPanel.add(submitButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void play() {

        String input = inputField.getText();

        if (input.length() != game.getAnswer().length()) {

            JOptionPane.showMessageDialog(
                    this,
                    game.getAnswer().length()
                            + "자리 숫자를 입력하세요."
            );

            return;
        }

        Result result = game.play(input);

        countLabel.setText(
                "시도 횟수 : "
                        + game.getTryCount()
        );

        resultArea.append(
                input + " → "
                        + result.getStrike()
                        + " Strike "
                        + result.getBall()
                        + " Ball\n"
        );

        if (result.getStrike()
                == game.getAnswer().length()) {

            recordManager.saveRecord(
                    game.getTryCount()
            );

            int select =
                    JOptionPane.showConfirmDialog(
                            this,
                            "정답!\n"
                                    + game.getTryCount()
                                    + "번 만에 성공!\n"
                                    + "다시 시작할까요?"
                    );

            if (select == JOptionPane.YES_OPTION) {

                dispose();

                SwingUtilities.invokeLater(
                        MainFrame::new
                );

            } else {

                System.exit(0);
            }
        }

        inputField.setText("");
    }
}
