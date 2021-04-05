# 중간 값을 구하는 여러 방법

```java
int a = 21억
int b = 21억

//첫번 째 방법
int mid = (a + b) / 2; //오버플로우 발생 !!!

//두번 째 방법
int mid = a + (b - a) / 2; //!!!!!

//세번 째 방법
int mid = (start + end) >>> 1; //이게 간지
```

