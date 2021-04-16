# Kafka

- 카프카는 고가용성, 낮은 지연과 높은 처리량을 통해서 효과적인 데이터 처리를 제공하는 메시징 큐이다.

## 배경

- 전통적인 단방향 Source Application ↔ Target Application 데이터 전송의 경우 그 수가 많아지면 복잡해지고 배포, 장애에 대응하기가 힘들어지는 단점이 있음
  - 또한 Data 통신 프로토콜에도 의존적이게 됨
- Apache Kafka는 이러한 복잡도를 없애기 위해서 Linkedin 에서 내부적으로 개발하여 Open Source로 공개하였다.
- 즉, Source Application ↔ Target Application 의 결합도를 낮추기 위하여 등장 하였다.
  - Source Application 은 주로 데이터를 전송하는 쪽으로 '클릭로그', '결제로그' 등의 로그를 전송한다.
  - Target Application은 로그를 적재하거나 로그를 처리할 수 있다.
  - Kafka는 이들 중간에서 Messaging Queue 역할을 한다.
![image](https://user-images.githubusercontent.com/18481400/114971731-f44fb400-9eb7-11eb-974e-53190aa5e3ea.png)

- Kafka는 큐 역할을 하는 topic을 가지고 있으면서 topic에 log를 적재하고 가져갈 수 있게끔 한다.
  - log를 적재하는 기능을 위해서는 Producer 라이브러리를 제공한다.
  - log를 가져가는 기능을 위해서는 Comsumer 라이브러리를 제공한다.

![image](https://user-images.githubusercontent.com/18481400/114971743-fd408580-9eb7-11eb-895f-2f4efcf9d781.png)


## Kafka의 특징

### Broker

- 실행된 카프카 애플리케이션 서버 중 1대
- 주로 3대 이상의 브로커로 클러스터를 구성
- n개의 브로커 중에서 1개의 브로커는 반드시 컨트롤러 기능을 수행

![image](https://user-images.githubusercontent.com/18481400/114971763-07628400-9eb8-11eb-8a93-f18920b48783.png)


### Zookeeper

- 메타데이터 (브로커 id, 컨트롤러 id 등)을 저장
- 브로커들의 메시징 큐 정보를 관리해주는 역할

### Record

- 객체 메시지를 의미하며 프로듀서에서 컨슈머로 전달하기 위해 Kafka 내부에 byte 형태로 저장
- 직렬화 클래스를 제공하고 있다.

### Topic & Partition

- 메시지의 분류 단위이다.
- topic에는 1개 이상의 partition이 존재한다.
- 각 partition 마다 고유한 오프셋을 가지며 이는 순차적으로 부여된다.
- 다만 파티션이 여러개 인 경우 메시지 처리 순서는 파티션 별로 관리되기 때문에
  - 들어간 순서가 보장 되지 않는다는 것을 의미함

![image](https://user-images.githubusercontent.com/18481400/114971778-0df0fb80-9eb8-11eb-9ef1-1c679cb8414f.png)


### Producer

- 프로듀서는 레코드를 생성하여 브로커로 전송한다.
- 전송된 레코드는 파티션에 신규 오프셋과 함께 기록된다.

### Consumer

- 컨슈머는 브로커에 레코드를 요청한다. (polling 방식)
  - 이때 컨슈머 고유의 offet을 가지고 있어 읽어들인 offset 정보를 가지고 있다.

### Kafka log & segment

- 메시지가 저장되는 파일 시스템 단위
- 세그먼트 파일은 시간, 크기 기준으로 닫힌다.
- 단힌 후에는 일정 시간 이후에 삭제, 압축됨

### Consumer Group

- Consumer 들의 묶음을 의미한다.
- Consumer Group은 반드시 topic의 파티션과 n : m 매칭을 해야 한다.
  - 그렇기에 파티션과 컨슈머의 개수가 동일한 경우 훨씬 좋은 성능을 낼 수가 있다.
  - 다만 컨슈머의 개수가 더 많다면 나머지는 놀게 된다.

![image](https://user-images.githubusercontent.com/18481400/114971797-147f7300-9eb8-11eb-8e99-5832e1fee4a2.png)


- 일부 Consumer가 장애가 난 경우 **리밸런스**가 되어 나머지 Consumer로 할당이 재조정된다.
- 목적에 따라서  컨슈머 기룹을 분리하는 경우도 있다.

![image](https://user-images.githubusercontent.com/18481400/114971807-1ba68100-9eb8-11eb-9208-3e8853748aa8.png)


### Replication

- 고가용성을 위해서 다수의 broker 간의 데이터 복제를 이용하여 데이터 유실을 방지하는 방법이다.
- 리더 파티션: Kafka 클라이언트와 데이터를 주고 받는 역할
- 팔로워 파티션: 리더 파티션으로 부터 레코드를 지속 복제, 리더 파티션의 동작이 불가능한 경우 나머지 팔로워 중 1개가 리더로 선출된다.

![image](https://user-images.githubusercontent.com/18481400/114971818-2103cb80-9eb8-11eb-9ec6-95e73d3c8898.png)


### ISR

- 리더, 팔로워 파티션의 완벽하게 복제되어 싱크가 맞는 것을 In-Sync Replica 라고 한다.
- 하지만 ISR이 아닌 경우에서 리더 파티션이 장애가 난 경우 unclean.leader.election.enable 여부를 통해서 기다리거나, 처리하거나 선택할 수 있다.
