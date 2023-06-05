# 시큐어코딩 테스트 웹
시큐어코딩 테스트 웹 애플리케이션

# 초기 설정 방법
## JDK 설정
> File > Settings > Build, Execution, Deployment > Build tools > Gradle 에서 하단 Gradle JVM을 17로 변경

## DB 설정
### properties 설정
> properties파일은 src > main > resources > application.properties 위치해있습니다.
> <br/> intellij에서 쉬프트 두번 입력 후 나타나는 검색 창에 `application.properties`를 입력하여 이동할 수도 있습니다.

`spring.datasource.username`값에 설치한 MariaDB의 접속 계정명 입력(기본값:root)
<br/>
> 예시 : spring.datasource.username=root

`spring.datasource.password`값에 설치한 MariaDB의 계정 비밀번호 입력
<br/>
> 예시 : spring.datasource.password=password

### 스키마 생성
1. MariaDB 콘솔 접근 (Windows기준 찾기에서 MySQL Client검색 후 앱 실행)
2. root 계정 접속
3. 콘솔에 `CREATE DATABASE IF NOT EXISTS baedalbujok` 입력 후 엔터
4. 이후 스프링 애플리케이션을 실행하면 자동으로 테이블과 초기값이 생성됩니다.

