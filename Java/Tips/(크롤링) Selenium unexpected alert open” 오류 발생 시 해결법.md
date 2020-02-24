# (크롤링) Selenium "unexpected alert open” 오류 발생 시 해결법

### 들어가며

-   Selenium 사용시 예상치 못한 Alert 창이 생겨 "unexpected alert open"이라는 오류가 발생한적이 있었습니다

[##_Image|kage@b4A9sE/btqBZg1FzLv/kHVp5y2yE77gYzdUewfC8k/img.png|alignCenter|data-origin-width="0" data-origin-height="0" width="580" height="176"|||_##]

-   Selenium을 사용하여 alert 창 발생시 alert 창을 close하는 방법을 알아보겠습니다.

---

### 해결방법

-   alert 창을 닫는 방법은 여러가지가 있습니다.

#### 1\. alert 개별 처리 방법

간단한 일부 alert 창을 닫기 위한 코드로 쓰입니다.

```
driver.switchTo().alert().accept();
```

---

#### 2\. 크롬 기본 세팅 변경

Chrome Capabilite를 ACCEPT, INGORE 또는 DISMISS 알림으로 표시 할 수 있습니다.

```
DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
WebDriver driver = new ChromeDriver(capabilities);
```

---

#### 3\. Robot 클래스 사용

```
Robot r = new Robot();

r.keyPress(KeyEvent.VK_ENTER);
r.keyRelease(KeyEvent.VK_ENTER);
```

---

#### 4\. 세션 새로 만들기

```
driver.quit();
driver = new ChromeDriver();
```

---

### 참고

https://stackoverflow.com/questions/19173195/how-to-handle-the-unexpected-alert-open