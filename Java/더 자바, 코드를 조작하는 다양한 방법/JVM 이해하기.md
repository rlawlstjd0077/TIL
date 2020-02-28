# JVM 이해하기

## 자바 JVM, JDK, JRE

------

![img](https://lh5.googleusercontent.com/JBPBku4INfsSlclF2b2RRSfQ6333ybJ22sC81BPNA9xVdCcNPUItUt_COAwuqn_wv4FUY_hE5D-8BrdP2p9Dd1x50F53PoCt2QDhJ8u1FC0gQi5YsE_yetxZPf4PdFZQYNJQE5yR)

### JVM (Java Virtual Machine)

- **자바 가상 머신**
- 자바 바이트 코드(.class 파일)를 OS에 특화된 코드로 변환(인터프리터와 JIT 컴파일러)하여 실행함
- JVM 스펙([링크](https://docs.oracle.com/javase/specs/jvms/se11/html/))은 정해져 있고 이에 맞춘 구현체(JVM 밴더)는 다양하게 존재함 (오라클, 아마존, Azul, 등)
- 플랫폼에 종속적임
  - 실행시 네이티브 코드로 변경을 하게 되는데 이를 OS에 맞추어 실행해야 되기 때문

### JRE (Java Runtime Environment)

- 자바 애플리케이션을 실행하기 위한 최소한의 구성 (JVM + 라이브러리)
- JVM과 핵심 라이브러리 및 자바 런타임 환경에서 사용하는 프로퍼티 세팅이나 리소스 파일을 가지고 있음
- 오라클은 자바 11부터는 JDK만 제공하며 JRE를 따로 제공하지 않음

### JDK (Java Development Kit)

- JRE + 개발에 필요한 툴
- 소스 코드를 작성할 때 사용하는 자바 언어는 플랫폼에 독립적임

### Java

- 프로그래밍 언어
- JDK에 들어있는 자바 컴파일러(javac)를 사용하여 바이트코드(.class파일)로 컴파일 할 수 있음
- 자바 유료화?
  - 오라클 JDK가 11버전 부터 상용으로 사용할 때 유료임

### JVM 기반 언어

- JVM은 Java와 의존성이 타이트 하지 않음
  - .class 파일만 있으면 실행이 되기 때문
- 즉, 다른 언어에서 컴파일시 class파일이 만들어진다거나 java 파일을 만들어주기만 하면 JVM을 활용가능함
  - 클로저, 그루비, JRuby, Jython, Kotlin, Scala 등등

## JVM 동작 방식

------

![img](https://lh6.googleusercontent.com/rvA_tW7RQKCQQ0e7Kb2dQ5coQ8ogwVxdaKVWmgjVBc6HCgb2hCeUFqXVse55R1y0VPNZaCLuZecDe3Y6NISrvugohqdVYj6IjVKY0Bs_TzpancL8-xBi86rhlHXEonPL598frjjo)

### 클래스 로더 시스템

- .class 파일에서 바이트코드를 읽고 메모리에 저장함
- 로딩
  - 클래스를 읽어오는 과정
- 링크
  - 레퍼런스를 연결하는 과정
- 초기화
  - static 값들 초기화 및 변수에 할당

### 메모리

- 메소드영역
  - 클래스 수준(클래스 이름, pacakege Path, 부모 클래스 이름, 메서드, 변수)를 저장함
  - 공유자원으로, 다른 영역에서도 참조 가능
- 힙 영역
  - 객체(인스턴스)를 저장함
  - 공유자원으로, 다른 영역에서도 참조 가능
- 스택 영역
  - 스레드 마다 런타임 스택을 만들고 그 안에 스택 프레임(메서드 호출)을 쌓음
  - 스레드를 종료하면 함께 사라짐
- PC (Program Counter) 레지스터
  - 스레드 마다 현재 실행할 스택 프레임을 가리키는 포인터가 생성됨
  - 스택에 맞물려 생성됨
- 네이티브 메소드 스택
  - 네이티브 메소드를 호출 할때 사용하는 별도의 스택
  - 네이티브 메소드 라이브러리를 사용하기 위해서 JNI를 사용하는 메소드 스택은 네이티브 메소드 스택에 저장이 된다 .
  - 네이티브 메소드: 메서드에 Native라는 키워드가 붙어 있고 그 구현을 java가 아닌 C나C++이 구현을 한 형태

### 실행 엔진

- 인터프리터: 바이트 코드를 한줄 씩 실행하는 역할
  - 인터프리터가 이해 할 수 있음 한 줄 한 줄씩 실행 하면서 네이티브 코드로 바꿔서 기계가 이해할 수 있개끔하여 실행
  - 똑같은 코드가 여러번 나와도 매번 실행하는게 비효율 적임
- JIT 컴파일러: 바이트 코드를 네이티브 코드로 컴파일 해주는 것
  - 반복되는 코드들을 전부 찾아 미리 네이티브 코드로 바꿔 놓음
  - 그 다음에 인터프리터가 해당 라인을 만나면 인터프리팅 하지 않고 바로 네이티브 코드를 바로 사용함
- GC: 더 이상 참조되지 않는 객체를 모아서 정리
  - 이해도 해야 하고 경우에 따라 커스터마이징을 해야 할 수도 있음 (옵션을 조정)
    - 프로파일링을 할때도 사용하는 GC가 어떤 GC인지
    - 애플리케이션을 실행하기 전에 우리가 사용할 GC를 선택해야 할 경우도 있음
  - 대표적인 GC
    - throughput 위주
    - stop-the world 위주
      - 서버 운영중에 많은 객체를 생성하고 반응 시간이 중요하다
      - GC를 할 때 발생하는 멈춤 현상을 최소화 할 수 있는 GC

### JNI

- 자바 애플리케이션에서 C, C++, 어셈블리로 작성된 함수를 사용할 수 있는 방법 제공
- Native 키워드를 사용한 메소드 호출

### 네이티브 메소드 라이브러리

- C, C++로 작성 된 라이브러리

### 전체적인 동작 방식

1. 바이트코드를 전달받아 클래스로더가 메모리영역에 배치
   - 힙, 메소드 영역에 적절하게 배치
2. 실행엔진에서 스레드가 만들어짐에 따라 스택, PC, 네이티브 스택 생성
   - JIT을써서 컴파일이 되고 GC도 이루어짐
   - 만약 네이티브 메소드를 사용하게 되면 JNI를 통해서 네이티브 메소드 사용

## 클래스 로더

![img](https://lh5.googleusercontent.com/0Et98u8-Uqifmb8kUdoMbuJlN836o1tS789BCHo4CVr0Gr6OMPaGcklZFTj1eI1AiSzYUOe2dVvwr4hKRTuPAlG3qlL0nPiXa3H5ZXWtJaXA6Noby5vgviCFLfwnfGTLWWPpAd-m)

- 로딩, 링크, 초기화 순으로 진행된다.

### 로딩

- 클래스 로더가 .class 파일을 읽고 그 내용에 따라 적절한 바이너리 데이터를 만들고 "메소드" 영역에 저장
- 메소드 영역에 저장하는 데이터
  - FQCN (Fully Qualified Class Name) - Full package 경로, Class 이름
  - 클래스, 인터페이스, Enum 인지?
  - 메소드와 변수
- 로딩이 끝나면 해당 클래스 타입의 Class 객체를 생성하여 "힙" 영역에 저장
  - 즉, 클래스 타입 고유의 객체를 생성하는 것 Class <MyType>

### 링크

- Verify, Prepare, Resolve(optional) 세 단계로 나누어져 있음
- Verify: .class 파일 형식이 유효한지 체크함
- Preparation: 클래스 변수(static 변수)와 기본값에 필요한 메모리를 준비함
- Resolve: 심볼릭 메모리 레퍼런스(외부 클래스 참조)를 메소드 영역에 있는 실제 레퍼런스로 교체함 (Optional)

### 초기화

- static 변수의 값을 할당함 (static 블럭이 있다면 이때 실행됨)

### 클래스로더의 형태

- 클래스 로더는 계층 구조로 이루어져 있으며 기본적으로 세가지 클래스 로더가 제공됨

  - 부트 스트랩 클래스로더

    - JAVA_HOME\lib에 있는 코어 자바 API를 제공함
    - 최상위 우선순위를 가진 클래스로더

  - 플랫폼 클래스로더

    - JAVA_HOME\lib\ext 폴더 또는 java.ext.dirs 시스템 변수에 해당하는 위치에 있는 클래스를 읽음

  - 애플리케이션 클래스로더

    - 애플리케이션 ClassPath(실행할 때 주는 -classpath 옵션 또는 java.class.path 환경 변수의 값에 해당하는 위치)에서 클래스를 읽음

  - 클래스 로더 확인해보기

    ```java
    ClassLoader classloader = MyClass.class.getClassLoader(); //App Class Loader 
    System.out.println(classloader); //Platform Class Loader 
    System.out.println(classloader.getParent()); //Bootstrap Class Loader, 네이티브 코드로 구현되어 있어 자바코드에서 참조하여 출력할 수 없음 
    System.out.println(classloader.getParent().getParent());
    ```

  - 클래스로더는 최상위에서 부터 클래스를 읽어 오는데 클래스를 못 읽어 오면 "ClassNotFoundException"이 발생하게됨