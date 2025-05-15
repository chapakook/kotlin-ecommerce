package kr.hhplus.be.server.infrastructure.coupon

import kr.hhplus.be.server.domain.coupon.Coupon
import kr.hhplus.be.server.domain.coupon.CouponRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class CouponRepositoryImpl(
    private val jpaCouponRepository: JpaCouponRepository,
) : CouponRepository {
    override fun findByCouponIdAndUserId(id: Long, userId: Long): Coupon? =
        jpaCouponRepository.findByCouponIdAndUserId(id, userId)

    @Transactional
    override fun save(coupon: Coupon): Coupon = jpaCouponRepository.save(coupon)
}