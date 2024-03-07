package 프로그래머스.level3.사라지는_발판;

public class Solution {

    public static void main(String[] args) {
//        int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[][] board = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[] aloc = {1, 0};
        int[] bloc = {1, 2};
//        int[][] board = {{1, 1, 1, 1, 1}};
//        int[] aloc = {0, 0};
//        int[] bloc = {0, 4};
//        int[][] board = {{1}};
//        int[] aloc = {0, 0};
//        int[] bloc = {0, 0};
        int result = new Solution().solution(board, aloc, bloc);
        System.out.println(result);
    }

    static class Game {
        private static final int[] DR = {-1, 1, 0, 0};
        private static final int[] DC = {0, 0, -1, 1};

        int[][] board;
        Player playerA;
        Player playerB;

        public Game(int[][] board, Player playerA, Player playerB) {
            this.board = board;
            this.playerA = playerA;
            this.playerB = playerB;
        }

        public int play() {
            Result result = turnA(0);
            return result.count;
        }

        private Result turnA(int count) {
            int win = Integer.MAX_VALUE;
            int loose = 0;
            if(board[playerA.r][playerA.c] == 0) {
                return new Result(false, count);
            }
            boolean isEnd = true;
            for(int d=0; d<4; d++) {
                int currentR = playerA.r;
                int currentC = playerA.c;
                int nextR = currentR + DR[d];
                int nextC = currentC + DC[d];
                if(canMove(nextR, nextC)) {
                    isEnd = false;
                    board[currentR][currentC] = 0;
                    playerA.move(nextR, nextC);
                    Result result = turnB(count + 1);
                    playerA.move(currentR, currentC);
                    board[currentR][currentC] = 1;
                    if(result.isWin) {
                        loose = Math.max(loose, result.count);
                    } else {
                        win = Math.min(win, result.count);
                    }
                }
            }
            if(isEnd) {
                return new Result(false, count);
            } else if(win != Integer.MAX_VALUE) {
                return new Result(true, win);
            } else {
                return new Result(false, loose);
            }
        }

        private Result turnB(int count) {
            int win = Integer.MAX_VALUE;
            int loose = 0;
            if(board[playerB.r][playerB.c] == 0) {
                return new Result(false, count);
            }
            boolean isEnd = true;
            for(int d=0; d<4; d++) {
                int currentR = playerB.r;
                int currentC = playerB.c;
                int nextR = currentR + DR[d];
                int nextC = currentC + DC[d];
                if(canMove(nextR, nextC)) {
                    isEnd = false;
                    board[currentR][currentC] = 0;
                    playerB.move(nextR, nextC);
                    Result result = turnA(count + 1);
                    playerB.move(currentR, currentC);
                    board[currentR][currentC] = 1;
                    if(result.isWin) {
                        loose = Math.max(loose, result.count);
                    } else {
                        win = Math.min(win, result.count);
                    }
                }
            }
            if(isEnd) {
                return new Result(false, count);
            } else if(win != Integer.MAX_VALUE) {
                return new Result(true, win);
            } else {
                return new Result(false, loose);
            }
        }

        private boolean canMove(int r, int c) {
            int n = board.length;
            int m = board[0].length;
            if(r < 0 || c < 0 || r >= n || c >= m) {
                return false;
            }
            if(board[r][c] == 0) {
                return false;
            }
            return true;
        }
    }

    static class Result {
        boolean isWin;
        int count;

        public Result(boolean isWin, int count) {
            this.isWin = isWin;
            this.count = count;
        }
    }

    static class Player {

        int r;
        int c;

        public Player(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void move(int nextR, int nextC) {
            this.r = nextR;
            this.c = nextC;
        }
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        Player playerA = new Player(aloc[0], aloc[1]);
        Player playerB = new Player(bloc[0], bloc[1]);
        Game game = new Game(board, playerA, playerB);
        return game.play();
    }
}
