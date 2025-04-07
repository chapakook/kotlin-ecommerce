# 시퀀스 다이어그램

### 시나리오
- 잔액조회
- 잔액충전
- 상품조회
- 선착순 쿠폰발급
- 주문/결제
- 상위 상품 조회

<br>

### 잔액조회
```mermaid
sequenceDiagram
    사용자 ->> 잔액조회 : 잔액조회 요청
    activate 사용자
    activate 잔액조회
    잔액조회 ->> 유저 : 유저조회
    activate 유저
    activate 유저
    deactivate 잔액조회
    alt 유저 없음
        유저 -->> 사용자 : "유저없음" 오류 리턴
        deactivate 유저
    else 유저 있음
        유저 ->> 포인트 : 잔액조회
        deactivate 유저
        activate 포인트
    end
    포인트 -->> 사용자 : "잔액" 리턴
    deactivate 포인트
    deactivate 사용자
```
<br>

### 잔액충전
```mermaid
sequenceDiagram
    사용자 ->> 잔액충전 : 잔액충전 요청
    activate 사용자
    activate 잔액충전
    잔액충전 ->> 유저 : 유저조회
    deactivate 잔액충전
    activate 유저
    activate 유저
    alt 유저 없음
        유저 -->> 사용자 : "유저없음" 오류 리턴
        deactivate 유저
    else 유저 있음
        유저 ->> 포인트 : 잔액 충전
        deactivate 유저
        activate 포인트
        activate 포인트
    end
    alt 충전요천 포인트 이상
        포인트 -->> 사용자 : "충전포인트 이상" 오류 리턴
        deactivate 포인트
    else 충전요청 포인트 정상
        포인트 ->> 포인트 : 포인트 충전
        포인트 -->> 사용자 : "잔액" 리턴
    end
    deactivate 포인트
    deactivate 사용자
```
<br>

### 상품조회
```mermaid
sequenceDiagram
    사용자 ->> 상품조회 : 상품조회 요청
    activate 사용자
    activate 상품조회
    상품조회 ->> 상품 : 상품조회
    deactivate 상품조회
    activate 상품
    상품 -->> 사용자 : "조회된 상품" 리턴
    deactivate 상품
    deactivate 사용자
```

<br>

### 선착순 쿠폰발급
```mermaid
sequenceDiagram
    사용자 ->> 쿠폰발급 : 쿠폰발급 요청
    activate 사용자
    activate 쿠폰발급
    쿠폰발급 ->> 대기열 : 대기열 등록
    deactivate 쿠폰발급
    activate 대기열
    대기열 -->> 사용자 : "현재 대기열" 리턴
    deactivate 대기열
    alt 대기열 > 0
        loop 1초 마다
            사용자 ->> 대기열 : 대기열 조회
            activate 대기열
            activate 사용자
            대기열 -->> 사용자 : "현재 대기열" 리턴
            deactivate 대기열
            deactivate 사용자
        end
    else 대기열 <= 0
        대기열 ->> 쿠폰 : 쿠폰재고 조회
        activate 대기열
        activate 쿠폰
        activate 쿠폰
        alt 쿠폰 <= 0
            쿠폰 -->> 대기열 : 쿠폰재고 없음
            activate 대기열
            deactivate 쿠폰
            대기열 -->> 사용자 : "쿠폰발급실패" 리턴 
            deactivate 대기열
        else 쿠폰 > 0
            쿠폰 -->> 대기열 : 쿠폰재고 충분
            deactivate 쿠폰
            대기열 ->> 쿠폰 : 쿠폰발급 요청
            activate 쿠폰
            deactivate 대기열
            쿠폰 ->> 쿠폰 : 쿠폰 차감
            쿠폰 -->> 사용자 : "발급쿠폰" 리턴
            deactivate 쿠폰
            deactivate 사용자
        end
    end 
```
<br>

### 주문/결제
주문/결제
```mermaid
sequenceDiagram
    사용자 ->> 주문 : 주문 요청
    activate 사용자
    activate 주문
    주문 ->> 상품 : 상품 조회
    activate 상품
    상품 ->> 재고 : 재고 조회
    deactivate 상품
    activate 재고
    재고 -->> 주문 : "재고" 리턴
    deactivate 재고
    주문 ->> 재고 : 재고차감 요청
    activate 재고
    재고 ->> 재고 : 재고 차감
    재고 -->> 주문 : "차감재고" 리턴
    deactivate 재고
    주문 ->> 쿠폰 : 쿠폰 조회
    activate 쿠폰
    쿠폰 -->> 주문 : 쿠폰 리턴
    deactivate 쿠폰
    주문 ->> 쿠폰 : 쿠폰 차감 요청
    activate 쿠폰
    쿠폰 ->> 쿠폰 : 쿠폰 차감
    쿠폰 -->> 주문 : 차감된쿠폰 리턴
    deactivate 쿠폰
    주문 ->> 결제 : 결재 요청
    activate 결제
    결제 ->> 포인트 : 포인트 조회
    activate 포인트
    포인트 -->> 결제 : "포인트충분" 리턴
    deactivate 포인트
    결제 ->> 결제 : 결제
    결제 ->> 포인트 : 포인트 차감 요청
    activate 포인트
    포인트 ->> 포인트 : 포인트 차감
    포인트 -->> 결제 : "차감포인트" 리턴
    deactivate 포인트
    결제 -->> 주문 : 결제 완료
    deactivate 결제
    주문 -->> 사용자 : 주문 완료
    deactivate 주문
    deactivate 사용자
```

`포인트 부족` → `포인트 충전` → 결제
```mermaid
sequenceDiagram
    주문 ->> 결제 : 결제 요청
    activate 주문
    activate 결제
    결제 ->> 포인트 : 포인트 조회
    activate 포인트
    포인트 -->> 결제 : 포인트 부족
    deactivate 포인트
    결제 ->> 사용자 : 충전 요청
    activate 사용자
    사용자 ->> 포인트 : 충전요청
    deactivate 사용자
    activate 포인트
    포인트 -->> 사용자 : "충전포인트" 리턴
    deactivate 포인트
    activate 사용자
    사용자 ->> 결제 : 결제 요청
    deactivate 사용자
    결제 ->> 포인트 : 포인트차감 요청
    activate 포인트
    포인트 -->> 결제 : 차감포인트 리턴
    deactivate 포인트 
    결제 -->> 주문 : 결제완료
    deactivate 결제
    deactivate 주문
```

<br>

### 상위 상품 조회
```mermaid
sequenceDiagram
    사용자 ->> 상위상품조회 : 상위상품조회 요청
    activate 사용자
    activate 상위상품조회
    상위상품조회 ->> 집계데이터 : 조회
    deactivate 상위상품조회
    activate 집계데이터
    alt 집계데이터 >= 5
        집계데이터 -->> 사용자 : "상위상품" 5개 리턴
        activate 집계데이터
        deactivate 집계데이터
    else 5 > 집계데이터 > 0
        집계데이터 -->> 사용자 : "상위상품" 리턴
        activate 집계데이터
        deactivate 집계데이터
    else 집계데이터 >= 0
        집계데이터 -->> 사용자 : "없음" 리턴
        activate 집계데이터
        deactivate 집계데이터
    end
    deactivate 집계데이터
    deactivate 사용자
```