## 핵심

- dp
- 점화식은 `dp[begin][end] = min(dp[begin][end], dp[begin][e] + dp[e+1][end] + sum[end] - sum[begin-1]`
  이다.
- 즉, 각 구간의 누적 비용 + 새로운 비용이다. 
- `sum[i]`는 i번째 수까지의 누적합이다.
- 루프를 돌리는 것이 까다롭기 때문에 천천히 적으면서 푸는 것이 좋다.

## 문제

https://www.acmicpc.net/problem/11066
