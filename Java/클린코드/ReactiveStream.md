# Reactive Stream

- 리액티브란 외부의 이벤트, 데이터가 발생했을 때 대응하는 식의 프로그램을 작성하는 것을 의미함
- Duality (쌍대성?)
- Observer Pattern
- Reactive Streams - Reactive의 표준 (Java9 API 포함됨)
- Iterable 을 구현한 객체라면 for-each 구문에 사용이 가능함
- 익명 object를 많이 쓰나 ?
- Iterable <--> Observable (duality)
  - 반대의 의미
- Iterable은 Pull 방식
  - next를 통해서 원하는 값을 가져오는 방식
- Observable은 Push 방식 
  - 데이터를 가져가라고 넣어주는 방식
- Observable 
  - 이벤트 소스 라고 생각하면 됨. 
  - Observer에게 이벤트를 던지는 소스 
  - Observer를 등록하고 Observer에게 notify를 하는 방식임
- Observer 패턴의 장점 ?
  - 정해져 있는 데이터 소스가 아니라 동적으로 데이터 소스 (키보드 입력, 디비 업데이트 등)를 받아 브로드캐스트해줄 수 있음 
  - 별개의 스레드에서 동작하는 코드를 손쉽게 만들 수 있다. (Iterable은 복잡해질 수 있음)
- Observable 패턴의 문제점? 
  1. Complete에 대한 개념이 없음 (notify 밖에 할 수 없기 때문에)
  2. Error 처리 (Exception이 발생하는 경우)
     - 일시적으로 Excption이 발생하는 경우 (복구할 수 있는 경우)
     - 비동기적으로 구현된 경우 예외 전파, 예외 처리에 대한 아이디어가 없음 (패턴에 녹여져 있지 않았음)
- 위 두가지 문제점을 보완하여 Reactive Streams를 개발한 것임

## Reactive Streams

- API 자체는 심플함
- 각 API별 지켜야할 스펙은 따로 정의가 되어 있음

### Publisher

- 무한한 순차적인 데이터를 전달하는 것 
- Publisher가 Subscriber에게 전달해줘야 하는 스펙 (가장 핵심)
  - onSubscribe (반드시)
  - onNext * (0 ~ N)
  - (onError | onComplete) ?
- onSubscribe 시에는 Subscription을 넘겨줌
- 스펙상으로는 하나의 스레드에서 순차적인 값이 넘어오는 형태로 되어 있다. 
  - Subscriber 

### Subscriber

- 구독 정보를 가진 Subscription을 관리하고 Subscription을 통해서 요청을 할 수 있음
- BackPressure ?
  - 둘 사이의 속도 차이가 있는 경우 조절하는 기술인데 R.S 에서는 Subscription을 사용하고 있음 
- `request` 를 통해서 요청 개수를 넘김
  - 단지 요청일 뿐이며 Publisher가 추후 onNext로 이벤트를 쏴줌 

### Scheduler

- 결국에는 동시성을 가지고 복잡한 작업을 처리하는 코드를 간결하게 표현할 수 있는 방법이기 때문이다. 
  - 세련된 방식으로 정밀하게 조율이 가능하다. 
- 

## Operator

- Transformer 
  - Publisher <-> Subscriber 사이에 데이터 변환을 Process를 해줄 수가 있다.
  - 데이터 흐름을 정의할 수 있다는 것임 
  - 중간에 들어가는 Operator를 의미함



##  Reactor

- Publisher 인터페이스를 구현한 Flux를 제공한다.
  - 세련된 Chaining 을 제공해줌 

- 