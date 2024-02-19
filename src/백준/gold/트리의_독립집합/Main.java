package 백준.gold.트리의_독립집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] weight = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }
        int[][] lines = new int[n - 1][2];
        for(int i=0; i<n-1; i++) {
            String[] split = br.readLine().split(" ");
            lines[i][0] = Integer.parseInt(split[0]);
            lines[i][1] = Integer.parseInt(split[1]);
        }
        new Main().solution(n, weight, lines);
    }

    static class Node {
        int n;
        int weight;
        Set<Node> links = new HashSet<>();

        public Node(int n, int weight) {
            this.n = n;
            this.weight = weight;
        }

        public void link(Node node) {
            links.add(node);
            node.links.add(this);
        }

        public Result getWeightSum() {
            return getWeightSum(this);
        }

        public Result getWeightSum(Node parent) {
            Result result = new Result(this);
            for (Node link : links) {
                if(link == parent) {
                    continue;
                }
                Result prev = link.getWeightSum(this);
                result.addChoice(prev);
                result.addNotChoice(prev);
            }
            return result;
        }
    }

    static class Result {

        int choice;
        int notChoice = 0;
        Set<Node> choiceNode = new HashSet<>();
        Set<Node> notChoiceNode = new HashSet<>();

        public Result(Node node) {
            this.choice = node.weight;
            choiceNode.add(node);
        }

        public void addChoice(Result prevResult) {
            choice += prevResult.notChoice;
            choiceNode.addAll(prevResult.notChoiceNode);
        }

        public void addNotChoice(Result prevResult) {
            if(prevResult.choice > prevResult.notChoice) {
                notChoice += prevResult.choice;
                notChoiceNode.addAll(prevResult.choiceNode);
            } else {
                notChoice += prevResult.notChoice;
                notChoiceNode.addAll(prevResult.notChoiceNode);
            }
        }
    }

    private void solution(int n, int[] weight, int[][] lines) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(0 , 0));
        for(int i=1; i<=n; i++) {
            nodes.add(new Node(i, weight[i-1]));
        }
        for (int[] line : lines) {
            Node a = nodes.get(line[0]);
            Node b = nodes.get(line[1]);
            a.link(b);
        }

        Node root = nodes.get(1);
        Result result = root.getWeightSum();
        int maxWeight = 0;
        Set<Node> maxWeightPath;
        if(result.choice > result.notChoice) {
            maxWeight = result.choice;
            maxWeightPath = result.choiceNode;
        } else {
            maxWeight = result.notChoice;
            maxWeightPath = result.notChoiceNode;
        }
        System.out.println(maxWeight);
        maxWeightPath.stream()
            .sorted(Comparator.comparingInt(a -> a.n))
            .forEach(node -> System.out.print(node.n + " "));
    }
}
