## 핵심

- LIS, 이분탐색
- 가장 긴 증가하는 부분 수열 문제이다. n이 최대 40,000이기 때문에 O(n^2) 방법으로는 시간 초과가 발생한다.

```java
private void solution(int n, int[] arr) {
    int[] dp = new int[n];
    dp[0] = arr[0];
    int len = 1;
    for (int i = 1; i < n; i++) {
        if (arr[i] > dp[len - 1]) {
            dp[len] = arr[i];
            len++;
        } else {
            int idx = Arrays.binarySearch(dp, 0, len, arr[i]);
            if (idx < 0) {
                idx = - (idx + 1);
            }
            dp[idx] = arr[i];
        }
    }
    System.out.println(len);
}
```

- `Arrays.binarySearch()`는 정확한 key 값을 찾지 못하면 `-(insertion point) - 1`을 반환한다. 해당하는 삽입 위치는 `-(반환값 + 1)`을 계산해서 찾을 수 있다.
- 현재 값이 부분수열의 가장 마지막 값보다 크면 추가한다. 작거나 같은 경우 이분 탐색을 통해 삽입 위치를 파악한 뒤 교체한다.

## 문제

https://www.acmicpc.net/problem/2352
