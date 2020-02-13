# (크롤링) Selenium iframe 파싱 방법 

## 들어가며..

- Selenium으로 파싱을 하는데 iframe 안에 있는 정보를 파싱을 하고자 했다. (Java 사용)
- 이리저리 알아보다 간단한 방법을 알아내었다.

## 방법

- iframe 파싱 예제 코드

  - 'driver.switch_to.frame' 메서드를 사용하여 iframe element로 switch를 해주는 것으로 보임

  ```java
  //파싱 하고자하는 iframe elements를 찾아 switch함
  driver.switch_to.frame(driver.find_element_by_tag_name("iframe"))
  ```

- 다시 기본 컨텐츠로 돌아오는 예제 코드

  ```java
  driver.switch_to.default_content()
  ```

## 참고

- https://stackoverflow.com/questions/18924146/selenium-and-iframe-in-html