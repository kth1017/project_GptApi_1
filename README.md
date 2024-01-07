# project_GptApi_1
GptApi를 이용한 웹서비스 토이 프로젝트

- 문제 발생 : gpt를 사용하여 프로그래밍 관련 질문을 하는데 한글로 질문하면 너무 오래 걸림
- 해결 : 파파고를 통해 한글 질문을 영어로 변환하여 gpt에 영어로 질문하는 기능을 구현

url 링크: http://ec2-52-79-255-69.ap-northeast-2.compute.amazonaws.com:3000/
post 링크: https://fadet-coding.tistory.com/82

# 개요
react+spring으로 GptApi, PapagoApi를 이용하여 만든 프로그래밍 ai질문 웹서비스
![image](https://user-images.githubusercontent.com/96664524/221394229-c8e595b7-4350-44b9-b22c-0ab44d492125.png)
![image](https://user-images.githubusercontent.com/96664524/221394278-c8740e1e-0d9d-4520-a836-53025e297674.png)




# 사용 기술
NginX(웹서버, 포워드 프록시) > React(앞단) > axios > Spring Boot(뒷단) > JPA > mariaDB (DB)

- react : Component 캡슐화 , Context API, axios 라이브러리를 사용하여 구성 / MUI로 디자인
- sping boot: spring boot를 이용해 mvc 패턴의 서버 작성, jpa/jpql을 사용한 entity-db 매핑, junit5로 클래스별 유닛 테스트

- AWS EC2&RDS : EC2를 사용하여 배포 + RDS DB 연동 / VPC 구축
- docker : 모든 빌드 파일을 이미지화하여 EC2내 docker-compose를 사용해 배포
- NginX : 포워드 프록시로 사용하여 정적 React build 파일 배포 및 캐싱


# ver 1.0.0
@branch 'main' commit 'ver 1.0.0'

@to be
- 번역, ai 질문의 기본 기능 구현
- 도커라이징하여 EC2로 배포

# ver 1.0.1
@branch 'main' commit 'ver 1.0.1'

@to be
- Controller로 들어오는 요청의 Exception handling을 위해 ControllerAdvice 추가
- Gpt api 서버가 불안정할 때가 존재하여 service의 timeout 설정
- 배포 rds의 파라미터 그룹 수정

  
