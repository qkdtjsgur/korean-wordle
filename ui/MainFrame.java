package ui;

import game.BaseballGame;
import model.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class MainFrame extends JFrame {
    private BaseballGame game;
    private JTextField inputField;
    private JTextArea logArea;
    private JButton submitButton;

    public MainFrame() {
        game = new BaseballGame();
        
        // 창 기본 설정
        setTitle("숫자야구 퀴즈");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 게임 기록이 표시될 텍스트 구역
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);

        // 하단 입력 구역
        JPanel bottomPanel = new JPanel();
        inputField = new JTextField(10);
        inputField.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
        submitButton = new JButton("입력");

        bottomPanel.add(new JLabel("숫자 3자리:"));
        bottomPanel.add(inputField);
        bottomPanel.add(submitButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // 버튼 클릭 및 엔터키 이벤트 설정
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        };
        submitButton.addActionListener(actionListener);
        inputField.addActionListener(actionListener);

        logArea.append("⚾ 게임 시작! 1~9까지 서로 다른 3자리 숫자를 맞춰보세요.\n");
        logArea.append("=========================================\n");
    }

    private void processGuess() {
        String input = inputField.getText().trim();
        inputField.setText("");

        // 입력값 검증 (숫자인지, 3자리인지, 중복이 없는지)
        if (input.length() != 3 || !input.matches("^[1-9]{3}$") || input.chars().distinct().count() != 3) {
            JOptionPane.showMessageDialog(this, "1~9까지의 '서로 다른' 3자리 숫자를 입력해주세요.");
            return;
        }

        Result result = game.guess(input);
        int attempts = game.getAttempts();

        if (result.isWin()) {
            logArea.append(attempts + "회: [" + input + "] -> 🎉 3 스트라이크! 정답!\n");
            saveRecord(attempts); // records.txt에 저장
            JOptionPane.showMessageDialog(this, attempts + "번 만에 정답을 맞췄습니다!\n새 게임을 시작합니다.");
            game.generateAnswer();
            logArea.append("\n⚾ 새 게임이 시작되었습니다!\n");
        } else if (result.isOut()) {
            logArea.append(attempts + "회: [" + input + "] -> 아웃!\n");
        } else {
            logArea.append(attempts + "회: [" + input + "] -> " + result.getStrikes() + "S " + result.getBalls() + "B\n");
        }
    }

    // records.txt 파일에 기록 저장
    private void saveRecord(int attempts) {
        try (FileWriter fw = new FileWriter("records.txt", true)) {
            fw.write("성공! 시도 횟수: " + attempts + "회\n");
        } catch (IOException ex) {
            logArea.append("[오류] 기록 저장에 실패했습니다.\n");
        }
    }
}
