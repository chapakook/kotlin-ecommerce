package kr.hhplus.be.server.support

enum class ErrorCode(val code: String, val message: String) {
    // E0xx general error
    USER_NOT_FOUND("E000", "사용자를 찾을 수 없습니다."),

    // E1xx point error
    AMOUNT_MUST_BE_POSITIVE("E100", "충전 포인트는 음수일 수 없습니다."),
    OUT_OF_POINT("E101", "포인트가 부족합니다."),
    POINT_CHARGE_FAILED("E102", "포인트 충전에 실패하였습니다."),
    POINT_USE_FAILED("E103", "포인트 사용에 실패하였습니다."),

    // E2xx product error
    QUANTITY_MUST_BE_POSITIVE("E200", "재고차감 수량이 잘못됐습니다."),
    OUT_OF_STOCK("E201", "상품의 재고가 부족합니다."),
    STOCK_NOT_FOUND("E202", "상품의 재고를 찾을 수 없습니다."),
    POPULARITY_NOT_FOUNT("E203", "인기상품이 집계되지 않았습니다. 잠시만 기달려주세요."),

    // E3xx coupon error
    COUPON_EVENT_NOT_FOUND("E303", "쿠폰이벤트를 찾을 수 없습니다."),
    EXPIRED_COUPON_EVENT("E305", "만료된 쿠폰이벤트 입니다"),

    // E4xx coupon event error
    MAX_ISSUED_COUPON("E400", "선착순 쿠폰이 모두 발급되었습니다."),
    USED_COUPON("E401", "이미 사용된 쿠폰입니다."),
    EXPIRED_COUPON("E402", "만료된 쿠폰입니다."),
    COUPON_NOT_FOUND("E404", "쿠폰을 찾을 수 없습니다."),

    // E5xx lock error
    UNABLE_ACQUIRE_LOCK("E500", "락을 획득할 수 없습니다"),

    // TE*** test error
    CREATE_BASE_POINT("TE001", "포인트 초깃값은 양수여야 합니다.")
}