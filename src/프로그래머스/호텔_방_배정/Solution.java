package 프로그래머스.호텔_방_배정;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        long k = 10;
        long[] roomNumber = {1,3,4,1,3,1};
        long[] result = new Solution().solution(k, roomNumber);
        for (long l : result) {
            System.out.print(l + ",");
        }
    }

    public long[] solution(long k, long[] room_number) {
        int len = room_number.length;
        long[] result = new long[len];

        Map<Long, Long> parent = new HashMap<>();
        for(int i=0; i<len; i++) {
            result[i] = findParent(room_number[i], parent);
        }

        return result;
    }

    private long findParent(long room, Map<Long, Long> parent) {
        if(!parent.containsKey(room)) {
            parent.put(room, room + 1);
            return room;
        } else {
            long parentRoom = findParent(parent.get(room), parent);
            parent.put(room, parentRoom);
            return parentRoom;
        }
    }
}
