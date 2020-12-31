# Spring Boot Test

- Spring Boot는 애플리케이션을 테스트 하기 위한 많은 기능을 제공한다.
- Spring Boot 에서 테스트 모듈은 spring-boot-test, spring-boot-test-autoconfigure가 존재한다.
- spring-boot-starter-test는 JUnit는 물론이고, AssertJ, Hamcrest를 포함한 여러 유용한 라이브러리를 포함하고 있다.

## @SpringBootTest

- 테스트에 사용할 ApplicationContext를 쉽게 생성하고 조작할 수 있다.
- 전체 빈 중 특정 빈만 Mock으로 대체한다든지, 테스트에 사용할 프로퍼티 파일을 선택하거나 특정 속성만 추가한다든지, 특정 Configuration을 선택하여 설정할 수 있다.
- 다만 이 어노테이션을 사용하기 위해서는 아래 @RunWith(SpringRunner.class)를 함께 사용해야 한다.

### Bean

- `@SpringBootTest` 어노테이션을 사용하면 테스트에 사용할 빈을 손쉽게 등록 할 수 있다.
- classes라는 속성을 제공한다. 해당 속성을 통해서 빈을 생성할 클래스들을 지정할 수 있습니다.
- classes 속성에 @Configuration 어노테이션을 사용하는 클래스가 있다면 내부에서 @Bean 어노테이션을 통해서 생성되는 빈도 모두 등록이 됩니다.
- 만일 classes 속성을 통해서 클래스를 지정하지 않으면 애플리케이션 상에 정의된 모든 빈을 생성한다.

```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ArticleServiceImpl.class, CommonConfig.class})
public class SomeClassTest {
    // Service로 등록하는 빈
    @Autowired
    private ArticleServiceImpl articleServiceImpl;
    // CommonConfig에서 생성하는 빈
    @Autowired
    private RestTemplate restTemplate;
}
```

### MockBean

- spring-boot-test 패키지는 Mockito를 포함하고 있어서 `@MockBean` 어노테이션을 사용해서  이름 그대로 Mock 객체를 빈으로써 등록할 수 있다.
- `@MockBean` 어노테이션을 사용해서 이름 그대로 Mock 객체를 빈으로써 등록할 수 있다.
- 그렇기 때문에 만일 @MockBean으로 선언된 빈을 주입받는다면(@Autowired 같은 어노테이션 등을 통해서) Spring의 ApplicationContext는 Mock 객체를 주입해준다.

```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArticleServiceImpl.class)
public class ArticleServiceImplTest {
    @MockBean
    private RestTemplate restTemplate;
    @MockBean
    private ArticleDao articleDao;
    @Autowired
    private ArticleServiceImpl articleServiceImpl;

    @Test
    public void testFindFromDB() {
        List<Article> expected = Arrays.asList(
                new Article(0, "author1", "title1", "content1", Timestamp.valueOf(LocalDateTime.now())),
                new Article(1, "author2", "title2", "content2", Timestamp.valueOf(LocalDateTime.now())));

        given(articleDao.findAll()).willReturn(expected);

        List<Article> articles = articleServiceImpl.findFromDB();
        assertThat(articles).isEqualTo(expected);
    }
}
```

### Properties

- 이 속성을 이용해서 별도의 테스트를 위한 application.properties(또는 application.yml)을 지정할 수 있다.

```java
@RunWith(SpringBoot.class)
@SpringBootTest(properties = "classpath:application-test.yml")
public class SomeTest {
    ...
}
```

### Web Environment test

- 손쉽게 웹 테스트 환경을 선택할 수 있다.
  - MOCK
    - WebApplicationContext를 로드하며 내장된 서블릿 컨테이너가 아닌 Mock 서블릿을 제공합니다. @AutoConfigureMockMvc 어노테이션을 함께 사용하면 별다른 설정 없이 간편하게 MockMvc를 사용한 테스트를 진행할 수 있습니다.
  - RANDOM_PORT
    - EmbeddedWebApplicationContext를 로드하며 실제 서블릿 환경을 구성합니다. 생성된 서블릿 컨테이너는 임의의 포트는 listen합니다.
  - DEFINED_PORT
    - RAMDOM_PORT와 동일하게 실제 서블릿 환경을 구성하지만, 포트는 애플리케이션 프로퍼티에서 지정한 포트를 listen합니다(application.properties 또는 application.yml에서 지정한 포트).
  - NONE
    - 일반적인 ApplicationContext를 로드하며 아무런 서블릿 환경을 구성하지 않습니다.

### TestRestTemplate

- 편리한 웹 통합 테스트를 해볼 수 있다.
- `RestTemplate` 의 테스트를 위한 버전으로 @SpringBootTest에서 Web Environment 설정을 하였다면 TestRestTemplate은 그에 맞춰서 자동으로 설정되어 빈이 생성됩니다.

```java
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test() {
        ResponseEntity<Article> response = restTemplate.getForEntity("/api/articles/1", Article.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        ...
    }
}
```

- 기존에 컨트롤러를 테스트하는 데 많이 사용되던 MockMvc와 어떤 차이가 있는지 궁금할 것입니다. 가장 큰 차이점이라면 Servlet Container를 사용하느냐 안 하느냐의 차이입니다. MockMvc는 Servlet Container를 생성하지 않습니다.
- 반면, @SpringBootTest와 TestRestTemplate은 Servlet Container를 사용합니다. 그래서 마치 실제 서버가 동작하는 것처럼(물론 몇몇 빈은 Mock 객체로 대체될 수는 있습니다) 테스트를 수행할 수 있습니다.
- 또한, 테스트하는 관점도 서로 다릅니다. MockMvc는 서버 입장에서 구현한 API를 통해 비즈니스 로직이 문제없이 수행되는지 테스트를 할 수 있다면, TestRestTemplate은 클라이언트 입장에서 RestTemplate을 사용하듯이 테스트를 수행할 수 있습니다.

## @RunWith(SpringRunner.class)

- 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
- SpringRunner 라는 스프링 실행자를 사용하는 것을 의미함
- 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함
- 정리해보자면 @RunWith에 Runner클래스를 설정하면 JUnit에 내장된 Runner대신 그 클래스를 실행한다. 여기서는 스프링 테스트를 위해서 SpringRunner라는 Runner 클래스를 설정해 준 것이다.
- 한 클래스내에 여러개의 테스트가 있더라도 어플리케이션 컨텍스트를 초기 한번만 로딩하여 사용하기 때문에, 여러개의 테스트가 있더라도 처음 테스트만 조금 느리고 그 뒤의 테스트들은 빠르다.

## @WebMvcTest

- server-side 에서 API를 테스트 하는 어노테이션
  - client-side는 `TestRestTemplate` 를 사용
- 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션이다.
  - 선언할 경우 @Controller, @ControllerAdvice, @Filter, WebMvcConfigurer, HandlerMethodArgumentResolver 등을 스캔하여 사용함
  - 단, @Service, @Component, @Repository 등은 사용할 수 없음

```java
@RunWith(SpringRunner.class)
@WebMvcTest(ArticleApiController.class)
public class ArticleApiControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ArticleService articleService;

    @Test
    public void testGetArticles() throws Exception {
        List<Article> articles = asList(
                new Article(1, "kwseo", "good", "good content", now()),
                new Article(2, "kwseo", "haha", "good haha", now()));

        given(articleService.findFromDB(eq("kwseo"))).willReturn(articles);

        mvc.perform(get("/api/articles?author=kwseo"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("@[*].author", containsInAnyOrder("kwseo", "kwseo")));
    }

    private Timestamp now() {
        return Timestamp.valueOf(LocalDateTime.now());
    }
}
```

## 참고 자료

- https://meetup.toast.com/posts/124