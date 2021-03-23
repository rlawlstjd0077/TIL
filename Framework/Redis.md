# Redis

## 개요

> 외부의 프로세스로 동작하는 메모리 기반의 키-값 구조 관리 서버

- Remote Dictionary Server
  - 외부에 있는 Key, Value 쌍의 자료구조, 서버
- 주로 Database, Cache, Message Broker 로 쓰인다.

## Cache

- 결과를 미리 저장했다가 빠르게 사용하는 것
- 메모리 계층 구조

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0701b42c-5ccd-4267-b9f6-8189cd6421d7/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0701b42c-5ccd-4267-b9f6-8189cd6421d7/Untitled.png)

- ex) 12MV Cache Memory, 16GB DRAM, 512GB SSD
  - 12MB Cache Memory (가장 빠름, 비쌈)
  - 16GB DRAM (휘발성)
  - 512GB SSD (비교적 저렴)
- 레디스가 나온 이유
  - 기술이 발전하면서 하드웨어들이 커지다 보니 DRAM으로 데이터를 저장하고 접근하면 어떨까?
  - 더 자주 접근하고 덜 자주 바뀌는 데이터를 저장하자

## Redis

### Redis의 특징

- 5가지 자료구조 Collection 을 제공한다. (Redis - Java 형식)
  - String - Map<Key, Value>
    - 가장 일반적인 형태로 key, value 쌍을 저장하는 형태
  - List - LinkedList
  - Set - HashSet
  - Sorted Set - Tree Set
    - 유저 랭킹보드 서버 같은 구현에 사용할 수 있음
  - Hash - Redis만의 자료구조

### Redis가 해결하는 문제

- 기존의 문제
  - 서버가 여러대인 경우 Consistency의 문제 발생
    - 데이터 동기화가 되지 않는 문제
    - ex) 세션 저장의 경우
  - Multi Thread 환경에서 Race Condition 발생
    - Race Condition
      - 여러 개의 Thread가 경합을 벌이는 것
      - Context Switching에 따라 원하지 않는 결과가 발생
- Redis 에서는?
  - 기본적으로 Single Thread 이다.
    - 개발의 단순화, 사용의 단순화를 위해서
  - Redis 자료구조는 Atomix Critical Section 에 대한 동기화를 제공
    - Critical Section은 동시에 프로세스 여러개가 접근하면 안되는 영역

### Redis를 사용하는 곳

- 여러 서버에서 같은 데이터를 공유할 때
- 여러 서버가 아니더라도 Atomic 자료구조 & Cache 기능을 활용할 때

### Redis를 사용할 때 주의해야 할 점

- Single Thread 서버이므로 시간 복잡도를 고려해야 함
  - KEYS, Flush, GetAll 등 전체 조회 O(n) 은 조심해야 함
- In-Memory 특성상 OS, Memory 파편화 지식이 필요함

### Redis과 관련하여 알아둬야 하는 지식들

- Memory 파편화
  - 가용 메모리의 크기 때문에 실 사용되는 메모리가 적어질 수 있다.
  - Memory를 적당하게 여유있게 잡아주는 등의 메모리 관리를 요함
- Virtual Memory - Swap
  - 프로세스를 전부 올리지 않고 일부만 메모리에 올리고 덜 쓰이는 프로세스의 메모리는 디스크에 저장했다가 필요할 때 올리는 방식을 택함
- Replication - Fork
  - 휘발성 때문에 유실될 문제를 해결하기 위해서 복제 기능을 제공함
    - Slave Redis Server, Disk에 저장하는 방식 선택
  - 이 과정에서 Fork 로 프로세스상에서 동일하게 복제해서 사용하는데 이 과정에서 메모리가 차있다면 서버가 죽는 현상이 발생할 수도 있음

참고

- https://www.youtube.com/watch?v=Gimv7hroM8A
- https://velog.io/@hyeondev/Redis-란-무엇일까