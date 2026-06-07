package game;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NumberGenerator {

    public String generate(int digit) {

        Random random = new Random();
        Set<Integer> numbers = new HashSet<>();

        while (numbers.size() < digit) {
            numbers.add(random.nextInt(10));
        }

        StringBuilder sb = new StringBuilder();

        for (int num : numbers) {
            sb.append(num);
        }

        return sb.toString();
    }
}
