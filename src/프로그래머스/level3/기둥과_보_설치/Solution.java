package 프로그래머스.level3.기둥과_보_설치;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        int n = 5;
//        int[][] arr = {
//            {1, 0, 0, 1},
//            {1, 1, 1, 1},
//            {2, 1, 0, 1},
//            {2, 2, 1, 1},
//            {5, 0, 0, 1},
//            {5, 1, 0, 1},
//            {4, 2, 1, 1},
//            {3, 2, 1, 1}};
        int[][] arr = {
            {0, 0, 0, 1},
            {2, 0, 0, 1},
            {4, 0, 0, 1},
            {0, 1, 1, 1},
            {1, 1, 1, 1},
            {2, 1, 1, 1},
            {3, 1, 1, 1},
            {2, 0, 0, 0},
            {1, 1, 1, 0},
            {2, 2, 0, 1}
        };
        int[][] result = new Solution().solution(n, arr);
        for (int[] ints : result) {
            System.out.print(ints[0] + " " + ints[1] + " " + ints[2]);
            System.out.println();
        }
    }


    private int[][][] map;

    public int[][] solution(int n, int[][] build_frame) {
        map = new int[n + 1][n + 1][2]; //0 기둥, 1 보
        for (int[] command : build_frame) {
            int x = command[0];
            int y = command[1];
            int type = command[2];
            int cmd = command[3];
            switch (type) {
                case 0 -> {  // 기둥이면
                    switch (cmd) {
                        case 0 -> removeTower(x, y);
                        case 1 -> installTower(x, y);
                    }
                }
                case 1 -> { // 보면
                    switch (cmd) {
                        case 0 -> removeBeam(x, y);
                        case 1 -> installBeam(x, y);
                    }
                }
            }
        }
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (map[i][j][0] == 1) {
                    result.add(new int[]{i, j, 0});
                }
                if (map[i][j][1] == 1) {
                    result.add(new int[]{i, j, 1});
                }
            }
        }
        result.sort((a, b) -> {
            if (a[0] - b[0] == 0) {
                if (a[1] - b[1] == 0) {
                    return a[2] - b[2];
                }
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int[][] answer = new int[result.size()][3];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private void installTower(int x, int y) {
        if(canInstallTower(x, y)) {
            map[x][y][0] = 1;
        }
    }

    private boolean canInstallTower(int x, int y) {
        if(y == 0) {  //바닥이거나
            return true;
        }
        if(y-1>=0 && map[x][y-1][0] == 1) {  //아래에 기둥이 있거나
            return true;
        }
        if(x-1>=0 && map[x-1][y][1] == 1) {  //왼쪽에 보가 있거나
            return true;
        }
        if(map[x][y][1] == 1) {  //오른쪽에 보가 있거나
            return true;
        }
        return false;
    }

    private void removeTower(int x, int y) {
        map[x][y][0] = 0;
        if(canRemoveTower(x, y)) {
            return;
        }
        map[x][y][0] = 1;
    }

    private boolean canRemoveTower(int x, int y) {
        if(y+1 < map.length && map[x][y+1][0] == 1 && !canInstallTower(x, y+1)) {  //위쪽 기둥이 불가하거나
            return false;
        }
        if(y+1 < map.length && map[x][y+1][1] == 1 && !canInstallBeam(x, y+1)) {  //위, 오른쪽 보가 불가하거나
            return false;
        }
        if(x-1 >= 0 && y+1 < map.length && map[x-1][y+1][1] == 1 && !canInstallBeam(x-1, y+1)) {  //위, 왼쪽 보가 불가하거나
            return false;
        }
        return true;
    }

    private void installBeam(int x, int y) {
        if(canInstallBeam(x, y)) {
            map[x][y][1] = 1;
        }
    }

    private boolean canInstallBeam(int x, int y) {
        if(y-1 >= 0 && map[x][y-1][0] == 1) {  //아래, 왼쪽에 기둥이 있거나
            return true;
        }
        if(x+1 < map.length && y-1 >= 0 && map[x+1][y-1][0] == 1) {  //아래, 오른쪽에 기둥이 있거나
            return true;
        }
        if(x-1 >= 0 && x+1 < map.length && map[x-1][y][1] == 1 && map[x+1][y][1] == 1) {  //좌, 우에 보가 있거나
            return true;
        }
        return false;
    }

    private void removeBeam(int x, int y) {
        map[x][y][1] = 0;
        if(canRemoveBeam(x, y)) {
            return;
        }
        map[x][y][1] = 1;
    }

    private boolean canRemoveBeam(int x, int y) {
        if(map[x][y][0] == 1 && !canInstallTower(x, y)) {  //좌에 기둥이 불가하거나
            return false;
        }
        if(x+1 < map.length && map[x+1][y][0] == 1 && !canInstallTower(x+1, y)) {  //우에 기둥이 불가하거나
            return false;
        }
        if(x-1 >= 0 && map[x-1][y][1] == 1 && !canInstallBeam(x-1, y)) {  //좌에 보가 불가하거나
            return false;
        }
        if(x+1 < map.length && map[x+1][y][1] == 1 && !canInstallBeam(x+1, y)) {  //우에 보가 불가하거나
            return false;
        }
        return true;
    }
}
