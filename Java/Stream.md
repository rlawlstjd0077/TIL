## map 메서드
- 스트림에 있는 값들을 특정 방식으로 변환하여 새로운 스트림으로 반환한다.
### EXample
    //String List에서 가장 작은 length를 가진 String의 size를 반환하는 코드
    stringList.stream().mapToInt(String::length).min().getAsInt();