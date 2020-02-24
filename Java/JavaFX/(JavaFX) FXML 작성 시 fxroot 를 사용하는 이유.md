# (JavaFX) FXML 작성 시 "<fx:root>" 를 사용하는 이유

## `<fx:root>`란

-   [JavaFX 2.2](https://docs.oracle.com/javafx/2/fxml_get_started/whats_new2.htm)에 새로 추가된 .fxml 파일 작성 사용하는 root Tag이다.

```
(JavaFX) FXML 작성 시 "<fx:root>" 를 사용하는 이유  <fx:root type="javafx.scene.control.TextArea" fx:id="editor" prefWidth="500" prefHeight="400" xmlns:fx="http://javafx.com/fxml"/>    contents..</fx:root>
```

-   `<fx:root>`를 사용하게 되면 FXML로 재사용 가능한 컴포넌트를 작성하는 데 도움을 준다고 한다.

---

## 재사용성?

-   예를 들어, `HBox`안에 `TextField`와 `Button`이 들어 있는 컨포넌트를 작성한다고 해보자.(`fx:root` 사용 하지 않고)
    
    -   그럼 아래와 같이 작성할 수 있을 것이다.
    
    ```
    VBox vbox = new VBox();
    vbox.getChildren().add(new MyComponent());
    ```
    
    -   그리고 `MyComponent` Java Class는 아래와 같이 작성할 것이다.
    
    ```
    public class MyComponent extends HBox {
      private TextField textField ;
      private Button button ;
    
      public MyComponent() {
          textField = new TextField();
          button = new Button();
          this.getChildren().addAll(textField, button);
      }
    }
    ```
    
    -   대응되는 FXML 코드는 아래와 같이 작성할 수 있을 것이다.
    
    ```
    <HBox>
      <TextField fx:id="textField"/>
      <Button fx:id="button" />
    </HBox>
    ```
    
-   이때 발생할 수 있는 문제는 FXML 자체도 일종의 `Node`이고, `MyComponent` 클래스 자체도 `Node`라는 것이다.
    
    -   이해하기 어려울 것이다, 아래 코드를 보자
    
    ```
    public class MyComponent extends HBox {
      @FXML
      private TextField textField ;
      @FXML
      private Button button ;
      public MyComponent() {
          try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("MyComponent.fxml"));
              loader.setController(this);
    
              //FXML 자체가 HBox 노드 인 것이다!
              HBox hbox = loader.load();
              this.getChildren().add(hbox);
          } catch (IOException exc) {
              // handle exception
          }
      }
    }
    ```
    
    -   위 코드의 결과는 `TextField`, `Button`을 감싸는 `HBox`를 감싸는 `HBox`로 구성된 MyComponent가 생성됨
        -   그 이유는 FXML Root의 `Node`와 이를 구성하는 클래스의 Node가 필요하기 때문임
-   `<fx:root>`를 한번 써보자.
    
    -   `<fx:root>`는 `Node`를 컴포넌트 (Java 클래스)로 작성하고 FXML의 루트를 컴포넌트의 노드로 사용하도록 지시하는 매커니즘
        
        ```
        <fx:root type="javafx.scene.layout.HBox">
        <TextField fx:id="textField" />
        <Button fx:id="button" />
        </fx:root>
        ```
        
    -   컴포넌트 코드를 작성 해보면
        
        ```
        public class MyComponent extends HBox {
        @FXML 
        private TextField textField ;
        @FXML
        private Button button ;
        public MyComponent() {
          try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("MyComponent.fxml"));
              loader.setController(this);
              loader.setRoot(this);
              loader.load();
          } catch (IOException exc) {
              // handle exception
          }
        }
        }
        ```
    
-   결과적으로 MyComponent는 FXML과 Java 클래스와 대응되어 사용할 수 있게 된다.
    
-   이에 더하여, MyComponent는 다른 FXML에서도 사용할 수 있게 되어 **UI의 계층 구조를 형성**할 수 있다.
    

---

## 결론

-   앞으로 FXML의 루트는 `fx:root`로 작성하자
    

---

## 참고

https://stackoverflow.com/questions/23600926/how-to-understand-and-use-fxroot-in-javafx
