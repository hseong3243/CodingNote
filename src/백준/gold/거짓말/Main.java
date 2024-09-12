package 백준.gold.거짓말;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    private static class Party {
        private int n;
        private Set<Integer> joiner = new HashSet<>();
        boolean sayTruth = false;

        public Party(int n) {
            this.n = n;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        Set<Integer> truthKnown = new HashSet<>();
        List<Party> parties = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int truthKnownSize = Integer.parseInt(st.nextToken());
        for(int i=0; i< truthKnownSize; i++) {
            truthKnown.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            Party party = new Party(Integer.parseInt(st.nextToken()));
            for(int j=0; j<party.n; j++) {
                party.joiner.add(Integer.parseInt(st.nextToken()));
            }
            parties.add(party);
        }

        new Main().solution(n, m, truthKnown, parties);
    }

    private void solution(int n, int m, Set<Integer> truthKnown, List<Party> parties) {
        Queue<Integer> q = new LinkedList<>(truthKnown);
        while (!q.isEmpty()) {
            Integer onlyTruth = q.poll();
            for (Party party : parties) {
                if(party.joiner.contains(onlyTruth)) {
                    party.sayTruth = true;
                    for (Integer joiner : party.joiner) {
                        if(truthKnown.contains(joiner)) {
                            continue;
                        }
                        truthKnown.add(joiner);
                        q.add(joiner);
                    }
                }
            }
        }
        int result = 0;
        for (Party party : parties) {
            if(party.sayTruth) {
                continue;
            }
            result++;
        }
        System.out.println(result);
    }
}
