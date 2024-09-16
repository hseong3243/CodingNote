package 백준.gold.줄_세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    private static class Student {

        private boolean isRoot = true;
        private int number = Integer.MAX_VALUE;
        private Set<Student> back = new HashSet<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<Integer, Student> students = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            students.put(i, new Student());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Student studentA = students.get(a);
            Student studentB = students.get(b);
            studentA.back.add(studentB);
            studentB.isRoot = false;
        }
        new Main().solution(n, m, students);
    }

    private void solution(int n, int m, Map<Integer, Student> students) {
        for (Student student : students.values()) {
            if (student.isRoot) {
                student.number = 1;
                check(student);
            }
        }
        StringBuilder sb = new StringBuilder();
        students.entrySet().stream()
            .sorted(Comparator.comparingInt(a -> a.getValue().number))
            .forEach(entry -> sb.append(entry.getKey()).append(" "));
        System.out.println(sb);
    }

    private void check(Student student) {
        Queue<Student> q = new LinkedList<>();
        q.add(student);
        while (!q.isEmpty()) {
            Student cur = q.poll();
            for (Student nextStudent : cur.back) {
                if (nextStudent.number == Integer.MAX_VALUE) {
                    nextStudent.number = cur.number + 1;
                    q.add(nextStudent);
                } else {
                    int nextNumber = cur.number + 1;
                    if(nextNumber > nextStudent.number) {
                        nextStudent.number = nextNumber;
                        q.add(nextStudent);
                    }
                }
            }
        }
    }
}
