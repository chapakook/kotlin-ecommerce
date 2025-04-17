package kr.hhplus.be.server.support

enum class ErrorCode(val code: String, val message: String) {
    USER_NOT_FOUND("E001", "사용자를 찾을 수 없습니다."),
    AMOUNT_MUST_BE_POSITIVE("E002", "충전 포인트는 음수일 수 없습니다."),
    OUT_OF_POINT("E003", "포인트가 부족합니다."),
    QUANTITY_MUST_BE_POSITIVE("E004", "재고차감 수량이 잘못됐습니다."),
    OUT_OF_STOCK("E005", "상품의 재고가 부족합니다."),
    STOCK_NOT_FOUND("E006", "상품의 재고를 찾을 수 없습니다."),
    POPULARITY_NOT_FOUNT("E007", "인기상품이 집계되지 않았습니다. 잠시만 기달려주세요."),
    MAX_ISSUED_COUPON("E008", "선착순 쿠폰이 모두 발급되었습니다."),
    USED_COUPON("E009", "이미 사용된 쿠폰입니다."),
    EXPIRED_COUPON("E010", "만료된 쿠폰입니다."),
    COUPON_EVENT_NOT_FOUND("E011", "쿠폰이벤트를 찾을 수 없습니다."),
    COUPON_NOT_FOUND("E012", "쿠폰을 찾을 수 없습니다."),

    CREATE_BASE_POINT("TE001", "포인트 초깃값은 양수여야 합니다.")
}