package 백준.gold.후위_표기식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        new Main().solution(str);
    }

    private final static Map<Character, Integer> PRIORITY = new HashMap<>() {{
        this.put('(', 3);
        this.put(')', 3);
        this.put('+', 2);
        this.put('-', 2);
        this.put('*', 1);
        this.put('/', 1);
    }};

    private void solution(String str) {
        char[] arr = str.toCharArray();
        Stack<Character> symbols = new Stack<>();

        StringBuilder result = new StringBuilder();
        for (char c : arr) {
            if(Character.isAlphabetic(c)) {
                result.append(c);
            } else {
                switch (PRIORITY.get(c)) {
                    case 3:
                        if(c == ')') {
                            while (symbols.peek() != '(') {
                                result.append(symbols.pop());
                            }
                            symbols.pop();
                        } else {
                            symbols.add(c);
                        }
                        break;
                    case 1:
                    case 2:
                        while (!symbols.isEmpty() && PRIORITY.get(c) >= PRIORITY.get(symbols.peek())) {
                            result.append(symbols.pop());
                        }
                        symbols.add(c);
                        break;
                }
            }
        }
        while (!symbols.isEmpty()) {
            result.append(symbols.pop());
        }
        System.out.println(result);
    }
}
