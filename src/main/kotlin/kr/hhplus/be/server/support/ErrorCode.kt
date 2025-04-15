package kr.hhplus.be.server.support

enum class ErrorCode(val code: String, val message: String) {
    USER_NOT_FOUND("E001", "사용자를 찾을 수 없습니다."),
    AMOUNT_MUST_BE_POSITIVE("E002", "충전 포인트는 음수일 수 없습니다."),
    AMOUNT_OVER_BALANCE("E003", "사용금액은 포인트 잔액보다 많을 수 없습니다.")
}