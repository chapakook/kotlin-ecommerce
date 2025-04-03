# ERD
- [Total](#total)
- [User](#user)
- [UserPoint](#userpoint)
- [Order](#order)
- [OrderLine](#orderline)
- [Product](#product)
- [ProductOption](#productoption)
- [ProductStock](#productstock)
- [Discount](#discount)

<br>

### Total
```mermaid
erDiagram
    User ||--|| UserPoint : has
    User ||--o{ Order : has
    Order ||--o{ OrderLine : has
    Order ||--o| Payment : has
    Order ||--o| Discount : has
    Product ||--o| ProductStock : stock
    Product ||--o{ OrderLine : includes
    
    User{
        BIGINT userId PK
        VARCHAR(50) userName
        VARCHAR(100) email
        DATETIME createdAt
    }
    UserPoint{
        BIGINT pointId PK
        BIGINT userId FK
        INT balance
        DATETIME updatedAt
    }
    Payment{
        BIGINT paymentId PK
        BIGINT orderId FK
        INT amount
        ENUM paymentStatus "SUCCESS, FAILURE"
        DATETIME createdAt
    }
    Discount{
        BIGINT discountId PK
        BIGINT productId FK
        ENUM discountType "PERCENTAGE, FIXED"
        INT value 
        DATETIME expirationDate
    }
    Order{
        BIGINT orderId PK
        BIGINT userId FK
        INT totalPrice
        ENUM orderStatus "PENDING, COMPLETE"
    }
    OrderLine{
        BIGINT lineId PK
        BIGINT orderId PK
        BIGINT productId PK
        INT quantity
        INT totalPrice
    }
    Product{
        BIGINT productId PK
        VARCHER(100) name
        INT price
        DATETIME createdAt
    }
    ProductStock{
        BIGINT stockId PK
        BIGINT productId FK
        INT quantity
        DATETIME updatedAt
    }
```

### User
```mermaid
erDiagram
    User ||--|| UserPoint : has
    User ||--o{ Order : has
    
    User{
        BIGINT userId PK
        VARCHAR(50) userName
        VARCHAR(100) email
        DATETIME createdAt
    }
    UserPoint{
        BIGINT pointId PK
        BIGINT userId FK
        INT balance
        DATETIME updatedAt
    }
    
    Order{
        BIGINT orderId PK
        BIGINT userId FK
        INT totalPrice
        ENUM orderStatus "PENDING, COMPLETE"
    }
    
```
**Properties**
- `userId`: PK - 사용자 아이디
- `userName`: 사용자명
- `email`: 이메일
- `createdAt`: 생성일자

<br>

### UserPoint
```mermaid
erDiagram
    User ||--|| UserPoint : has
    
    User{
        BIGINT userId PK
        VARCHAR(50) userName
        VARCHAR(100) email
        DATETIME createdAt
    }
    UserPoint{
        BIGINT pointId PK
        BIGINT userId FK
        INT balance
        DATETIME updatedAt
    }
```
**Properties**
- `pointId`: PK - 포인트 아이디
- `userId`: FK - User.userId
- `balance`: 잔액
- `updatedAt`: 업데이트 일자

<br>

### Order
```mermaid
erDiagram
    User ||--o{ Order : has
    Order ||--o{ OrderLine : has
    Order ||--o| Payment : has
    Order ||--o| Discount : has
    
    User{
        BIGINT userId PK
        VARCHAR(50) userName
        VARCHAR(100) email
        DATETIME createdAt
    }

    Order{
        BIGINT orderId PK
        BIGINT userId FK
        INT totalPrice
        ENUM orderStatus "PENDING, COMPLETE"
    }
    Payment{
        BIGINT paymentId PK
        BIGINT orderId FK
        INT amount
        ENUM paymentStatus "SUCCESS, FAILURE"
        DATETIME createdAt
    }
    Discount{
        BIGINT discountId PK
        BIGINT productId FK
        ENUM discountType "PERCENTAGE, FIXED"
        INT value 
        DATETIME expirationDate
    }
    
    OrderLine{
        BIGINT lineId PK
        BIGINT orderId PK
        BIGINT productId PK
        INT quantity
        INT totalPrice
    }
```
**Properties**
- `orderId`: PK - 오더아이디
- `userId`: FK - User.userId
- `totalPrice`: 전체가격
- `orderStatus`: 주문상태

<br>

### OrderLine
```mermaid
erDiagram
    Order ||--o{ OrderLine : has
    Product ||--o{ OrderLine : includes
    
    Order{
        BIGINT orderId PK
        BIGINT userId FK
        INT totalPrice
        ENUM orderStatus "PENDING, COMPLETE"
    }
    OrderLine{
        BIGINT lineId PK
        BIGINT orderId PK
        BIGINT productId PK
        INT quantity
        INT totalPrice
    }
    Product{
        BIGINT productId PK
        VARCHER(100) name
        INT price
        DATETIME createdAt
    }
```
**Properties**
- `lineId`: PK - 라인아이디
- `orderId`: FK - Order.orderId
- `productId`: FK - Product.productId
- `quantity`: 양
- `totalPrice`: 초가격

<br>

### Product
```mermaid
erDiagram
    Product ||--o| ProductStock : stock
    Product ||--o{ OrderLine : includes
    
    OrderLine{
        BIGINT lineId PK
        BIGINT orderId PK
        BIGINT productId PK
        INT quantity
        INT totalPrice
    }
    Product{
        BIGINT productId PK
        VARCHER(100) name
        INT price
        DATETIME createdAt
    }
    ProductStock{
        BIGINT stockId PK
        BIGINT productId FK
        INT quantity
        DATETIME updatedAt
    }
```
**Properties**
- `productId`: PK - 상품아이디 
- `name`: 제품명
- `price`: 단가
- `createdat`: 생성일자

<br>

### ProductStock
```mermaid
erDiagram
    Product ||--o| ProductStock : stock
    
    Product{
        BIGINT productId PK
        VARCHER(100) name
        INT price
        DATETIME createdAt
    }
    ProductStock{
        BIGINT stockId PK
        BIGINT productId FK
        INT quantity
        DATETIME updatedAt
    }
```
**Properties**
- `stockId`: PK - 재고아이디
- `productId`: FK - Product.productId
- `quantity`: 양
- `updatedAt`: 업데이트일자

<br>

### Discount
```mermaid
erDiagram
    Order ||--o| Discount : has

    Discount{
        BIGINT discountId PK
        BIGINT productId FK
        ENUM discountType "PERCENTAGE, FIXED"
        INT value 
        DATETIME expirationDate
    }
    Order{
        BIGINT orderId PK
        BIGINT userId FK
        INT totalPrice
        ENUM orderStatus "PENDING, COMPLETE"
    }
```
**Properties**
- `discountId`: PK - 할인아이디
- `productId`: FK - Product.productId
- `discountType`
- `value`: 할인 값
- `expirationDate`: 유효기간