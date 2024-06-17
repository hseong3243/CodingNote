package 백준.gold.파일_탐색기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Main {

    private static final Map<Character, Integer> ALPHABET = new HashMap<>();

    static {
        for (int i = 0; i < 26; i++) {
            ALPHABET.put((char) ('A' + i), ALPHABET.size());
            ALPHABET.put((char) ('a' + i), ALPHABET.size());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }
        new Main().solution(n, arr);
    }

    private static class File {

        private static final Pattern NUMBER = Pattern.compile("[0-9]+");
        private final List<String> name;

        public File(List<String> name) {
            this.name = name;
        }

        public int compareTo(File input) {
            int length = name.size();
            int inputLength = input.name.size();
            for (int i = 0; i < Math.min(length, inputLength); i++) {
                String origin = name.get(i);
                String target = input.name.get(i);
                if (isNumber(origin) && isNumber(target)) {
                    BigInteger originNumber = new BigInteger(origin);
                    BigInteger targetNumber = new BigInteger(target);
                    if (!originNumber.equals(targetNumber)) {
                        return originNumber.compareTo(targetNumber);
                    } else {
                        if(origin.length() != target.length()) {
                            return origin.length() - target.length();
                        }
                    }
                } else if(isNumber(origin)) {
                    return -1;
                } else if(isNumber(target)) {
                    return 1;
                } else {
                    if(!origin.equals(target)) {
                        return ALPHABET.get(origin.charAt(0)) - ALPHABET.get(target.charAt(0));
                    }
                }
            }
            return length < inputLength ? -1 : 1;
        }

        private boolean isNumber(String input) {
            return NUMBER.matcher(input).matches();
        }

        public String print() {
            StringBuilder sb = new StringBuilder();
            for (String s : name) {
                sb.append(s);
            }
            return sb.toString();
        }
    }

    private void solution(int n, String[] arr) {
        List<File> files = new ArrayList<>();
        for (String name : arr) {
            List<String> strs = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (char c : name.toCharArray()) {
                if(ALPHABET.containsKey(c)) {
                    strs.add(sb.toString());
                    strs.add(String.valueOf(c));
                    sb = new StringBuilder();
                } else {
                    sb.append(c);
                }
            }
            if(sb.length() > 0) {
                strs.add(sb.toString());
            }
            files.add(new File(strs));
        }
        files.sort(File::compareTo);

        for (File file : files) {
            System.out.println(file.print());
        }
    }
}
