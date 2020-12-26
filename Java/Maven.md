## 빌드 관리 도구

### 빌드 관리 도구란?

- 빌드 툴로 아래와 같은 반복적인 프로젝트 필드 과정을 효율적으로 수행할 수 있도록 도와준다. (굵은 글씨의 부분을 담당함)
  - compile
  - package
  - deploy
- 빌드 과정을 경량화 시킬 수 있다.

## Maven

- Java 프로젝트의 빌드를 자동화 해주는 빌드 관리 도구

### Maven의 원리

- 라이브러리 의존성 관리를 위해서 중앙 저장소 (mavenCentral, jcenter 등) 에 라이브러리를 배포 관리한다.
- 프로젝트를 새로 만들고 pom.xml 파일에 필요한 라이브러리를 명시해주면 maven 이 알아서 중앙저장소로 부터 다운로드를 받고(내부적으로 .m2 라는 폴더에 다운로드) 빌드를 해준다.

### pom.xml (Maven 이 참조하는 설정 파일)

- 프로젝트 최상위 디렉토리에 "pom.xml" 이라는 파일이 생성됨 (Product Object Model)
- 프로젝트 내 빌드 옵션을 설정하는 부분
- 최상의 pom 설정 파일이 생성되며 이는 모든 pom.xml 이 상속하는 부모 설정파일이다.
  - `mvn help:effective-pom` 으로 내용 확인 할 수 있음
  - 음 확인해보니 프로젝트 마다 생성시에 생기는 것 같음

![1](https://github.com/rlawlstjd0077/TIL/blob/master/Java/img/Maven-1.png)

### pom.xml 구조

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="<http://maven.apache.org/POM/4.0.0>" xmlns:xsi="<http://www.w3.org/2001/XMLSchema-instance>"
    xsi:schemaLocation="<http://maven.apache.org/POM/4.0.0> <http://maven.apache.org/xsd/maven-4.0.0.xsd>">
    <modelVersion>4.0.0</modelVersion>
	  # 4.0.0이라고 써있는데 이것은 maven의 pom.xml의 모델 버전이다. 형식이 4.0.0 버전이라고 이해하면 된다.
    <groupId>com.example</groupId>
		# 프로젝트를 생성한 조직 또는 그룹명으로 보통, URL의 역순으로 지정한다.
    <artifactId>demo</artifactId>
		# 프로젝트를 식별하는 유일한 아이디를 의미한다. com.example이라는 groupid를 사용하고 있는 조직에 여러 프로젝트가 존재할 수 있다.
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>
 
    <name>demo</name>
    <description>Demo project for Spring Boot</description>
 
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.5.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
 
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>
		# pom.xml에서 중복해서 사용되는 설정(상수) 값들을 지정해놓는 부분. 다른 위치에서 ${...}로 표기해서 사용할 수 있다. (java.version에 1.8을 적용하고 다른 위치에서 ${java.version}이라고 쓰면 "1.8"이라고 쓴 것과 같다.
 
		<profiles>
		  <profile>
		   <id>dev</id>
		   <properties>
		    <java.version>1.8</java.version>
		   </properties>
		  </profile>
		  <profile>
		   <id>prod</id>
		   <properties>
		    <java.version>1.9</java.version>
		   </properties>
		  </profile>
		</profiles>
		# dev, prod 이런식으로 개발할 때, 릴리즈할 때를 나눠야할 필요가 있는 설정 값은 profiles로 설정할 수 있다.
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
 
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
		# 의존성 라이브러리 정보들 최소한 groupId, artifactId, version 정보가 필요하지만
		# spring-boot-starter-*같은 경우에는 부모 pom.xml에서 이미 버전정보가 있어서 version은 따로 지정할 필요가 없다.
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
		# maven의 핵심인 빌드와 관련된 정보를 설정할 수 있는 곳 (생명 주기 부분 참고)
</project>
```

### 라이프 사이클

![2](https://github.com/rlawlstjd0077/TIL/blob/master/Java/img/Maven-2.png)

- 크게는 default, clean, site 라이프 사이클로 나누고 세부적으로는 하나의 단위를 Phase라고 한다.
- Maven의 모든 기능은 플러그인을 기반으로 동작한다.
- 플로그인에서 실행할 수 있는 각각의 작업을 골(goal) 이라고 하며 한개의 Phase는 한 개의 골과 연결된다.
- 라이프 사이클의 순서는 아래와 같다.
  1. **mvn process-resources** : resources:resources의 실행으로 resource 디렉토리에 있는 내용을 target/classes로 복사한다.
  2. **mvn compile** : compiler:compile의 실행으로 src/java 밑의 모든 자바 소스를 컴파일해서 target/classes로 복사
  3. **mvn process-testResources, mvn test-compile** : 이것은 위의 두 개가 src/java였다면 test/java의 내용을 target/test-classes로 복사. (참고로 test만 mvn test 명령을 내리면 라이프사이클상 원본 소스로 컴파일된다.)
  4. **mvn test** : surefire:test의 실행으로 target/test-classes에 있는 테스트케이스의 단위테스트를 진행한다. 결과를 target/surefire-reports에 생성한다.
  5. **mvn package** : target디렉토리 하위에 jar, war, ear등 패키지파일을 생성하고 이름은 <build>의 <finalName>의 값을 사용한다 지정되지 않았을 때는 아까 설명한 "artifactId-version.extention" 이름으로 생성
  6. **mvn install** : 로컬 저장소로 배포
  7. **mvn deploy** : 원격 저장소로 배포
  8. **mvn clean** : 빌드 과정에서 생긴 target 디렉토리 내용 삭제
  9. **mvn site** : target/site에 문서 사이트 생성
  10. **mvn site-deploy** : 문서 사이트를 서버로 배포
- <build> 에서 설정할 수 있는 값은 아래와 같다.
  - <fileName>: 빌드 결과물(ex .jar) 이름 설정
  - <resources>: 리소스(각종 설정 파일)의 위치를 지정할 수 있다.
    - <resource> : 없으면 기본으로 "src/main/resources"
  - <testResources> : 테스트 리소스의 위치를 지정할 수 있다.
    - <testResource> : 없으면 기본으로 "src/test/resources"
  - <Repositories>: 빌드할 때 접근할 저장소의 위치를 지정할 수 있다. 기본적으로 메이븐 중앙 저장소인 http://repo1.maven.org/maven2로 지정되어 있다.
  - <outputDirectory>: 컴파일한 결과물 위치 값 지정, 기본 "target/classes"
  - <testOutputDirectory>: 테스트 소스를 컴파일한 결과물 위치 값 지정, 기본 "target/test-classes"
  - <plugin>: 어떠한 액션 하나를 담당하는 것으로 가장 중요하지만 들어가는 옵션은 제 각각이다. 다행인 것은 플러그인 형식에 대한 것은 안내가 나와있으니 그것을 참고해서 작성하면 된다.

## 참고

- https://www.slideshare.net/ssuser5445b7/ss-56566336
- https://jeong-pro.tistory.com/168