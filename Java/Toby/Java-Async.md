## 자바 에서의 비동기
자바에서는 Future 가 가장 기본이 되는 인터페이스임. 
비동기 적인 무엇인가에 대한 연산, 작업에 대한 결과를 가지고 있는 것임. 
ExecutorService 자체가 스레드로 띄워져 있음. 

스레드에서 예외가 발생된다면? Main 스레드로 예외가 전파 되는게 핵심임 
콜백으로 예외를 던져주는게 훨씬 우아하다. (그래서 예외용 콜백을 별도로 두는 것으로 보임.)

인터럽트 익셉션의 경우 뭔가 조치가 필요하기 보다는 인터럽트 되었다는 시그널을 보내주는 것이 중요함. 
그래서 보통 currentThread interrupt를 호출함.

@Async를 쓰는 경우 ?
- 아주 긴 작업을 하는 경우 ? or 
	- 결과를 데이터 베이스에 넣고 궁금하면 DB를 엑세스 해보는 방식 (제일 쉬운 방식)
	- Http Session에 Future 핸들러를 넣고 원할때 꺼내 보는 방식 (10년전 방식) - 비용이 비교적 저렴
or 리스너로 처리를 하고 싶다 ?

기본적으로 자바의 Thread Pool 은 Executor를 기반으로 구현이 되어 있음. 
스레드를 만드는 것 자체는 많은 리소스를 먹는다. 
기본 Executor는 SimpleAcyncTaskExecutor 를 사용하는데 매우 구림. 
설정
- core size: 기본적으로 가지고 있는 스레드 개수
- queue capacity: core 이상으로 작업이 있는 경우 쌓이는 큐 사이즈
- max pool size: 큐 이상으로 들어오는 경우 늘릴 스레드 최대 개수 

서블릿 3.0 이 나오면서 혁명적인 변화가 있었음
- 비동기 서블릿 매커니즘임 !

블로킹 서블릿에서는 스레드당 connection 하나
- Http Connection Http Request, Response 내부에서 InputStream을 쓰게 되어 있기 때문에 블로킹 I/O 이기 때문임.

블로킹 I/O 가 많으면 CPU 자원을 많이 소모함 
- 단순히 Blocking이 아니라 CPU는 일을 하지 않는 상태로 보고 다른 스레드를 끌어와서 작업을 하기 때문에 C.S 가 2번이나 일어남

그럼 NIO는 ?
- 커넥션 자체는 NIO 이지만 그 뒤에 스레드 매핑을 일일이 해줘야 했었음

!!! Servlet 스레드 -> WorkThread 로 제어권이 넘어간 상황에서 Servlet 스레드를 놀게 하고 싶지 않은 것임.  (비동기 서블릿 기술을 이용해서)
스레드가 많아진다는 것은 컨테스트 스위칭에 많은 자원을 사용을 하기 때문에 자원 소모가 심해진다는 뜻임. 

서블릿 3.1 에서 논블록킹 IO가 되면서 완벽히 지원 할수 있었짐

1. NIO Conenctor 가 요청을 다 받음
2. 서블릿 스레드풀에서 스레드를 가져와 요청을 넘김 
3. 작업이 필요한 경우 작업 스레드 풀에서 스레드를 가져와 작업을 넘기고 바로 리턴
4. 응답처리는 비동기 서블릿 엔진에서 AsyncContext 를 서블릿 스레드에 넘겨준 뒤 Connector가 바로 반환을 함


예제 ... 
- 요청, 응답에 사용된 nio 스레드(서블릿 스레드)를 보면 다름 

awaitTermination 을 쓰면 Executor Service 가 종료될때까지 대기를 함 


결론적으로 Worker Thread 에서 긴 시간 수행해야 될 작업이 있고 일반 작업이 있는 경우 효과적임 그렇지 않다면 큰 의미는 없음


### 비동기 RestTemplate & MVC/Servlet

사용량 데이터 

