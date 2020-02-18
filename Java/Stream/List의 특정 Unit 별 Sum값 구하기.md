# List의 특정 Unit 별 Sum값 구하기

### 들어가며

-   List에 있는 Item들을 대상으로 고유한 ID별로 합계를 구해야 하는 상황이 발생했다.

#### 문제 상황

-   id와 duration을 가지는 MyClass가 있고 MyClass List을 받아 id별 duration의 합을 가지는 Map을 반환해야 한다. 

```
class MyClass {
   public String id;
   public Float duration;
}
 
private Map<String, Float> sum(List<MyClass> list) {
    return ????
}
```

---

### 해결방법

-   일단 코드부터 보자면 아래와 같다.

```
private Map<String, Float> sum(List<MyClass> list) {
    return list.stream().collect(Collectors.toMap(e -> e.id, e -> e.duration, Float::sum));
}
```

-   좀더 자세하게 보자면 Collector.toMap() 메서드는
    -   첫번째 인자에 의해서 Key가 결정되는 맵을 작성함
    -   두번째 인자는 맵의 Value에 스트림 요소를 매핑함
    -   **세번째 인자는 Key에 충돌이 있는 경우 Value를 merge하는 연산자**
-   세번째 인자에 Float::sum 연산자를 넘겨줌으로써 id가 동일한 경우 duration을 합산하도록 Map을 작성한 것이다.

---

### 참고

- https://stackoverflow.com/questions/44348207/java-8-stream-sum-of-units-of-same-items