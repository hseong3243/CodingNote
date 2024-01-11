## 핵심 포인트

- 2차원 배열의 누적합
- (r1, c1)부터 (r2, c2)까지의 합을 모두 연산하며 진행하면 O(N*M*K)의 시간이 걸린다.
- 따라서 (ri, c1)과 (ri, c2 + 1)에 대해 누적합을 적용한다.
- 다시 (r1, ci)와 (r2 + 1, ci)에 대하여 누적합을 적용하면 2차원 배열에 대한 누적합을 구할 수 있다.

## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/92344

## 해설 링크

https://tech.kakao.com/2022/01/14/2022-kakao-recruitment-round-1/
