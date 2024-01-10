package 프로그래머스.level2.단체사진_찍기;

public class Solution {

    public static void main(String[] args) {
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
//        String[] data = {"M~C<2", "C~M>1"};
        int result = new Solution().solution(n, data);
        System.out.println(result);
    }

    private static final char[] arr = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private boolean[] visit = new boolean[8];
    private String[] data;
    private int result = 0;

    public int solution(int n, String[] data) {
        this.data = data;

        char[] line = new char[8];
        find(0, line);
        return result;
    }

    private void find(int count, char[] line) {
        if (count >= 8) {
            if(check(line)) {
                result++;
            }
            return;
        }
        for (int i = 0; i < 8; i++) {
            if (!visit[i]) {
                visit[i] = true;
                line[count] = arr[i];
                find(count + 1, line);
                visit[i] = false;
            }
        }
    }

    private boolean check(char[] line) {
        for (String datum : data) {
            int distance = Math.abs(
                indexOf(line, datum.charAt(0)) - indexOf(line, datum.charAt(2))) - 1;
            char query = datum.charAt(3);
            int n = datum.charAt(4) - '0';
            if (query == '=') {
                if (distance != n) {
                    return false;
                }
            } else if (query == '<') {
                if (distance >= n) {
                    return false;
                }
            } else {
                if(distance <= n) {
                    return false;
                }
            }
        }
        return true;
    }

    private int indexOf(char[] line, char input) {
        for (int i = 0; i < 8; i++) {
            if (line[i] == input) {
                return i;
            }
        }
        return -1;
    }
}
