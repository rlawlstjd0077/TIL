# XOR 연산을 활용하는 방법

- 요구사항
  - 정수 배열이 주어지는 경우 하나의 정수를 제외한 나머지 정수는 모두 배열에 2번씩 존재한다. 
  - 이때 한번만 존재하는 정수를 구하라.
- XOR 연산을 이용한다면 순서는 상관 없다. 절차적으로 지워나간다.

```java
	public static void main(String[] args) {
        int result = doSolve(new int[]{5, 2, 4, 1, 5, 2, 4});
        System.out.println(result);
    }

    private static int doSolve(int[] ints) {
        int result = ints[0];
        for (int i = 1; i < ints.length; i++) {
            result = result ^ ints[i];
            System.out.println(result);
        }
        return result;
    }
```

