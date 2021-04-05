# git username, password 계정 정보 저장 방법

- 회사 Bitbucket, 개인 Github 계정 설정이 꼬여 Github 에 접근할 때마다 password를 묻는 prompt 가 뜨곤 했다.
- 아래의 명령으로 설정이 가능
  - **다만, 해당 방법은 계정 정보를 PC Disk에 PlainText로 저장하기 때문에 문제가 생길 수 있다.**
  - 저장되는 위치는 ~/.git-credentials

```java
git config --global credential.helper store
```

- 초기화를 위해서는 `~/.git-credentials` 파일을 수정해주면 된다.

