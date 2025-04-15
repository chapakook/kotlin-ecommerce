package kr.hhplus.be.server.support

enum class ErrorCode(val code: String, val message: String) {
    USER_NOT_FOUND("E001", "사용자를 찾을 수 없습니다."),
    AMOUNT_MUST_BE_POSITIVE("E002", "충전 포인트는 음수일 수 없습니다."),
    OUT_OF_POINT("E003", "포인트가 부족합니다."),

    CREATE_BASE_POINT("TE001", "포인트 초깃값은 양수여야 합니다.")
}