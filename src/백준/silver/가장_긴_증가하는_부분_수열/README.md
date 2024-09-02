## 핵심 포인트

- DP, LIS
- LIS는 주어진 수열에서 오름차순으로 정렬된 가장 긴 부분 수열을 찾는 알고리즘이다. 부분 수열은 원래 수열의 순서를 유지하면서 일부 원소를 제거하여 만들 수 있다.
- 수열의 각 원소에 대해, 그 원소를 마지막으로 하는 증가 부분 수열의 최대 길이를 계산한다. 이전 원소들과의 관계를 이용하여 현재 원소의 LIS 길이를 결정한다.
- 구현 방법은 DP(O(n^2))와 이진 탐색(O(nlogn))이 있다.

```java
private void solution(int n, int[] arr) {
    int[] dp = new int[n];
    int result = 0;

    for(int i=0; i<n; i++) {
        dp[i] = 1;
        for(int j=0; j<i; j++) {
            if(arr[i] > arr[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        result = Math.max(result, dp[i]);
    }
    
    System.out.println(result);
}
```

- 외부 루프에서 길이에 해당하는 `dp[i]`를 1로 초기화한다.
- 내부 루프에서 이전 원소의 크기보다 크다면 증가하는 부분 수열에 해당한다. 이전 원소까지의 길이인 `dp[j]`에 1을 더하여 현재 원소까지의 길이를 비교(`max(dp[i], dp[j]+1)`)한다.

## 문제 링크

https://www.acmicpc.net/problem/11053
