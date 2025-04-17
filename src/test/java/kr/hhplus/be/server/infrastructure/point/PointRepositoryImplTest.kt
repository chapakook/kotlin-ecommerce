package kr.hhplus.be.server.infrastructure.point

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.hhplus.be.server.domain.point.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class PointRepositoryImplTest {

    private val mockPointJPARepository = mockk<PointJPARepository>()
    private val pointRepositoryImpl = PointRepositoryImpl(mockPointJPARepository)

    @Test
    fun `happy - userId 로 point 찾기`() {
        // given
        val testPoint = Point(pointId = 1L, userId = 123L, balance = 1000L, updateMillis = System.currentTimeMillis())
        every { mockPointJPARepository.findPointById(123L) } returns testPoint
        // when
        val result = pointRepositoryImpl.findPointById(123L)
        // then
        assertNotNull(result)
        assertThat(testPoint).isEqualTo(result)
        verify(exactly = 1) { mockPointJPARepository.findPointById(123L) }
    }

    @Test
    fun `happy - 포인트 저장`() {
        // given
        val testPoint = Point(pointId = 1L, userId = 123L, balance = 1000L, updateMillis = System.currentTimeMillis())
        every { mockPointJPARepository.save(testPoint) } returns testPoint
        // when
        val result = pointRepositoryImpl.save(testPoint)
        // then
        assertNotNull(result)
        assertEquals(testPoint, result)
        verify(exactly = 1) { mockPointJPARepository.save(testPoint) }
    }
}