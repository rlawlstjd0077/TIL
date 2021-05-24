# 3.2 함수를 호출하기 쉽게 만들기

- Java와 비교하여 Kotlin 에서 함수를 정의하고 호출하는데 있어서 편리한 기능을 예를 들어 설명한다.
- 책의 내용을 요약하고자 했지만 .. 최근 작업 중에 적절한 예제가 있어 인용을 하고자 한다.

### 문제 상황

- sEventType Entity 조회시에 status, keyType 파라미터로 필터링을 수행하는 메서드 
  - 이때 status, keyType은 nullable 한 상황

```
    private fun getEventTypes(
        workspaceId: Long,
        status: EventType.Status?,
        keyType: EventKey.Type?
    ): List<EventType> {
        return eventTypeRepository.findByWorkspaceIdAndStatusAndKeyTypeOrderByCreatedAtDesc(
            workspaceId = workspaceId,
            status = status,
            keyType = keyType
    )
    
```

### 3.2.2 디폴트 파라미터 값 

- getEventTypes() 메서드를 사용하는 입장에서 필터 없이 모든 eventType을 가져오고자 한다고 가정해보자.
- 아래와 같이 작성할 수 있다.

```
fun getAllEventTypes(workspaceId: Long) {
  return getEventTypes(workspaceId, null. null)
}
```

- 다만 null을 넣어주는 게 맘에 들지 않는다. 
  - 만약 status 만 필터를 없애는 경우에는? keyType 만 필터를 없애는 경우에는? 전부 null 값을 넘기도록 구현해줘야 한다. 



- 이때 사용할 수 있는 것이 바로 **디폴트 파라미터** 이다.
- 코틀린에서는 함수 선언에서 파라미터의 디폴트 값을 지정해줄 수 있다. 
- 또한 디폴트 파라미터를 사용하게 되면 해당 파라미터에 대한 오버로딩 메서드를 만들지 않아도 되는 이점을 얻을 수 있다.

```
private fun getEventTypes(
        workspaceId: Long,
        status: EventType.Status? = null,
        keyType: EventKey.Type? = null
    ): List<EventType> {
    //중략
    
fun getAllEventTypes(workspaceId: Long) {
  return getEventTypes(workspaceId)
}
```



### 3.2.1 이름 붙인 인자

- 메서드가 늘어남에 따라서 파라미터에 대한 의미 유추가 힘들어질 수가 있다. 

```
fun getAllEventTypes(workspaceId: Long) {
  return getEventTypes(workspaceId)
}

fun getAllCustomEventTypes(workspaceId: Long) {
  return getEventTypes(workspaceId, EventKey.Type.CUSTOM)
}

fun getAllActiveEventTypes(workspaceId: Long) {
  return getEventTypes(workspaceId, EventType.Status.ACTIVE)
}

//more..
```



- 이럴 때 사용할 수 있는 것이 **Named Parameter** 이다.
- 보통 유지보수의 용이성을 위해서 인자 중 하나라도 이름을 명시 했다면 이후 모든 인자에 대해서는 이름을 명시해줘야 한다.

```
fun getAllCustomEventTypes(workspaceId: Long) {
  return getEventTypes(workspaceId = workspaceId, status = EventKey.Type.CUSTOM)
}

fun getAllActiveEventTypes(workspaceId: Long) {
  return getEventTypes(workspaceId = workspaceId, status = EventType.Status.ACTIVE)
}
```



### 3.2.3 정적 유틸리티 클래스 없이기 

- Java에서는 모든 코드를 클래스의 메소드로 작성해야 한다. 
- 실무에서는 어느 한 클래스에 포함시키기 어려운 코드들이 생기기 마련인데 그 결과 Util 과 같은 정적 메소드를 모아두는 클래스가 생겨난다. 
  - ex) JDK Collection



- 코틀린에서는 이런 무의미한 클래스가 필요가 없다. 
- 함수를 소스 파일의 최상위 수준에 정의하게 되면 다른 패키지에서 해당 함수가 정의된 패키지를 임포트 하여 사용할 수 있다. 

```
//join.kt
package strings

fun joinToString(...): String { ... }

// 컴파일 후 ..
package strings;

public class JoinKt {
  public static String joinToString(...) { ... }
}
```



- 위 함수는 컴파일시에 새로운 클래스를 정의해준다.
  - JVM이 클래스 안에 들어있는 코드만을 실행할 수 있기 때문에 
- 코틀린 컴파일러가 생성하는 클래스의 이름은 최상위 함수가 들어있던 소스 파일의 이름과 대응한다. 



- 함수와 마찬가지로 프로퍼티도 파일의 최상위 수준에 놓을 수 있다. 

```
var opCount = 0
fun performOperation() {
  opCount++
}

fun reportOperationCount() {
  println("Operation ... $opCount")
}
```



# 3.3 확장 함수, 확장 프로퍼티

- **확장 함수**는 어떤 클래스의 멤버 메소드인 것 처럼 호출할 수 있지만 그 클래스 밖에 선언된 함수이다. 
  - 기존 자바 API를 재작성하지 않고도 코틀린이 제공하는 여러 편리한 기능을 사용할 수 있게끔 해준다.