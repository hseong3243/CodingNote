package 프로그래머스.level3.퍼즐_조각_채우기;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class Solution {

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    static class Block {

        List<Point> points;
        boolean visit = false;

        public Block(List<Point> points) {
            this.points = points;
        }

        private void visit() {
            visit = true;
        }

        private boolean isNotVisit() {
            return !visit;
        }

        public boolean isEqualTo(Block block) {
            for (int i = 0; i < points.size(); i++) {
                if (!points.get(i).equals(block.points.get(i))) {
                    return false;
                }
            }
            return true;
        }

        public void sort() {
            points.sort((a, b) -> {
                if (a.r > b.r) {
                    return 1;
                } else if (a.r == b.r) {
                    return a.c > b.c ? 1 : -1;
                } else {
                    return -1;
                }
            });
            int leftUpRow = points.get(0).r;
            int leftUpCol = points.get(0).c;
            for(int i=0; i<points.size(); i++) {
                points.get(i).r -= leftUpRow;
                points.get(i).c -= leftUpCol;
            }
        }
    }

    static class Point {

        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public static void main(String[] args) {
        int[][] gameBoard = new int[][]{{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1},
            {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}};
        int[][] table = new int[][]{{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1},
            {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}};
        new Solution().solution(gameBoard, table);
    }

    public int solution(int[][] game_board, int[][] table) {
        int n = game_board.length;

        Map<Integer, List<Block>> blankGroup = new HashMap<>();
        Map<Integer, List<Block>> blockGroup = new HashMap<>();
        for (int i = 0; i <= 6; i++) {
            blankGroup.put(i, new ArrayList<>());
            blockGroup.put(i, new ArrayList<>());
        }
        boolean[][] visitBoard = new boolean[n][n];
        boolean[][] visitTable = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (canMove(i, j, game_board, visitBoard, 0)) {
                    visitBoard[i][j] = true;
                    List<Point> points = findFixture(i, j, game_board, visitBoard, 0);
                    blankGroup.get(points.size()).add(new Block(points));
                }
                if (canMove(i, j, table, visitTable, 1)) {
                    visitTable[i][j] = true;
                    List<Point> points = findFixture(i, j, table, visitTable, 1);
                    blockGroup.get(points.size()).add(new Block(points));
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= 6; i++) {
            List<Block> blanks = blankGroup.get(i);
            List<Block> blocks = blockGroup.get(i);
            for (Block blank : blanks) {
                for (Block block : blocks) {
                    if (blank.isNotVisit() && block.isNotVisit() && isMatch(blank, block)) {
                        block.visit();
                        blank.visit();
                        result += block.points.size();
                    }
                }
            }
        }
        return result;
    }

    private List<Point> findFixture(int row, int col, int[][] gameBoard, boolean[][] visit,
        int target) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(row, col));
        while (!q.isEmpty()) {
            Point point = q.poll();
            for (int d = 0; d < 4; d++) {
                int nextRow = point.r + DR[d];
                int nextCol = point.c + DC[d];
                if (canMove(nextRow, nextCol, gameBoard, visit, target)) {
                    visit[nextRow][nextCol] = true;
                    q.add(new Point(nextRow, nextCol));
                    points.add(new Point(nextRow - row, nextCol - col));
                }
            }
        }
        return points;
    }

    private boolean canMove(int nextRow, int nextCol, int[][] gameBoard, boolean[][] visit,
        int target) {
        int n = gameBoard.length;
        if (nextRow < 0 || nextRow >= n) {
            return false;
        }
        if (nextCol < 0 || nextCol >= n) {
            return false;
        }
        if (visit[nextRow][nextCol]) {
            return false;
        }
        if (gameBoard[nextRow][nextCol] != target) {
            return false;
        }
        return true;
    }

    private boolean isMatch(Block blank, Block block) {
        blank.sort();
        for (int r = 0; r < 4; r++) {
            for (Point point : block.points) {
                int temp = point.r;
                point.r = point.c;
                point.c = -1 * temp;
            }
            block.sort();
            for (int i = 0; i < block.points.size(); i++) {
                if (blank.isEqualTo(block)) {
                    return true;
                }
            }
        }
        return false;
    }
}
