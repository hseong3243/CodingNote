## 핵심

- dp
- 현재 위치의 최댓값 `max[i][j]`는 이동 가능한 이전 위치의 최댓값 `max(max[i-1][j], max[i-1][j+?]) + map[i][j]`이다.
- 최솟값도 동일한 방식으로 구할 수 있다.

## 문제

https://www.acmicpc.net/problem/2096
