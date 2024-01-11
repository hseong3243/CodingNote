package 프로그래머스.level3.자물쇠와_열쇠;

public class Solution {

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1, 1}, {1, 1, 0, 1}, {1, 0, 1, 1}, {0, 0, 0, 0}};
        boolean solution = new Solution().solution(key, lock);
        System.out.println(solution);
    }

    int[][] extendedKey;
    int extendedLen;
    int keyLen;
    int lockLen;

    public boolean solution(int[][] key, int[][] lock) {
        keyLen = key.length;
        lockLen = lock.length;
        extendedLen = 2 * lockLen + keyLen - 2;
        extendedKey = new int[extendedLen][extendedLen];
        extendKey(key);

        for (int d = 0; d < 4; d++) {
            if (moveAndMatch(lock)) {
                return true;
            }
            lock = rotate(lock);
        }

        return false;
    }

    private boolean moveAndMatch(int[][] lock) {
        for (int i = 0; i < lockLen - 2 + keyLen; i++) {
            for (int j = 0; j < lockLen - 2 + keyLen; j++) {
                if (isMatch(lock, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isMatch(int[][] lock, int r, int c) {
        for (int i = 0; i < lockLen; i++) {
            for (int j = 0; j < lockLen; j++) {
                if (lock[i][j] + extendedKey[r + i][c + j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void extendKey(int[][] key) {
        for (int i = 0; i < keyLen; i++) {
            for (int j = 0; j < keyLen; j++) {
                extendedKey[i + lockLen - 1][j + lockLen - 1] = key[i][j];
            }
        }
    }

    private int[][] rotate(int[][] lock) {
        int[][] arr = new int[lockLen][lockLen];
        for (int i = 0; i < lockLen; i++) {
            for (int j = 0; j < lockLen; j++) {
                arr[j][lockLen - i - 1] = lock[i][j];
            }
        }
        return arr;
    }

    private void print(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
    }
}
