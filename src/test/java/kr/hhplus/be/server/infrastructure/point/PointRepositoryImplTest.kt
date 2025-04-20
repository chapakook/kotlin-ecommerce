package kr.hhplus.be.server.infrastructure.point

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointRepositoryImplTest {

    private val mockPointJPARepository = mockk<PointJPARepository>()
    private val pointRepositoryImpl = PointRepositoryImpl(mockPointJPARepository)

    @Test
    fun `happy - userId 로 point 찾기`() {
        // given
        val entity = PointEntity(1L, 123L, 1000L, System.currentTimeMillis())
        every { mockPointJPARepository.findPointById(any()) } returns entity
        // when
        val result = pointRepositoryImpl.findPointById(123L)
        // then
        result?.let {
            assertThat(result.pointId).isEqualTo(entity.pointId)
            assertThat(result.userId).isEqualTo(entity.userId)
            assertThat(result.balance).isEqualTo(entity.balance)
        }
        verify(exactly = 1) { mockPointJPARepository.findPointById(any()) }
    }

    @Test
    fun `happy - 포인트 저장`() {
        // given
        val entity = PointEntity(1L, 123L, 1000L, System.currentTimeMillis())
        every { mockPointJPARepository.save(any()) } returns entity
        // when
        val result = pointRepositoryImpl.save(entity.to())
        // then
        assertThat(result).isNotNull
        assertThat(result.pointId).isEqualTo(entity.pointId)
        assertThat(result.userId).isEqualTo(entity.userId)
        assertThat(result.balance).isEqualTo(entity.balance)
        verify(exactly = 1) { mockPointJPARepository.save(any()) }
    }
}