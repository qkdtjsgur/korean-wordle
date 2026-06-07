package game;

import model.Result;

public class BaseballGame {

    private String answer;
    private int tryCount;

    private NumberGenerator generator;
    private Judge judge;

    public BaseballGame(int digit) {

        generator = new NumberGenerator();
        judge = new Judge();

        answer = generator.generate(digit);
        tryCount = 0;
    }

    public Result play(String input) {

        tryCount++;
        return judge.judge(answer, input);
    }

    public int getTryCount() {
        return tryCount;
    }

    public String getAnswer() {
        return answer;
    }
}
