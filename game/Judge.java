package game;

import model.Result;

public class Judge {

    public Result judge(String answer, String input) {

        int strike = 0;
        int ball = 0;

        for (int i = 0; i < answer.length(); i++) {

            if (answer.charAt(i) == input.charAt(i)) {
                strike++;
            } else if (answer.contains(String.valueOf(input.charAt(i)))) {
                ball++;
            }
        }

        return new Result(strike, ball);
    }
}
