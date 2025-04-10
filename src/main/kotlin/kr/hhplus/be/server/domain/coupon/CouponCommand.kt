package kr.hhplus.be.server.domain.coupon

class CouponCommand {
    data class Use (
        val userId: Long,
        val code: String?,
    ) {
        init {
            require(userId > 0) { "userId must be positive." }
        }
    }
    data class Issue(
        val userId: Long,
        val couponId: Long,
    ){
        init {
            require(userId > 0) { "userId must be positive." }
            require(couponId > 0) { "couponId must be positive" }
        }
    }
}