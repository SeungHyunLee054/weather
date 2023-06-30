# Zerobase Practice Project 02 weather
## 사용한 기능
### Tool
1. IntelliJ
### database
1. MySQL
### plugins
1. Spring boot
2. Gradle
3. Junit5
4. JPA
5. Mockito
6. Lombok
7. swagger
## 제공하는 기능(API)
### 일기(Diary) 관련 기능
1. 일기 생성
2. 일기 읽기
3. 일기 범위 읽기
4. 일기 수정
5. 일기 삭제

### 로그(log) 관련 기능
1. logback을 이용한 로그 기록 저장
2. 모든 로그를 저장하는 파일과 error만을 저장하는 파일을 나눔
3. 일정 용량이상이 되면 자동으로 압축

### API document 기능
1. swagger를 이용한 일기 API 이용방법

### 날씨 저장 기능
1. scheduled를 이용하여 매일 오전 1시에 날씨 정보를 db에 저장
