package kr.hhplus.be.server.infrastructure.coupon

import kr.hhplus.be.server.domain.coupon.Coupon
import kr.hhplus.be.server.domain.coupon.CouponRepository
import org.springframework.stereotype.Repository

@Repository
class CouponRepositoryImpl(
    private val couponJPARepository: CouponJPARepository,
) : CouponRepository {
    override fun findCouponByUserIdAndId(userId: Long, couponId: Long): Coupon? =
        couponJPARepository.findCouponByUserIdAndId(userId, couponId)

    override fun save(coupon: Coupon): Coupon = couponJPARepository.save(coupon)
}