package 프로그래머스.level3.표_병합;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
//        String[] commands = {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap",
//            "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean",
//            "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian",
//            "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik",
//            "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"};
        String[] commands = {"MERGE 1 1 2 2", "MERGE 1 1 3 3", "UPDATE 3 3 A", "PRINT 1 1",
            "PRINT 2 2", "PRINT 3 3"};
//        String[] commands = {"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d",
//            "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2",
//            "PRINT 1 1"};
        String[] result = new Solution().solution(commands);
        for (String s : result) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    static class Data {

        String str;
        int r;
        int c;
        int parentR;
        int parentC;

        public Data(String str, int parentR, int parentC) {
            this.str = str;
            this.r = parentR;
            this.c = parentC;
            this.parentR = parentR;
            this.parentC = parentC;
        }

        public void update(String str) {
            this.str = str;
        }

        public boolean isBlank() {
            return str.isBlank();
        }

        public boolean isNotBlank() {
            return !isBlank();
        }

        public void setParent(Data parentA) {
            parentR = parentA.parentR;
            parentC = parentA.parentC;
        }
    }

    private Data[][] arr = new Data[51][51];

    public String[] solution(String[] commands) {
        for (int i = 0; i <= 50; i++) {
            for (int j = 0; j <= 50; j++) {
                arr[i][j] = new Data("", i, j);
            }
        }
        List<String> result = new ArrayList<>();
        for (String command : commands) {
            String[] split = command.split(" ");
            String cmd = split[0];
            switch (cmd) {
                case "UPDATE" -> {
                    if (split.length == 4) {
                        int r = Integer.parseInt(split[1]);
                        int c = Integer.parseInt(split[2]);
                        String value = split[3];
                        Data parent = find(r, c);
                        parent.update(value);
                    } else {
                        String value1 = split[1];
                        String value2 = split[2];
                        for (int i = 1; i <= 50; i++) {
                            for (int j = 1; j <= 50; j++) {
                                if (arr[i][j].str.equals(value1)) {
                                    arr[i][j].update(value2);
                                }
                            }
                        }
                    }
                }
                case "MERGE" -> {
                    int r1 = Integer.parseInt(split[1]);
                    int c1 = Integer.parseInt(split[2]);
                    int r2 = Integer.parseInt(split[3]);
                    int c2 = Integer.parseInt(split[4]);
                    Data parentA = find(r1, c1);
                    Data parentB = find(r2, c2);
                    if (parentA == parentB) {
                        continue;
                    }
                    if (parentA.isNotBlank() && parentB.isNotBlank()) {
                        parentB.setParent(parentA);
                    } else if (parentA.isNotBlank() && parentB.isBlank()) {
                        parentB.setParent(parentA);
                    } else if (parentB.isNotBlank() && parentA.isBlank()) {
                        parentA.setParent(parentB);
                    } else {
                        if (parentA.parentR < parentB.parentR) {
                            parentB.setParent(parentA);
                        } else if (parentA.parentR == parentB.parentR) {
                            if (parentA.parentC < parentB.parentC) {
                                parentB.setParent(parentA);
                            } else {
                                parentA.setParent(parentB);
                            }
                        } else {
                            parentA.setParent(parentB);
                        }
                    }
                }
                case "UNMERGE" -> {
                    int r = Integer.parseInt(split[1]);
                    int c = Integer.parseInt(split[2]);
                    Data origin = find(r, c);
                    String unmerge = origin.str;
                    List<Data> needToInit = new ArrayList<>();
                    for (int i = 1; i <= 50; i++) {
                        for (int j = 1; j <= 50; j++) {
                            Data parent = find(i, j);
                            if(parent == origin) {
                                needToInit.add(arr[i][j]);
                            }
                        }
                    }
                    needToInit.forEach(data -> {
                        data.parentR = data.r;
                        data.parentC = data.c;
                        data.str = "";
                    });
                    arr[r][c].str = unmerge;
                }
                default -> {
                    int r = Integer.parseInt(split[1]);
                    int c = Integer.parseInt(split[2]);
                    Data parent = find(r, c);
                    if (parent.isNotBlank()) {
                        result.add(parent.str);
                    } else {
                        result.add("EMPTY");
                    }
                }
            }
        }
        return result.toArray(new String[0]);
    }

    private Data find(int r, int c) {
        if (arr[r][c].parentR == r && arr[r][c].parentC == c) {
            return arr[r][c];
        }
        return find(arr[r][c].parentR, arr[r][c].parentC);
    }
}
