package kr.hhplus.be.server.domain.coupon

class CouponCommand {
    class Use{}
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