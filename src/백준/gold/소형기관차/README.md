## 핵심

- dp, 누적합
- 객차의 개수 n은 50,000이다. 따라서 O(n^2)보다 작은 시간 복잡도를 가져야 한다.
- m개의 객차만큼 승객 수를 계산하기 위해 누적합을 이용한다.
- dp를 이용할 경우 현재 k개의 객차를 이용했을 때 태울 수 있는 최대 승객 수는 `dp[i][k]`이다.
- `dp[i][k]`는 직전 호차까지 태운 최대 승객 수 `dp[i-1][k]`와 소형기관차를 사용했을 때 태울 수 있는 최대 승객 수 `dp[i-m][k-1] + arr[i] - arr[m]`중 큰 값이다.
- 즉, `dp[i][k] = max(dp[i-1][k], dp[i-m][k-1] + arr[i] - arr[m]`이다.

## 문제

https://www.acmicpc.net/problem/2616
