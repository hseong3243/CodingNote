## 핵심 포인트

- 이분 탐색
- 높이에 따라 사용해야할 포인트를 정렬하면 임의의 높이 target를 향해서 감소하거나 증가한다. 따라서 이분 탐색을 이용하여 구한 임의의 높이 h의 포인트 사용량이 h+1의 포인트 사용량보다 크다면 `min = mid + 1`, h+1의 포인트 사용량보다 크다면 `max = mid - 1`로 이분 탐색을 진행할 수 있다.

## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/12984
