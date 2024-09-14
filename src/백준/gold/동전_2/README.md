## 핵심

- dp
- i원을 만들기 위한 최소 동전 개수를 `dp[i]`라 한다.
- `dp[0]`를 제외한 모든 원소를 max로 초기화한다.
- `dp[i] = min(dp[i], dp[i-coin] + 1)`이다. `coin`은 현재 사용 가능한 모든 동전 종류이다.
- `dp[i-coin] == max`인 경우 만들 수 없는 조합이므로 넘긴다.

## 문제

https://www.acmicpc.net/problem/2294
