# String의 '+' 연산은 내부에서 어떻게 이루어질까?

- Java String을 다루다 보면 문자열 조합을 위해서 `+` 연산자를 사용하는 경우가 많습니다.
- 아래 예제를 실행하게 되면 내부에서는 어떤 동작이 이루어질까요?

```java
public class Application {
  public static void main(String[] args) {
    String s1 = "afas";
    String s2 = "asfasfaasf";
    String s3 = s1 + s2;
    System.out.println(s3);
  }
}
```

### 검색을 해보자.

- StackOverFlow를 찾아보니 이런 내용이 있습니다.

> - 연산자는 Java 컴파일러에서 구현이 되며 String + String 연산은 컴파일 타임에 상수 혹은 StringBuilder 코드로 변환된다.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/46331621-afd7-44d0-a6fa-81d63e556e7b/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/46331621-afd7-44d0-a6fa-81d63e556e7b/Untitled.png)

- 정말인지 확인해보겠습니다.

### Byte Code를 까보자.

- 위 코드를 컴파일 하고 클래스 파일을 javap 를 이용하여 바이트 코드를 확인해볼 수 있습니다.
  - 참고로 JDK 1.8 환경입니다.

```java
> javac Application.java
> javap -c Application.class
```

- 우선 main 메서드의 0:, 3: 라인에서 `s1`을 "afas", `s2`를 "asfasfaasf"로 할당하는 것을 확인해볼 수 있습니다.
  - `ldc`는 상수를 실행 스택에 푸쉬 하는 OP Code 이며 문자열은 String Pool에 저장된 뒤에 가져와지는 방식
  - String Pool, String 객체 생성 방법에 대한 부분은 아래 포스팅을 참고하시면 좋습니다.
- 6: 라인에서 StringBuilder 객체가 생성되고 14:, 18: 라인에서 StringBuilder의 append 메소드가 호출되고 (흠 .. 어떤 객체가 전달되는지는 알수 없군요) 21: 라인에서 StringBuilder의 toString() 메소드가 호출됩니다.
  - `invokevirtual` 은 인스턴스 메소드를 호출하는 OP Code 입니다.

```java
public class com.jinseong.soft.string.Application {
  public com.jinseong.soft.string.Application();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: ldc           #2                  // String afas
       2: astore_1
       3: ldc           #3                  // String asfasfaasf
       5: astore_2
       6: new           #4                  // class java/lang/StringBuilder
       9: dup
      10: invokespecial #5                  // Method java/lang/StringBuilder."<init>":()V
      13: aload_1
      14: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      17: aload_2
      18: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      21: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      24: astore_3
      25: getstatic     #8                  // Field java/lang/System.out:Ljava/io/PrintStream;
      28: aload_3
      29: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      32: return
```

- 문맥상 컴파일 타임에 `String s3 = s1 + s2;` 코드는 아래처럼 변환되는 것을 유추해볼 수 있겠군요.

  ```java
  StringBuilder builder = new StringBuilder();
  builder.append(s1);
  builder.append(s2);
  String s3 = builder.toString();
  ```

- 결론적으로 위 StackOverflow 답변 내용이 맞다는 것을 알 수 있었습니다.