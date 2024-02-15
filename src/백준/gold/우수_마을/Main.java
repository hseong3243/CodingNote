package 백준.gold.우수_마을;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] town = new int[n];
        for (int i = 0; i < n; i++) {
            town[i] = Integer.parseInt(st.nextToken());
        }
        int[][] map = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
        }

        new Main().solution(n, town, map);
    }

    static class Town {

        int men;
        List<Town> links = new ArrayList<>();
        Town parent;
        int sumChoice;
        int sumNotChoice;

        public Town(int men) {
            this.men = men;
            this.sumChoice = men;
            this.sumNotChoice = 0;
        }

        public void link(Town town) {
            links.add(town);
            town.links.add(this);
        }

        public void findLeaf() {
            for (Town link : links) {
                if (link == parent) {
                    continue;
                }
                link.findLeaf(this);
                sumNotChoice += Math.max(link.sumChoice, link.sumNotChoice);
                sumChoice += link.sumNotChoice;
            }
        }

        private void findLeaf(Town parent) {
            this.parent = parent;
            findLeaf();
        }
    }

    private void solution(int n, int[] town, int[][] map) {
        List<Town> towns = new ArrayList<>();
        towns.add(new Town(0));
        for (int men : town) {
            towns.add(new Town(men));
        }
        for (int[] ints : map) {
            Town townA = towns.get(ints[0]);
            Town townB = towns.get(ints[1]);
            townA.link(townB);
        }

        Town root = towns.get(1);
        root.findLeaf();
        System.out.println(Math.max(root.sumChoice, root.sumNotChoice));
    }
}
