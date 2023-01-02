## PURE 자바 공공와이파이 수집 프로젝트

### - 프로젝트 소개
* 서울시 공공와이파이 오픈 API를 활용한 현재 위치에서 서울시 와이파이 중 가장 가까운 와이파이 정보를 볼 수 있는 서비스

### - 개발환경
* JAVA8
* JDK 1.8
* DataBase : sqlite 3.40.0
* IDE : Eclipse
* WAS : tomcat v8.5

### - 라이브러리
* okhttp3 4.9.3
* gson 2.9.0
* lombok 1.18.24
* sqlite-jdbc 3.36.0.3

### - 주요기능
* 서울 시 오픈 API로 공공 와이파이 전체 정보 수집
* 나의 위치에서 가장 가까운 거리순으로 와이파이 정보 20개 리스트 조회
* 근처 wifi 정보 보기 조회 시 현재 x, y 좌표 및 시간을 history 테이블에 저장

### - 개선사항
* API 수집 성능 개선

