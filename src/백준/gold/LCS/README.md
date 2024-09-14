## 핵심

- dp, LCS
- 기본 원리(by claude 3.5)
  1) LCS(i, j)는 문자열A의 처음 i개 문자, 문자열B의 처음 j개 문자 사이의 LCS 길이이다.
  2) `문자열A[i] == 문자열B[j]`라면 `LCS(i, j) = LCS(i-1, j-1) + 1`이다.
  3) `문자열A[i] != 문자열B[j]`라면 `LCS(i, j) = max(LCS(i-1, j), LCS(i, j-1))`이다.
  4) 이때, `LCS(i,0) = 0`, `LCS(0,j) = 0`이어야 한다.

## 문제

https://www.acmicpc.net/problem/9251
