package 백준.gold.선수과목;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int[][] requires = new int[m][2];
        for(int i=0; i<m; i++) {
            split = br.readLine().split(" ");
            requires[i][0] = Integer.parseInt(split[0]);
            requires[i][1] = Integer.parseInt(split[1]);
        }
        new Main().solution(n, m, requires);
    }
    
    static class Lecture {
        int n;
        Set<Lecture> requires = new HashSet<>();

        public Lecture(int n) {
            this.n = n;
        }

        public void addRequire(Lecture lectureA) {
            requires.add(lectureA);
        }
    }

    private void solution(int n, int m, int[][] requires) {
        Map<Integer, Lecture> lectures = new HashMap<>();
        for(int i=1; i<=n; i++) {
            lectures.put(i, new Lecture(i));
        }

        for (int[] require : requires) {
            Lecture lectureA = lectures.get(require[0]);
            Lecture lectureB = lectures.get(require[1]);
            lectureB.addRequire(lectureA);
        }

        int[] dp = new int[n+1];
        for(int i=1; i<=n; i++) {
            int quarter = find(lectures, dp, i);
            System.out.print(quarter + " ");
        }

    }

    private int find(Map<Integer, Lecture> lectures, int[] dp, int i) {
        Lecture lecture = lectures.get(i);
        if(dp[lecture.n] != 0) {
            return dp[lecture.n];
        }
        if(lecture.requires.isEmpty()) {
            dp[lecture.n] = 1;
            return dp[lecture.n];
        }
        for (Lecture require : lecture.requires) {
            dp[lecture.n] = Math.max(dp[lecture.n], find(lectures, dp, require.n) + 1);
        }
        return dp[lecture.n];
    }
}
