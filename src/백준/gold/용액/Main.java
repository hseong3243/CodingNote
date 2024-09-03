package 백준.gold.용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        new Main().solution(n, arr);
    }

    private void solution(int n, int[] arr) {
        int targetA = 0;
        int targetB = 0;
        int nearest = Integer.MAX_VALUE;

        int left = 0;
        int right = n - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if(Math.abs(sum) < nearest) {
                targetA = arr[left];
                targetB = arr[right];
                nearest = Math.abs(sum);
                if(nearest == 0) {
                    break;
                }
            }
            if(sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(targetA + " " + targetB);
    }

    private void solution2(int n, int[] arr) {
        int targetA = 0;
        int targetB = 0;
        int nearest = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int number = arr[i];
            int min = i + 1;
            int max = n - 1;
            while (min <= max) {
                int mid = (max + min) / 2;
                if(Math.abs(number) == Math.abs(arr[mid])) {
                    targetA = number;
                    targetB = arr[mid];
                    break;
                } else if(Math.abs(number) > Math.abs(arr[mid])) {
                    if(number < 0) {
                        min = mid + 1;
                    } else {
                        max = mid - 1;
                    }
                } else {
                    max = mid - 1;
                }

                if(Math.abs(number + arr[mid]) < nearest) {
                    nearest = Math.abs(number + arr[mid]);
                    targetA = number;
                    targetB = arr[mid];
                }
            }
        }

        System.out.println(targetA + " " + targetB);
    }
}
