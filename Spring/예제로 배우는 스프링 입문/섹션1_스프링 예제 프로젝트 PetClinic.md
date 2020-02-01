# 섹션1. 스프링 예제 프로젝트 PetClinic

## PetClinic이란?

- 스프링 프로젝트 공식 프로젝트 중 하나
  - SpringBoot를 사용함
- 스프링 프레임워크를 사용해서 만든 간단한 웹 애플리케이션
  - 동물병원을 예제로 만든 프로젝트

## 프로젝트 세팅

- Maven Wrapper(mvnw)
  - Apache Maven을 프로젝트에서 요구하는 버전으로 유지하기 위해 사용하는 도구이다. 복잡해지는 빌드 환경을 Maven Wrapper를 이용해 해결할 수 있다.
  - Maven이 아닌 환경에서도 빌드가 가능
- packaging
  - 위 옵션이 없을 경우 기본으로 jar(Java Archive) 패키징

## 프로젝트 개요

- 로그를 자세하게 보고 싶다면?
  - (SpringBoot의 경우) [application.properties](http://application.properties) logging level을 DEBUG로 추가

## 프로젝트 과제

- Data Object에 새로운 필드를 추가하는 경우
  - user lacks privilege or object....
    - 원인: DB에 해당 필드가 없기 때문임
    - 해결 방법: [application.properties](http://application.properties) 파일에 설정된 DB 스키마 파일을 열어서 TABLE CREATE 과정에 필드를 추가
  - row column count mismatch
    - 원인: 데이터를 넣을 때 Column에 맞게 데이터가 insert되지 않기 때문
    - 해결 방법: DB insert 문에 추가된 필드를 추가 해줘야 함