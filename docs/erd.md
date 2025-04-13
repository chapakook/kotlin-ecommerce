# ERD
- [Total](#total)
- [User](#user)
- [UserPoint](#userpoint)
- [Order](#order)
- [OrderLine](#orderline)
- [Product](#product)
- [ProductStock](#productstock)
- [Coupon](#coupon)
- [UserCoupon](#usercoupon)
- [OrderCoupon](#ordercoupon)
- [Payment](#payment)
- [CouponIssuedQueue](#couponissuedqueue)
- [TopSellingProduct](#topsellingproduct)

<br>

## Total
```mermaid
erDiagram
    User ||--|| UserPoint : ""
    User ||--o{ UserCoupon : ""
    UserPoint ||--o{ Payment : ""
    Coupon ||--o{ UserCoupon : ""
    Coupon ||--o{ OrderCoupon : ""
    Order ||--o{ OrderCoupon : ""
    User ||--o{ Order : "" 
    Order ||--|| Payment : ""
    Order ||--o{ OrderLine : ""
    Product ||--o{ OrderLine : ""
    Product ||--|| ProductStock : ""
    User ||--o{ CouponIssuedQueue: ""
    Coupon ||--o{ CouponIssuedQueue: ""
    
    User{
        BIGINT userId PK
        VARCHAR(50) username
        DATETIME createdAt "TIMESTAMP"
    }
    UserPoint{
        BIGINT pointId PK
        BIGINT userId FK
        DECIMAL balance
        DATETIME updatedAt
    }
    Payment{
        BIGINT paymentId PK
        BIGINT orderId FK
        DECIMAL amount
        ENUM paymentStatus "START, SUCCESS, FAILURE, COMPLETE"
        DATETIME createdAt "TIMESTAMP"
        DATETIME updatedAt  
    }
    Coupon{
        BIGINT couponId PK
        VARCHAR(36) code "UNIQUE"
        ENUM discountType "PERCENTAGE, FIXED"
        DECIMAL discountValue
        INT issuedLimit
        INT issuedCount
        BOOLEAN isActive
        DATETIME createdAt "TIMESTAMP"
    }
    UserCoupon{
        BIGINT userCouponId PK
        BIGINT couponId FK "Coupon.couponId"
        BIGINT userId FK "User.userId"
        DATETIME expiresAt
        BOOLEAN isUsed
    }
    OrderCoupon{
        BIGINT orderCouponId PK
        BIGINT couponId FK "Coupon.couponId"
        BIGINT orderId FK "Order.orderId"
        DECIMAL appliedDiscount
        DATETIME appliedAt
    }
    Order{
        BIGINT orderId PK
        BIGINT userId FK "User.userId"
        DECIMAL totalAmount
        ENUM orderStatus "PENDING, FAILURE, COMPLETE, CALCEL"
        DATETIME createdAt "TIMESTAMP"
        DATETIME updatedAt  
    }
    OrderLine{
        BIGINT orderLineId PK
        BIGINT orderId FK "Order.orderId"
        BIGINT productId FK "Product.productId"
        INT quantity
        DECIMAL unitPrics
    }
    Product{
        BIGINT productId PK
        VARCHER(100) name
        DECIMAL price
        DATETIME createdAt "TIMESTAMP"
    }
    ProductStock{
        BIGINT productStockId PK
        BIGINT productId FK
        INT quantity
        DATETIME updatedAt
    }
    CouponIssuedQueue{
        BIGINT queueId PK
        BIGINT userId FK "User.userId"
        BIGINT couponId FK "Coupon.couponId"
        ENUM status "PENDING, PROCESSING, ISSUED, FAILURE"
        DATETIME createdAt "TIMESTAMP"
        DATETIME updatedAt
        DATETIME processedAt
    }
    TopSellingProduct{
        BIGINT topSellId PK
        BIGINT productId FK "Product.productId"
        DATETIME periodStart
        DATETIME periodEnd
        INT totalQuantity 
        INT rank
    }
```

<br>

## Orders
```mermaid
erDiagram
    User ||--|| UserPoint : ""
    User ||--o{ Order : "" 
    Coupon ||--o{ UserCoupon : ""
    User ||--o{ UserCoupon : ""
    UserPoint ||--o{ Payment : ""
    Order ||--o{ OrderCoupon : ""
    Coupon ||--o{ OrderCoupon : ""
    Order ||--|| Payment : ""

    User{
        BIGINT userId PK
        VARCHAR(50) username
        DATETIME createdAt "TIMESTAMP"
    }
    UserPoint{
        BIGINT pointId PK
        BIGINT userId FK
        DECIMAL balance
        DATETIME updatedAt
    }
    Coupon{
        BIGINT couponId PK
        VARCHAR(36) code "UNIQUE"
        ENUM discountType "PERCENTAGE, FIXED"
        DECIMAL discountValue
        INT issuedLimit
        INT issuedCount
        BOOLEAN isActive
        DATETIME createdAt "TIMESTAMP"
    }
    UserCoupon{
        BIGINT userCouponId PK
        BIGINT couponId FK "Coupon.couponId"
        BIGINT userId FK "User.userId"
        DATETIME expiresAt
        BOOLEAN isUsed
    }
    Order{
        BIGINT orderId PK
        BIGINT userId FK "User.userId"
        DECIMAL totalAmount
        ENUM orderStatus "PENDING, FAILURE, COMPLETE, CALCEL"
        DATETIME createdAt "TIMESTAMP"
        DATETIME updatedAt  
    }
    Payment{
        BIGINT paymentId PK
        BIGINT orderId FK
        DECIMAL amount
        ENUM paymentStatus "START, SUCCESS, FAILURE, COMPLETE"
        DATETIME createdAt "TIMESTAMP"
        DATETIME updatedAt  
    }
    OrderCoupon{
        BIGINT orderCouponId PK
        BIGINT couponId FK "Coupon.couponId"
        BIGINT orderId FK "Order.orderId"
        DECIMAL appliedDiscount
        DATETIME appliedAt
    }
```
### `User`
**Properties**
- `userId`: 유저아이디 `PK`
- `username`: 유저명
- `createdAt`: 생성일시

### `UserPoint`
**Properties**
- `pointId`: 포인트 아이디 `PK`
- `userId`: [User.userId](#user) `FK`
- `balance`: 잔액
- `updatedAt`: 수정일시

### `Coupon`
**Properties**
- `couponId`: 쿠폰아이디 `PK`
- `code`: 코드번호 `UNIQUE`
- `discountType`: 할인형태
    > - `PERCENTAGE`: 퍼센트금액
    > - `FIXED`: 고정금액
- `discountValue`: 할인금액
- `issuedLimit`: 발급제한개수
- `issuedCount`: 발급개수
- `isActive`: 활성화상태
- `createdAt`: 생성일시

### `UserCoupon`
**Properties**
- `userCouponId`: 유저쿠폰아이디 `PK`
- `couponId`: [Coupon.couponId](#coupon) `FK`
- `userId`: [User.userId](#user) `FK`
- `expiresAt`: 만료일시
- `isUsed`: 사용여부

### `OrderCoupon`
**Properties**
- `orderCouponId`: 오더쿠폰아이디 `PK`
- `couponId`: [Coupon.couponId](#coupon) `FK`
- `orderId`: [Order.orderId](#order) `FK` 
- `appliedDiscount`: 할인적용금액
- `appliedAt`: 할인적용일시

### `Order`
**Properties**
- `orderId`: 오더아이디 `PK`
- `userId`: [User.userId](#user) `FK`
- `totalAmount`: 전체가격
- `orderStatus`: 주문상태
    > - `PENDING`: 결제대기 
    > - `FAILURE`: 주문실패
    > - `COMPLETE`: 주문완료
    > - `CALCEL`: 주문취소
- `createdAt`: 생성일시
- `updatedAt`: 수정일시

### `OrderLine`
**Properties**
- `orderLineId`: 오더라인아이디 `PK`
- `orderId`: [Order.orderId](#order) `FK`
- `productId`: [Product.productId](#product) `FK`
- `quantity`: 개수
- `unitPrics`: 단가

<br>

## Products
```mermaid
erDiagram
    Product ||--o{ OrderLine : ""
    Product ||--|| ProductStock : ""
    Order ||--o{ OrderLine : ""

    Order{
        BIGINT orderId PK
        BIGINT userId FK "User.userId"
        DECIMAL totalAmount
        ENUM orderStatus "PENDING, FAILURE, COMPLETE, CALCEL"
        DATETIME createdAt "TIMESTAMP"
        DATETIME updatedAt  
    }
    OrderLine{
        BIGINT orderLineId PK
        BIGINT orderId FK "Order.orderId"
        BIGINT productId FK "Product.productId"
        INT quantity
        DECIMAL unitPrics
    }
    Product{
        BIGINT productId PK
        VARCHER(100) name
        DECIMAL price
        DATETIME createdAt "TIMESTAMP"
    }
    ProductStock{
        BIGINT productStockId PK
        BIGINT productId FK
        INT quantity
        DATETIME updatedAt
    }
```

### `Product`
**Properties**
- `productId`: 상품아이디 `PK`
- `name`: 제품명
- `price`: 단가
- `createdAt`: 생성일시

### `ProductStock`
**Properties**
- `productStockId`: 재고아이디 `PK`
- `productId`: [Product.productId](#product) `FK`
- `quantity`: 재고량
- `updatedAt`: 수정일시

<br>

## Queue
```mermaid
erDiagram
    User ||--o{ CouponIssuedQueue: ""
    Coupon ||--o{ CouponIssuedQueue: ""

    User{
        BIGINT userId PK
        VARCHAR(50) username
        DATETIME createdAt "TIMESTAMP"
    }
    Coupon{
        BIGINT couponId PK
        VARCHAR(36) code "UNIQUE"
        ENUM discountType "PERCENTAGE, FIXED"
        DECIMAL discountValue
        INT issuedLimit
        INT issuedCount
        BOOLEAN isActive
        DATETIME createdAt "TIMESTAMP"
    }
    CouponIssuedQueue{
        BIGINT queueId PK
        BIGINT userId FK "User.userId"
        BIGINT couponId FK "Coupon.couponId"
        ENUM status "PENDING, PROCESSING, ISSUED, FAILURE"
        DATETIME createdAt "TIMESTAMP"
        DATETIME updatedAt
        DATETIME processedAt
    }
```
### `CouponIssuedQueue`
**Properties**
- `queueId`: 큐아이디 `PK`
- `userId`: [User.userId](#user) `FK` 
- `couponId` [Coupon.couponId](#coupon) `FK`
- `status`: 
    > - `PENDING`: 발급대기중
    > - `PROCESSING`: 발급중
    > - `ISSUED`: 발급완료
    > - `FAILURE`: 발급실패
- `createdAt`: 생성일시 `TIMESTAMP`
- `updatedAt`: 수정일시
- `processedAt`: 발급시작일시

## Aggregation
```mermaid
erDiagram
    TopSellingProduct{
        BIGINT topSellId PK
        BIGINT productId FK "Product.productId"
        DATETIME periodStart
        DATETIME periodEnd
        INT totalQuantity 
        INT rank
    }
```
### `TopSellingProduct`
**Properties**
- `topSellId`: 상위판매아이디 `PK`
- `productId`: [Product.productId](#product) `FK` 
- `periodStart`: 집계시작시간
- `periodEnd`: 집계완료시간
- `totalQuantity`: 총판매량
- `rank`: 순위