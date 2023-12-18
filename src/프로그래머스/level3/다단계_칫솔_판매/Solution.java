package 프로그래머스.level3.다단계_칫솔_판매;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution {

    static class Node {
        String name;
        Node parent;
        int sum = 0;

        public Node(String name) {
            this.name = name;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public void sell(double price) {
            if(price == 0) {
                return;
            }
            double toParent = Math.floor(price / 10);
            sum += (int) (price - toParent);
            if(Objects.nonNull(parent)) {
                parent.sell(toParent);
            }
        }
    }

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        int[] result = new Solution().solution(enroll, referral, seller, amount);
        for (int sum : result) {
            System.out.println(sum);
        }
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Node> nodes = new HashMap<>();
        for(int i=0; i<enroll.length; i++) {
            String nodeName = enroll[i];
            String parentName = referral[i];
            Node newNode = new Node(nodeName);
            nodes.put(nodeName, newNode);
            if(!parentName.equals("-")) {
                newNode.setParent(nodes.get(parentName));
            }
        }

        for(int i=0; i<seller.length; i++) {
            String nodeName = seller[i];
            int sellAmount = amount[i];
            Node node = nodes.get(nodeName);
            node.sell(100*sellAmount);
        }

        int[] result = new int[enroll.length];
        for (int i=0; i<enroll.length; i++) {
            String nodeName = enroll[i];
            Node node = nodes.get(nodeName);
            result[i] = node.sum;
        }

        return result;
    }
}
