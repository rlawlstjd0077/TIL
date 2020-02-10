# Windows Sound 음소거 (Mute) 방법

## 들어가며..

------

- Windows System Sound를 Mute/UnMute 하는 기능이 필요했다.
- 어떻게 보면 간단한 기능이라고 생각이 되지만 생각보다 쉽게 찾을 수가 없었다. 더군다나 Java라서 JNA를 써야 하나 .. 생각 중인 터에 아주 적절한 방법을 찾았다.

## 해결 방법

------

- ProcessBuilder를 이용하여 CMD 명령으로 System Sound를 활성화/비활성화 하는 방법이다.

  - 예제 코드

    public void test() throws LineUnavailableException, IOException { ProcessBuilder processBuilder = new ProcessBuilder();

    ```java
      //꼭 관리자 권한으로 실행해야 함
      processBuilder.command("cmd.exe", "/c", "net steop audiosrv");
    
      try {
        Process process = processBuilder.start();
    
        // blocked :(
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));
    
        String line;
        while ((line = reader.readLine()) != null) {
          System.out.println(new String(line));
        }
    
        int exitCode = process.waitFor();
        System.out.println("\\nExited with error code : " + exitCode);
    
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    ```

  - 꼭 관리자 권한으로 실행해야 한다. (IDE로 실행한다면 IDE를 관리자 권한으로 실행)

  - 만약 다시 Sound를 활성화(UnMute) 하고 싶다면 아래 코드 사용

    ```java
      processBuilder.command("cmd.exe", "/c", "net start audiosrv");
    ```