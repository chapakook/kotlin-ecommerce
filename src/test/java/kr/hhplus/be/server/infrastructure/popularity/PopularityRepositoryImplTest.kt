package kr.hhplus.be.server.infrastructure.popularity

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.hhplus.be.server.domain.popularity.Popularity
import kr.hhplus.be.server.domain.popularity.PopularityRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

class PopularityRepositoryImplTest {
    private val mockPopularityRepository = mockk<PopularityRepository>()
    private val popularityRepositoryImpl = PopularityRepositoryImpl(mockPopularityRepository)

    @Test
    fun `happy - 정상적으로 상위 5개를 조회함`() {
        // given
        val current = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
        val mockPopularityList = listOf(
            Popularity(1L, 1L, "p1", 1, 100, current),
            Popularity(2L, 2L, "p2", 2, 95, current),
            Popularity(3L, 3L, "p3", 3, 90, current),
            Popularity(4L, 4L, "p4", 4, 85, current),
            Popularity(5L, 5L, "p5", 5, 80, current)
        )
        every { mockPopularityRepository.findTop5ByOrderByPopularityDesc() } returns mockPopularityList
        // when
        val result = popularityRepositoryImpl.findTop5ByOrderByPopularityDesc()
        // then
        assertThat(result).isNotNull.hasSize(5)
        assertThat(result).isEqualTo(mockPopularityList)
        verify(exactly = 1) { mockPopularityRepository.findTop5ByOrderByPopularityDesc() }
    }

    @Test
    fun `happy - 정상적으로 저장 진행`() {
        // given
        val current = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
        val popularity = Popularity(1L, 1L, "p1", 1, 100, current)
        every { mockPopularityRepository.save(popularity) } returns popularity
        // when
        val result = popularityRepositoryImpl.save(popularity)
        // then
        assertThat(result).isNotNull
        assertThat(result).isEqualTo(popularity)
        verify(exactly = 1) { mockPopularityRepository.save(popularity) }
    }
}