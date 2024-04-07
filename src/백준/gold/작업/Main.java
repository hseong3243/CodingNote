package 백준.gold.작업;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static class Job {
        int time;
        Set<Job> require = new HashSet<>();

        public Job(int time) {
            this.time = time;
        }

        public void addRequire(Job job) {
            require.add(job);
        }

        public boolean remove(Job job) {
            return require.remove(job);
        }

        public boolean noMoreRequire() {
            return require.isEmpty();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(0));
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Job job = new Job(Integer.parseInt(st.nextToken()));
            int count = Integer.parseInt(st.nextToken());
            for(int j=0; j<count; j++) {
                int number = Integer.parseInt(st.nextToken());
                job.addRequire(jobs.get(number));
            }
            jobs.add(job);
        }
        new Main().solution(n, jobs);
    }

    private void solution(int n, List<Job> jobs) {
        int[] dp = new int[n+1];
        for(int i=1; i<=n; i++) {
            Job job = jobs.get(i);
            for(int j=i+1; j<=n; j++) {
                Job maybeRequired = jobs.get(j);
                if(maybeRequired.noMoreRequire()) {
                    continue;
                }
                if(maybeRequired.remove(job)) {
                    dp[j] = Math.max(dp[j], dp[i] + job.time);
                }
            }
        }

        int max = 0;
        for(int i=1; i<=n; i++) {
            Job job = jobs.get(i);
            max = Math.max(max, dp[i] + job.time);
        }
        System.out.println(max);
    }
}
