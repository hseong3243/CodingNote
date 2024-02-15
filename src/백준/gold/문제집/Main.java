package 백준.gold.문제집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[m][2];
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        new Main().solution(n, m, arr);
    }

    static class Problem {
        int n;
        Set<Problem> needToBeSolve = new HashSet<>();
        Set<Problem> nextProblem = new HashSet<>();
        boolean visit = false;

        public Problem(int n) {
            this.n = n;
        }

        public void addNextProblem(Problem problem) {
            nextProblem.add(problem);
            problem.needToBeSolve.add(this);
        }

        public void visit() {
            visit = true;
        }

        public void remove(Problem problem) {
            needToBeSolve.remove(problem);
        }
    }

    private void solution(int n, int m, int[][] arr) {
        List<Problem> problems = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            problems.add(new Problem(i));
        }
        for (int[] ints : arr) {
            int a = ints[0];
            int b = ints[1];
            problems.get(a).addNextProblem(problems.get(b));
        }
        List<Integer> result = bfs(problems);
        for (Integer number : result) {
            System.out.print(number + " ");
        }
    }

    private List<Integer> bfs(List<Problem> problems) {
        List<Integer> solveOrder = new ArrayList<>();
        PriorityQueue<Problem> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.n));
        for (Problem problem : problems) {
            if(problem.needToBeSolve.isEmpty() && problem.n != 0) {
                q.add(problem);
            }
        }
        while (!q.isEmpty()) {
            Problem problem = q.poll();
            problem.visit();
            solveOrder.add(problem.n);
            for (Problem next : problem.nextProblem) {
                next.remove(problem);
                if(next.needToBeSolve.isEmpty()) {
                    q.add(next);
                }
            }
        }
        return solveOrder;
    }
}
