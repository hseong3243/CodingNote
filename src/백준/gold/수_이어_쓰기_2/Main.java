package 백준.gold.수_이어_쓰기_2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        new Main().solution(n, k);
    }

    public void solution(int n, int k) {
        int number = 0;
        long numberLength = 1;
        long numberCount = 9;
        while (k > numberLength * numberCount) {
            k -= numberLength * numberCount;
            number += numberCount;
            numberLength++;
            numberCount *= 10;
        }
        number = (int)(number + 1 + (k - 1) / numberLength);
        if(number >n) {
            System.out.println(-1);
        } else {
            int idx = (int)((k - 1) % numberLength);
            System.out.println(String.valueOf(number).charAt(idx));
        }
    }
}
