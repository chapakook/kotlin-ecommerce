package kr.hhplus.be.server.support

enum class OutsideTopic(val topic: String) {
    TEST_V1("outside.test.v1.test"),
    COMPLETED_V1("outside.order.v1.completed"),
    ISSUE_REQUEST_COUPON_V1("outside.coupon.v1.issue.request"),
}