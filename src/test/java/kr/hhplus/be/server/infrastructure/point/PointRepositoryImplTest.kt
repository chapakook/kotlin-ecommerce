package kr.hhplus.be.server.infrastructure.point

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.hhplus.be.server.domain.point.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointRepositoryImplTest {

    private val jpaPointRepository = mockk<JpaPointRepository>()
    private val pointRepositoryImpl = PointRepositoryImpl(jpaPointRepository)

    @Test
    fun `happy - userId 로 point 찾기`() {
        // given
        val point = Point(1L, 123L, 1000L, 0, 0)
        every { jpaPointRepository.findByUserId(any()) } returns point
        // when
        val result = pointRepositoryImpl.findByUserId(123L)
        // then
        result?.let {
            assertThat(result.pointId).isEqualTo(point.pointId)
            assertThat(result.userId).isEqualTo(point.userId)
            assertThat(result.balance).isEqualTo(point.balance)
        }
        verify(exactly = 1) { jpaPointRepository.findByUserId(any()) }
    }

    @Test
    fun `happy - 포인트 저장`() {
        // given
        val point = Point(1L, 123L, 1000L, 0, 0)
        every { jpaPointRepository.save(any()) } returns point
        // when
        val result = pointRepositoryImpl.save(point)
        // then
        assertThat(result).isNotNull
        assertThat(result.pointId).isEqualTo(point.pointId)
        assertThat(result.userId).isEqualTo(point.userId)
        assertThat(result.balance).isEqualTo(point.balance)
        verify(exactly = 1) { jpaPointRepository.save(any()) }
    }
}