package 프로그래머스.level4.블록게임;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
            {0, 0, 0, 0, 3, 0, 4, 0, 0, 0}, {0, 0, 0, 2, 3, 0, 0, 0, 5, 5},
            {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}};
        int result = new Solution().solution(board);
        System.out.println(result);
    }

    static class Point {

        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Block {

        int idx;
        List<Point> points = new ArrayList<>();
        int type;

        public Block(int idx) {
            this.idx = idx;
        }

        public void add(int r, int c) {
            points.add(new Point(r, c));
            if (points.size() == 4) {
                points.sort((a, b) -> {
                    if (a.r == b.r) {
                        return a.c - b.c;
                    }
                    return a.r - b.r;
                });
                checkBoombable();
            }
        }

        private void checkBoombable() {
            this.type = -1;
            for (int type = 0; type < 5; type++) {
                boolean result = true;
                Point topLeftPoint = getTopLeftPoint();
                for (int i = 0; i < 4; i++) {
                    int r = BOOMBABLE_R[type][i] + topLeftPoint.r;
                    int c = BOOMBABLE_C[type][i] + topLeftPoint.c;
                    Point point = points.get(i);
                    if (!(point.r == r && point.c == c)) {
                        result = false;
                        break;
                    }
                }
                if (result) {
                    this.type = type;
                }
            }
        }

        public Point getTopLeftPoint() {
            return points.stream().findFirst().get();
        }
    }

    private static final int[][] BOOMBABLE_R = {{0, 1, 1, 1}, {0, 1, 2, 2}, {0, 1, 2, 2},
        {0, 1, 1, 1}, {0, 1, 1, 1}};
    private static final int[][] BOOMBABLE_C = {{0, 0, 1, 2}, {0, 0, -1, 0}, {0, 0, 0, 1},
        {0, -2, -1, 0}, {0, -1, 0, 1}};
    private static final int[][] BLACK_BLOCK_R = {{0, 0}, {0, 1}, {0, 1}, {0, 0}, {0, 0}};
    private static final int[][] BLACK_BLOCK_C = {{1, 2}, {-1, -1}, {1, 1}, {-2, -1}, {-1, 1}};

    private int n;
    private Map<Integer, Block> blockMap = new HashMap<>();

    public int solution(int[][] board) {
        n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                Block block = blockMap.getOrDefault(board[i][j], new Block(board[i][j]));
                block.add(i, j);
                blockMap.put(board[i][j], block);
            }
        }

        int result = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int idx = board[i][j];
                if(idx == 0) {
                    continue;
                }
                Block block = blockMap.get(idx);
                if(block.type == -1) {
                    continue;
                }
                Point topLeftPoint = block.getTopLeftPoint();
                int boomRA = BLACK_BLOCK_R[block.type][0] + topLeftPoint.r;
                int boomRB = BLACK_BLOCK_R[block.type][1] + topLeftPoint.r;
                int boomCA = BLACK_BLOCK_C[block.type][0] + topLeftPoint.c;
                int boomCB = BLACK_BLOCK_C[block.type][1] + topLeftPoint.c;
                boolean fail = false;
                for(int r=Math.max(boomRA, boomRB); r>=0; r--) {
                    if(board[r][boomCA] != 0 || board[r][boomCB] != 0) {
                        fail = true;
                        break;
                    }
                }
                if(fail) {
                    continue;
                }
                board[boomRA][boomCA] = 0;
                board[boomRB][boomCB] = 0;
                for (Point point : block.points) {
                    board[point.r][point.c] = 0;
                }
                result++;
            }
        }

        return result;
    }
}
