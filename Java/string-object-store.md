# String 객체는 어떻게 저장될까?

- 사실 주제 자체는 굉장히 추상적입니다. 어떤 Scope 에서 이야기 하느냐에 따라서 많이 달라질 것 같네요
- 본 글에서는 다소 고수준에서 String Literal / String Object를 비교하는 식으로 내부에서 어떻게 처리되는지를 살짝 맛볼 예정입니다.
  - 그 이유는 제 수준이 현재 그정도라고 감히 말씀드릴수 있을 것 같 ..

## String 클래스

- 보통 String 객체를 생성할 때 아래와 같이 선언합니다.

```java
String str = "Kim";
String str1 = new String("Kim");
```

- String 클래스를 한번 볼까요?
- String 클래스는 value 라는 byte(char) 배열을 가지고 있으면서 이 배열은 값을 저장합니다.

```java
package java.lang;

public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence,
               Constable, ConstantDesc {

		//~ Java8
    private final char[] value;    
    //Java9 ~ 
		private final byte[] value;
		.... 생략
```

- 그럼 메모리에서는 아래와 같이 저장됩니다. value는 메모리 공간에 할당된 배열을 참조합니다.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1c63ac27-ce0a-4f1c-902d-f85f1d756936/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1c63ac27-ce0a-4f1c-902d-f85f1d756936/Untitled.png)

## String Pool

- 위에서 살펴본 String 선언 예제에서 `str`, `str1` 은 메모리에 적재되는 형태가 다릅니다.

```java
//String Literal
String str = "Kim";
//String Object
String str1 = new String("Kim");
```

### String interning

- 먼저 String Literal을 살펴봅시다.
- Java의 String은 불변성(immutability) 이라는 특성이 있어서 JVM은 String 객체에 대한 메모리 할당을 최적화 할 수 있습니다.
- 메모리 내부에 String pool 이라는 공간을 만들어 두고 고유한 리터럴 문자열을 저장하여 이를 참조하여 사용하도록 하는 방법을 사용해서 말이죠.
  - 이 과정을 **String Interning** 이라고 합니다.
- 동일한 문자열을 가지는 String 리터럴을 선언하는 예제를 확인해보겠습니다.
- 기본적으로 String 변수를 생성하고 문자열을 할당 (리터럴) 하게 되면 JVM 내부에서는 문자열 값이 String Pool 에 존재하는지 확인합니다.
  - 이때 발견이 된다면 Java 컴파일러는 해당 문자열 리터럴의 주소에 대한 참조를 반환
  - 찾지 못한다면 해당 문자열의 리터럴을 Pool에 저장한 후에 참조를 반환

```java
String str1 = "Kim";
String str2 = "Kim";

//두 String 객체의 주소(Heap에 할당된)는 동일합니다. 
assertThat(str1).isSameAs(str2);
```

### String object

- String 생성자를 이용해서 String 객체를 생성하는 경우를 살펴봅시다.
- 기본적으로 `new` 키워드가 등장을 하면 Java 컴파일러는 새로운 객체를 heap 공간에 할당하여 저장합니다.
- 그렇기 때문에 `new` 로 생성된 String 객체들은 각자 Heap 메모리상에 적재되게 됩니다.

```java
String str1 = "Kim";
String str2 = new String("Kim");

//두 String 객체의 주소(Heap에 할당된)는 다릅니다. 
assertThat(str1).isNotSameAs(str2);
```

### String Literal VS String Object

- 결론적으로 Literal 선언이 재사용을 하기 때문에 new로 생성하는 것 보다는 빠릅니다.

  - 다만 큰차이는 나지 않습니다 ..

- 그래서 String에서 제공하는 

  ```
  intern()
  ```

   이라는 메소드를 통해서  new로 생성된 String 객체를 String pool에 넣을 수는 있습니다.

  - 다만.. `intern()` 이 생각보다 비싸서  오히려 부작용이 생길수 있습니다.
  - 자세한 내용은 아래 블로그를 참고하셔도 좋습니다.

https://blog.ggaman.com/918

### String Pool G/C

- Java 버전업이 String Pool 에도 변화가 있었습니다.
- Java7 이전에는 String Pool 은 **Perm** 이라는 고정된 사이즈 영역에 할당되어 GC 대상도 아니었으며interning 되는 문자열이 많아지면 OOM이 발생할 여지도 있었습니다.
- Java7 이후에 String Pool은 Heap 영역에 저장되어 GC 대상이 되었습니다.
  - 또한 Java에서는 String Pool에 대한 여러 옵션(사이즈 등)도 제공하여 아래 글을 참고하시면 됩니다.

https://www.baeldung.com/java-string-pool#performance-and-optimizations

- 여담이지만 Java9 부터는 문자열 형식에 따라 char

  

   byte

  

   사이의 적절한 인코딩을 선택하여 처리한다고 합니다.

  - 이를 **Compact String** 이라고 합니다.

- 이를 통해서 Heap 메모리를 절약하여 GC 성능을 좋게 만들수 있다고 합니다.

```java
//String 클래스 생성자 일부
String(char[] value, int off, int len, Void sig) {
        if (len == 0) {
            this.value = "".value;
            this.coder = "".coder;
            return;
        }
				//COMPACT STRING
        if (COMPACT_STRINGS) {
            byte[] val = StringUTF16.compress(value, off, len);
            if (val != null) {
                this.value = val;
                this.coder = LATIN1;
                return;
            }
        }
        this.coder = UTF16;
        this.value = StringUTF16.toBytes(value, off, len);
    }
```

## Advanced

- 한가지 흥미로운 점이 생겼습니다.
- 아래 예제를 보고 나서 아래의 그림을 보면 헷갈리기 시작합니다.

```java
String str = "Kim";
String str1 = new String("Kim");
System.out.println(System.identityHashCode(str));
System.out.println(System.identityHashCode(str1));
//366712642
//1829164700

System.out.println(System.identityHashCode(str.value));
System.out.println(System.identityHashCode(str1.value));
//1347137144
//1347137144 
```

- 분명 "Cat" (예제의 "Kim")은 다른 객체로 생성이 되었는데 char[] value는 같은 객체일까.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b4e23642-dabe-4cb7-b526-17ff0835bd00/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b4e23642-dabe-4cb7-b526-17ff0835bd00/Untitled.png)

### Detail

- 자세하게 확인해보죠.

- ```
  str1
  ```

   을 new 하는 시점의 String 클래스 생성자는 아래 예제와 같습니다.

  - 파라미터로 `original` 이라는 String 객체를 받아서 해당 객체의 값을 동일하게 참조하게 됩니다.

- ```
  str
  ```

  이 할당되고 난후 

  ```
  str
  ```

  의 주소를 출력하고 

  ```
  str1
  ```

   의 생성자 에서 전달받은 

  ```
  original
  ```

   의 주소를 출력해보겠습니다.

  - 물론 String 클래스를 수정할 수는 없으니 .. Debugger를 활용하여 확인 해봅니다.

```java
public static void main(String[] args) {
        String str = "Kim";
				System.out.println(System.identityHashCode(str));
				//1347137144
        String str1 = new String("Kim");        
}

//str1을 생성하는 String 생성자
public String(String original) {
				System.out.println(System.identityHashCode(original));
				//1347137144
        this.value = original.value;
        this.hash = original.hash;
}
```

- 결과를 보시면 주소가 같은 것으로 보아 `str` 객체가 `str1` 의 생성자에 할당된 것을 확인할 수 있습니다.

- 더 자세히 확인하기 위해서 바이트 코드를 확인해봅시다.

  - 끔찍하지만 한번 봐보죠 ..

- 일단 Constant pool은 상수 풀을 의미합니다. 보게 되면 #2 에서 'Kim' 이라는 값의 String 변수가 할당이 되었습니다.

- 아래의 0:, 7: 을 보게 되면 #2("Kim") 변수를 스택에 할당하는 것을 확인할 수 있습니다.

  - ldc - 상수를 스택으로 푸시하는 OP Code

- 즉, 컴파일 후에 "Kim" 이라는 값의 String 객체가 String Pool에 할당이 된 후 main 메서드의 상수 풀에 할당되고 아래의 과정으로 

  ```
  str
  ```

  , 

  ```
  str1
  ```

   이 초기화 되는 것입니다.

  - str 에는 미리 등록되어 있는 "Kim" 이라는 String 객체를 바로 할당
  - str1에는 new 연산을 통해서 객체를 생성할 때 파라미터로 미리 등록되어 있는 "Kim" 이라는 String 객체를 전달

```java
Constant pool:
   #1 = Methodref          #9.#26         // java/lang/Object."<init>":()V
   #2 = String             #27            // Kim
   ... 생략 ... 
  #27 = Utf8               Kim
	 ... 생략 ... 
{
  public com.jinseong.soft.Application();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 5: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/jinseong/soft/Application;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=3, locals=3, args_size=1
         0: ldc           #2                  // String Kim
         2: astore_1
         3: new           #3                  // class java/lang/String
         6: dup
         7: ldc           #2                  // String Kim
         9: invokespecial #4                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
        12: astore_2
        13: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
        16: aload_1
        17: invokestatic  #6                  // Method java/lang/System.identityHashCode:(Ljava/lang/Object;)I
        20: invokevirtual #7                  // Method java/io/PrintStream.println:(I)V
        23: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
        26: aload_2
        27: invokestatic  #6                  // Method java/lang/System.identityHashCode:(Ljava/lang/Object;)I
        30: invokevirtual #7                  // Method java/io/PrintStream.println:(I)V
        33: return
      ... 생략 ... 
}
```

### Conclusion

- 결론적으로 String 객체를 문자열 리터럴을 감싸는 래퍼 클래스로 볼 수 있으며 String 객체는 생성 방식에 따라 재사용 될 수도 있고 Heap에 개별 객체가 생성될 수도 있습니다.
- 다만 같은 문자열 값인 경우 **원본 문자열(char[] 배열) 객체**는 String Pool에 존재하는 객체를 사용(참조)한다는 것입니다.
- 그림으로 한번 정리해보았습니다.
  - ':String' 은 Heap에 할당된 인스턴스라는 의미입니다.
  - 'Kim' 배열의 경우 명확하진 않지만 String Pool 내에 저장이 되는 것을 표현하기 위해서 String Pool 내부에 그려놓았습니다.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4c83229f-3483-42f7-82eb-4d0f8bb7a3d9/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4c83229f-3483-42f7-82eb-4d0f8bb7a3d9/Untitled.png)

### 참고

https://www.baeldung.com/java-string-pool#performance-and-optimizations

https://cdn.journaldev.com/wp-content/uploads/2012/11/String-Pool-Java1.png

https://stackoverflow.com/questions/2486191/what-is-the-java-string-pool-and-how-is-s-different-from-new-strings/2486201#2486201