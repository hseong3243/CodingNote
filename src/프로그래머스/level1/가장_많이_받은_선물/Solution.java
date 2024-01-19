package 프로그래머스.level1.가장_많이_받은_선물;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        String[] friends = {"muzi", "ryan", "frodo", "neo"};
        String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi",
            "frodo muzi", "frodo ryan", "neo muzi"};
        int result = new Solution().solution(friends, gifts);
        System.out.println(result);
    }

    static class FriendsGiftStatus {

        Map<String, Friend> friendMap = new HashMap<>();
        Map<String, Integer> giftPoint = new HashMap<>();

        public void put(String friend) {
            friendMap.put(friend, new Friend(friend));
            giftPoint.put(friend, 0);
        }

        public void give(String friendA, String friendB) {
            Friend giver = friendMap.get(friendA);
            giver.giveTo(friendB);
            giftPoint.put(friendA, giftPoint.getOrDefault(friendA, 0) + 1);
            giftPoint.put(friendB, giftPoint.getOrDefault(friendB, 0) - 1);
        }

        public int findMaxGiftReceiver() {
            Map<String, Integer> thisMonthGift = new HashMap<>();
            List<Friend> friends = friendMap.values().stream().collect(Collectors.toList());
            for (Friend friend : friends) {
                thisMonthGift.put(friend.name, 0);
            }
            for (int i = 0; i < friends.size() - 1; i++) {
                Friend friendA = friends.get(i);
                for (int j = i + 1; j < friends.size(); j++) {
                    Friend friendB = friends.get(j);
                    int give = friendA.getGiveCount(friendB);
                    int receive = friendB.getGiveCount(friendA);
                    if (give > receive) {
                        thisMonthGift.put(friendA.name, thisMonthGift.get(friendA.name) + 1);
                    } else if(give < receive) {
                        thisMonthGift.put(friendB.name, thisMonthGift.get(friendB.name) + 1);
                    } else {
                        Integer friendAPoint = giftPoint.get(friendA.name);
                        Integer friendBPoint = giftPoint.get(friendB.name);
                        if(friendAPoint > friendBPoint) {
                            thisMonthGift.put(friendA.name, thisMonthGift.get(friendA.name) + 1);
                        } else if(friendAPoint < friendBPoint) {
                            thisMonthGift.put(friendB.name, thisMonthGift.get(friendB.name) + 1);
                        }
                    }
                }
            }
            return thisMonthGift.values().stream().max(Comparator.comparingInt(a -> a)).get();
        }
    }

    static class Friend {

        String name;
        Map<String, Integer> gift = new HashMap<>();

        public Friend(String name) {
            this.name = name;
        }

        public void giveTo(String friend) {
            Integer giftPoint = gift.getOrDefault(friend, 0);
            gift.put(friend, giftPoint + 1);
        }

        public int getGiveCount(Friend friend) {
            return gift.getOrDefault(friend.name, 0);
        }
    }

    public int solution(String[] friends, String[] gifts) {
        FriendsGiftStatus friendsGiftStatus = new FriendsGiftStatus();
        for (String friend : friends) {
            friendsGiftStatus.put(friend);
        }
        for (String gift : gifts) {
            String[] split = gift.split(" ");
            String friendA = split[0];
            String friendB = split[1];
            friendsGiftStatus.give(friendA, friendB);
        }
        return friendsGiftStatus.findMaxGiftReceiver();
    }
}
