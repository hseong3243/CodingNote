package 백준.gold.성냥개비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Main main = new Main();
        for(int i=0; i<n; i++) {
            int m = Integer.parseInt(br.readLine());
            String[] result = main.solution(m);
            for (String s : result) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    private String[] maxArr;
    private long[] minArr;

    public Main() {
        maxArr = getMaxArr();
        minArr = getMinArr();
    }

    private String[] solution(int m) {
        String a = String.valueOf(minArr[m]);
        if(m == 6) {
            a = "6";
        }
        return new String[]{a, maxArr[m]};
    }

    private String[] getMaxArr() {
        String[] maxArr = new String[101];
        maxArr[2] = "1";
        maxArr[3] = "7";
        for(int i=4; i<=100; i++) {
            maxArr[i] = maxArr[i - 2] + "1";
        }
        return maxArr;
    }

    private long[] getMinArr() {
        long[] minArr = new long[101];
        minArr[2] = 1;
        minArr[3] = 7;
        minArr[4] = 4;
        minArr[5] = 2;
        minArr[6] = 0;
        minArr[7] = 8;
        for(int i=8; i<=100; i++) {
            long result = Long.MAX_VALUE;
            for(int j=2; j<=i/2; j++) {
                String s;
                long a = minArr[j];
                long b = minArr[i - j];
                if(a == 0 && b == 0) {
                    s = "66";
                } else if(a == 0 || b == 0) {
                    long number = Math.max(a, b);
                    String sA = number + "0";
                    String sB = "6" + number;
                    s = String.valueOf(Math.min(Long.parseLong(sA), Long.parseLong(sB)));
                } else {
                    String sA = a + String.valueOf(b);
                    String sB = b + String.valueOf(a);
                    s = String.valueOf(Math.min(Long.parseLong(sA), Long.parseLong(sB)));
                }
                result = Math.min(result, Long.parseLong(s));
            }
            minArr[i] = result;
        }
        return minArr;
    }
}
