package game;

import model.Result;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseballGame {
    private List<Integer> answer;
    private int attempts;

    public BaseballGame() {
        generateAnswer();
    }

    // 1~9까지 서로 다른 3자리 난수 생성
    public void generateAnswer() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        
        answer = numbers.subList(0, 3);
        attempts = 0;
    }

    // 입력값을 비교하여 Result 객체 반환
    public Result guess(String input) {
        attempts++;
        int strikes = 0;
        int balls = 0;

        for (int i = 0; i < 3; i++) {
            int num = Character.getNumericValue(input.charAt(i));
            if (num == answer.get(i)) {
                strikes++;
            } else if (answer.contains(num)) {
                balls++;
            }
        }
        return new Result(strikes, balls);
    }

    public int getAttempts() {
        return attempts;
    }
}
