# ElasticSearch for Developer
## tip
- index의 두가지 의미 (색인을 한다, doc의 논리적인 구조 simmilar table)
- elasticsearch sparse doc(여러 Type 저장시 문제) 
- query 밑에 모든 것을 쓴다.
- 검색을 잘하려 하는 것 보다 처음에 설계에 시간을 투자 하는 것이 훨씬 효과적이다.
- text에 여러 국어가 있을 경우 Multifield, 혹은 Language Detecter를 사용할 수 있다.  
- 내 손과 머리가 가는 만큼 시스템은 점점 가벼워진다.
- Primary Shard 수를 1로 설정
- 아직까진 mappings type에 doc 대신에 _default_를 쓰는 것이 좋다.
- minimum_master_nodes를 node 수 / 2 + 1로 설정해야 split brain시 문제를 해결할 수 있다.
- 트랜잭션이 없기 때문에 snapshot을 활용하면 좋다. (index 없을 시 data folder를 복사 하는 것도 방법)
- doc단위로 delete 하거나 update를 하는 것도 delete marking을 하는 것일 뿐이기 때문에 segment merge가 일어난 후에야 지워진다.
- 속도를 위해선 filter를 넣어주는 것이 좋다.
## question
- elastic stack 각각의 용도 ?
- mean of mapping?
- mean of score?
- what is replica?
- 148 page
- synonyms analyzer ?
- property -> index : false ?
- 이전에 class를 변경해서 저장한 경우는 어떻게 가능했던 건지 ?
- ngram?
- 샤드란 ? 
- field data vs doc value (338 page)
- Node가 늘어나면 효율 Limit인 Shard 도 늘어나는 가 ?
## Link
- https://www.elastic.co/kr/blog - 한국어 블로그
- https://www.elastic.co/kr/videos - 비디오

## 1. ElasticSearch Introduction
### intro..
- Elastic Stack(Kibana, ElasticSearch, Beats, Logstash) like Linux ram stack, APM
- X-pack, ElasticCloud은 유료
### Components of ElasticSearch
- document : 모든 하나의 단일 정보, JSON 형태로 저장 모든 ducument 는 unique한 ID가 있음.
- index : 데이터의 논리적인 그룹,index -> type -> document 구조가 없어짐 indexing(색인, 데이터 저장)
### Indexing

- ID가 중복 되서 저장하는 경우 덮어 씌워진다.
- 첫번째 doc이 들어가면서 index가 생성, 첫번째 도규먼트로 mapping이 생성이 된다.
- ID를 직접 만든다면 direct aceess를 할 수가 있다.(*참고)
- search는 단어를 찾아서 가는 것 바로 접근은 바로 페이지를 여는 것

### Search
- URI search : 좀 더 한정적임 (43page)
- request body search : http 데이터 본문에 쿼리를 넣는것
- 결과 
1. took : 걸린 시간
2. hits : Default는 10 size로 되어 있는데 늘려서 사용하는 건 바람직 하진 않다.
- 로그는 날짜로 나누는 것이 바람직하다. (*참고)
- search Indices index 지정 방법(42page)

### CRUD OP
- elasticsearch는 트랜젝션이 없다.(*)
- Scroll 이라는 API가 존재 (*)
1. create : 이미 있을 때는 failed 가 발생
2. multiget : (49 page)

### Bulk (53 page 참고 읽어볼 필요 있음)
- 여러개의 명령을 사용할 때 쓰임.   
- 대용량을 저장할 때는 bulk가 훨씬 빠르다.
- Error Message (54 page) 
- Elastic Stack을 사용할 때는 버전을 맞춰서 사용하는 것이 좋다.

## 2. Search API (113 page)
### Searching for Terms 
- match query
- socre 로 정렬되어 출력되는 데 score가 높도록 해야 한다. 관련도를 나타낸 것 같다.
- 기본 적으로 operator는 or인데 query에 operator 옵션을 추가해야한다. (73 page)
- minimum_shoud_match (74 page)

### Saeching for Phrases
- matach_phrase query
- slop 이라는 옵션이 존재 (defult는 0)

### Rnage Saerch(81 page)
- range query

### Combining Search(86 page)
- bool query
1. must : and
2. should : 있으면 socre가 높아진다. (must와 같이 쓸 경우 must가 먼저 적용 됨, 많이 쓸 수록 circle이 넓어진다.)
- should만 잇는 경우 must 조건으로 적용이 된다. 
### Filtering Search
- Filter는 true or false를 따진다.
- 캐싱을 하기 때문에 성능이 좋아진다.

### Controlling Precision
- should not Query (100 page)

### Scoring(104 page)
- TF, IDF, Feild length
- DFS 옵션을 주면 정확히 계산이 가능
- IDF는 샤드 별로 계산을 한다. (*)

### Boosting Relevance
- 배수를 주는 것.
- boost parameter (110page 참고)
- should 안에서 쓴다. 

## 3. TextAnalysis
### W.I.A
- Analyzer : 단어 분해 토큰으로 분리
- 색인, 쿼리 시 같은 Anlyzer를 사용

### Inverted Index
- 토큰을 가지고 text를 찾아갈 수 있는 것    
- 기본적으로 소문자로 변환 하며 관사나 stopword는 제외한다.
- 단어의 원형으로 변형을 한다.
- 유의어 사전으로 설정을 하여 쓸수도 있다.

### Analyzers
- Analyzers 종류 (135page ~ )
- 아래의 순서로 적용된다. 3가지

### Character Filters
- Character Filter종류 (141page ~ )
- c++ -> cpp 를 하는 이유 ? 불용어는 삭제 글자 하나도 삭제됨.

### Tokenizers 
- Token 분리기 (145page)

### Token Filters

### Custom Anlyzer 
- 사용법 (153page) - index 단위로 만들어지게 된다. Template 만들 수도 있다.

## 4. Mapping
### W.I.M
- 스키마 라고 볼수 있다.
- Type 별로 Mapping을 할 수 있다.
- Mpaaing 지정 방법 (170page ~ ) - But 5.0 까지만
- 필드의 데이터 타입은 한번 정하면 바꿀 수 없다.
- Elastic Data Type 종류 (173b page)

### W.N.M.Y ?
- 이전에는 dynamic 하게 mapping을 했다.
- full text 를 mapping 하게 되면 keyword 필드도 자동으로 생긴다. (그렇기 때문에 keyword 세팅은 필요없다면 꺼두는 게 좋다.)

### Explicit Mapping
- Mapping 바로 하면 히면 힘들기 때문에 sample doc을 넣어서 mapping 참고하여 새로 만들면 좀 더 편하다.
- Mapping PUT 하는 방법 (184page)

### Change Mapping? 
- 기존 데이터 타입을 바꿀 수 없기 때문에 변경은 불가 (reindex 해야함!)
- 그러나 새로운 타입은 추가 가능. (dynamic property로 여부 조절 가능)
- 없던 Mpaaing 추가 방법(189page)

### Dive Deeper Mappings
- Array Fiels 선언 가능(191 page) ex. 가수 이름을 keywords 일때 여러가수가 들어오면 ?
- Date format (192 page)
- _all 은 모든 field를 array 형태로 출력하는 것인데 안쓰는 것이 용량 측면에서 좋다. (사용예는 이전에 모든 필드에서 검색시 사용됨 !)

### Multi Field
- 서브 필드로 지정할 수가 있다.

### Index Template
- 같은 패턴의 index 가 생성되게 되면 index template가 적용되도록 하는 것.
- templdate 만드는 법 (208page)
- 충돌이 있는 경우 order가 큰 것이 적용이 된다.

## 5. More Search Feature

### Index Aliases
- like nickname
- index name 대신에 접근이 가능
- index name이 바뀌어도 aliase 이름으로 가능 ..

### Date Math 
- 날짜 연산이 가능 (227page)

### Wildcard Query (235page)

### Term Query 
- match와 같은 결과를 낸다.
- 하지만 검색 할 때 쿼리를 anlyzing하지 않는다.
- 예전엔 keyword일 때도 analyze를 했는데 지금은 자동으로 해줘서 term query를 잘 쓰지 않는다.

### Multi_match (240 page)

### Fuzziness 
- 오타 수정 기능 
- 성능은 그다지 좋지 않다. 

### Highlighting 
- simonic으로 저장시 동의어가 결과로 나와서 구별이 힘들 때가 있는데 highlighting을 쓰면 검색 단어가 결과로 나온다.

## 6. Distributed Model

### Starting Node
- All node have clusterState(It can changed only be master node)
- All node is data node (config 변경 가능)
- All node can be Cordinating Node that catch  Request by client
- ClusterState가 업데이트 되면 모든 노드에 배포

### Second Node 
- Node can be join master node, They will be share clusterstate

### Shard 
- 미리 node 에서 여러 shard에 index를 나워서 저장한다.
- shard는 node에서 분산되어 저장된다. (Primary Shard)
### Replication
- Master Node에 요청을 통해서 clusterState를 변경 
- Shard 복제본은 만드는 것.
- Node가 하나일 때는 replica를 못만들가 때문에 yellow가 발생
- Shard 수는 Node의 배수로 하는 것이 좋을 수도 있다.
- 각 Node의 primary Shard를 다른의 replica shard로 만드는 것이다. (이해 필요 *)
## 7. Search Result
### Segment
- 기록 저장단위 inverted index 를 저장
- 각 필드 별로 segment가 만들어진다. Lucene에서 만듬.
- default 1초 단위로 만듬.
- refresh 후에 검색(segement에서 검색)이 가능하다.
- replica를 늘리면 검색 분산성이 좋고, shart가 어느정도 많을 수록 검색 속도가 증가한다.
### Sorting by String
- doc value 타입이 하나 더 생기는 것 리하 생각하면 됨. (해제하면 용량은 줄지만 aggregation 이 불가능)
- nomalizers -> keyword type 을 바꾸는 것 doc value를 바꾸는 것
### Pagination
- deep pigination 시 과부하가 걸릴 수 있음
- searqch_after 옵션을 이용 (352 page 확인)
### Scroll
- 모든 데이터를 가져올 때는 logstash를 사용해 input->E.S output->File 하는 방법
## 8. Suggersters
### Suggesters
- Similar-looking, ex). 연관 검색어 
- suggester 사용 시 doc을 반환하는 fuzzy search 와 달리 match 되는 token을 반환한다.
- Suggesting phrases (390 page)
## 9. Aggregation
### W.A.A
- 계산된 결과를 얻고자 할 떼 쓰인다.
- agreegation value만을 얻고 싶다면 size를 0으로
## 10. More Aggregations
### Aggregation Scope
1. global : 쿼리를 무시한다. 전체 index doc을 가져온다.
2. post_filter : query에만 영향을 끼침 aggs 에는 X
### Misssing Aggregation 
- field가 null 인 것들을 계산하고자 할 때
### Histogram
- interval을 나눠서 구한다.
- barchart 등을 만들때 용이함.
- min_doc_count parameter를 이용해서 최소 값 이상일 경우에만 출력하도록
### percentiles 
- 상위 몇 % 인지 이해하면 쉬움
### Significant Terms
- commonly common 한 값을 제외
