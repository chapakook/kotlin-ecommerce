package kr.hhplus.be.server.domain.couponevent

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CouponEventServiceIntegrationTest {
    @Autowired
    private lateinit var couponEventService: CouponEventService

    @Nested
    inner class Find {
        @Test
        fun `happy - 쿠폰이벤트를 찾을 수 있다`() {
            // given
            val couponEventId = 1L
            val cmd = CouponEventCommand.Find(couponEventId)
            // when
            val result = couponEventService.find(cmd)
            // then
            assertThat(result).isNotNull()
            assertThat(result.couponEventId).isEqualTo(couponEventId)
        }
    }

    @Nested
    inner class Issue {
        @Test
        fun `happy - 쿠폰 이벤트재고를 차감할 수 있다`() {
            // given
            val couponEventId = 2L
            val cmd = CouponEventCommand.Issue(couponEventId)
            val expected = couponEventService.find(CouponEventCommand.Find(couponEventId))
            // when
            val result = couponEventService.issue(cmd)
            // then
            assertThat(result.couponEventId).isEqualTo(couponEventId)
            assertThat(result.currentCount).isEqualTo(expected.currentCount + 1)
        }
    }
}