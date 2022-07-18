# Annotation
어노테이션
## Meta-Annotation
- Annotation 들의 Annotation 이라고 부른다. 

### Target 
- `ElementType` 을 통해서 어디에 어노테이션을 붙일 수 있는 지를 설정할 수 있다.  (EXPERIMENT_EXPOSURE_EXPERIMENT_ID)
#### PACKAGE
- ex) Spring 의 NonNullApi 를 참고 해당 package 하위에 싹다 적용하겠다는 의미를 가짐 
#### ANNOTATION_TYPE
- 다른 Meta Annotation 들에 적용 된다. (그래서 Target 본인한테도 붙어 있음 )


### Retention
- `RetentionPolicy`를 통해서 Annotation 이 어느시점까지 유지 될지를 반드시 지정해줘야 한다. 
.java (compile) - Compile Time Weaving -> .class (class load) - Load Time Weaving -> JVM Runtime 

#### SOURCE
- 컴파일 Time 에 Annotation 이 적용되고 사라진다. 
#### CLASS
- 캄파일 후 Class Loading 시점에 적용되고 사라진다.
#### RUNTIME
- Runtime 까지도 정보가 남아 메모리상에서도 접근이 가능하다. 

#### Lombok 은 ?
- Getter 로 간단한 테스트를 해보자. 
- Lomnbok 은 CTW 방식으로 동작을 한다. 

#### Default 동작은 ?
- 만약 지정하지 않는다면 default 로는 CLASS 타입으로 지정된다. (참고로 코틀린에서는 default가 RUMTIME 이다)
- 테스트 해보자. 