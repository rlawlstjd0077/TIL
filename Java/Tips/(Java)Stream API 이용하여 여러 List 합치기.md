# (Java)Stream API 이용하여 여러 List 합치기

## 들어가며

- Stream AP를 이용하여 여러 List를 합쳐야 하는 상황이 발생했다.

#### 문제 상황

- 아래와 같이 List를 value로 담고 있는 Map이 있고, Map이 담고 있는 모든 List를 합치고 싶은 상황이다. 

```java
Map<String, List<String>> testMap; 
```

------

## 해결방법

#### flatMap() 사용

```java
List<String> mergeList = testMap.values()
         .stream()
         .flatMap(list -> list.stream())
         .collect(Collectors.toList());
```

####  

