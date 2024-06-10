package 백준.gold.낚시왕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private static class Shark {

        public Shark(int id, int r, int c, int s, int d, int z) {
            this.id = id;
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }


        private int id;
        private int r;
        private int c;
        private int s;
        private int d;
        private int z;
        private boolean isCaught = false;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<Integer, Shark> sharks = new HashMap<>();
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            sharks.put(i, new Shark(
                i,
                Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken())
            ));
        }
        new Main().solution(r, c, m, sharks);
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, 1, -1};

    private int[][] map;
    private int r;
    private int c;
    private int m;
    private Map<Integer, Shark> sharks;

    private void solution(int r, int c, int m, Map<Integer, Shark> sharks) {
        this.map = new int[r][c];
        this.r = r;
        this.c = c;
        this.m = m;
        this.sharks = sharks;

        for (Shark shark : sharks.values()) {
            map[shark.r][shark.c] = shark.id;
        }

        int result = 0;
        for (int i = 0; i < c; i++) {
            result += find(i);
            moveShark();
        }

        System.out.println(result);
    }

    private int find(int c) {
        for (int i = 0; i < r; i++) {
            if (map[i][c] != 0) {
                Shark shark = sharks.remove(map[i][c]);
                shark.isCaught = true;
                map[i][c] = 0;
                return shark.z;
            }
        }
        return 0;
    }

    private void moveShark() {
        for (Shark shark : sharks.values()) {
            if (shark.isCaught) {
                continue;
            }

            int moveR = DR[shark.d];
            int moveC = DC[shark.d];
            int needToMove = shark.s;
            if(moveR != 0) {
                needToMove = shark.s % (2 * (r - 1));
            }
            if(moveC != 0) {
                needToMove = shark.s % (2 * (c - 1));
            }
            for (int i = 0; i < needToMove; i++) {
                if(moveR != 0) {
                    if(shark.r == 0) {
                        moveR = 1;
                        shark.d = 1;
                    }
                    if(shark.r == r-1) {
                        moveR = -1;
                        shark.d = 0;
                    }
                }
                if(moveC != 0) {
                    if(shark.c == 0) {
                        moveC = 1;
                        shark.d = 2;
                    }
                    if(shark.c == c-1) {
                        moveC = -1;
                        shark.d = 3;
                    }
                }
                shark.r += moveR;
                shark.c += moveC;
            }
        }

        clearMap();
        List<Shark> mustBeRemoved = new ArrayList<>();
        for (Shark shark : sharks.values()) {
            int r = shark.r;
            int c = shark.c;
            if(map[r][c] != 0) {
                Shark prevShark = sharks.get(map[r][c]);
                if(shark.z > prevShark.z) {
                    map[r][c] = shark.id;
                    mustBeRemoved.add(prevShark);
                } else {
                    mustBeRemoved.add(shark);
                }
            } else {
                map[r][c] = shark.id;
            }
        }

        for (Shark shark : mustBeRemoved) {
            sharks.remove(shark.id);
        }
    }

    private void clearMap() {
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                map[i][j] = 0;
            }
        }
    }
}
