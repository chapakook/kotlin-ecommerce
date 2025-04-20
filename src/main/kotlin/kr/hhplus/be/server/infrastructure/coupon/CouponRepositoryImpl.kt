package kr.hhplus.be.server.infrastructure.coupon

import kr.hhplus.be.server.domain.coupon.Coupon
import kr.hhplus.be.server.domain.coupon.CouponRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class CouponRepositoryImpl(
    private val couponJPARepository: CouponJPARepository,
) : CouponRepository {
    override fun findCouponByUserIdAndId(userId: Long, id: Long): Coupon? =
        couponJPARepository.findCouponByUserIdAndId(userId, id)?.to()

    @Transactional
    override fun save(coupon: Coupon): Coupon = couponJPARepository.save(CouponEntity.of(coupon)).to()
}