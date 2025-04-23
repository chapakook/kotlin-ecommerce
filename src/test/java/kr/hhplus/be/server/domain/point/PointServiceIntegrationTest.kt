package kr.hhplus.be.server.domain.point

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PointServiceIntegrationTest {

    @Autowired
    private lateinit var pointService: PointService

    @Nested
    inner class Find {
        @Test
        fun `happy - 포인트를 찾을 수 있다`() {
            // given
            val userId = 1L
            val cmd = PointCommand.Find(userId)
            // when
            val result = pointService.find(cmd)
            // then
            assertThat(result).isNotNull()
            assertThat(result.userId).isEqualTo(userId)
        }
    }

    @Nested
    inner class Charge {
        @Test
        fun `happy - 포인트 충전이 정상적으로 동작한다`() {
            // given
            val userId = 2L
            val amount = 100L
            val cmd = PointCommand.Charge(userId, amount)
            val expected = pointService.find(PointCommand.Find(userId))
            // when
            val result = pointService.charge(cmd)
            // then
            assertThat(result.userId).isEqualTo(userId)
            assertThat(result.balance).isEqualTo(expected.balance + amount)
        }
    }

    @Nested
    inner class Use {
        @Test
        fun `happy - 포인트 사용이 가능하다`() {
            // given
            val userId = 3L
            val amount = 100L
            val cmd = PointCommand.Use(userId, amount)
            val expected = pointService.find(PointCommand.Find(userId))
            // when
            val result = pointService.use(cmd)
            // then
            assertThat(result.userId).isEqualTo(userId)
            assertThat(result.balance).isEqualTo(expected.balance - amount)
        }
    }
}