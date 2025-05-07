package kr.hhplus.be.server.infrastructure.popularity

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.hhplus.be.server.domain.popularity.Popularity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

class PopularityRepositoryImplTest {
    private val popularityJPARepository = mockk<PopularityJPARepository>()
    private val popularityRepositoryImpl = PopularityRepositoryImpl(popularityJPARepository)

    @Test
    fun `happy - 정상적으로 상위 5개를 조회함`() {
        // given
        val current = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
        val popularities = listOf(
            Popularity(1L, 1L, "p1", 1, 100, current),
            Popularity(2L, 2L, "p2", 2, 95, current),
            Popularity(3L, 3L, "p3", 3, 90, current),
            Popularity(4L, 4L, "p4", 4, 85, current),
            Popularity(5L, 5L, "p5", 5, 80, current)
        )
        every { popularityJPARepository.findTop5ByOrderByRankDesc() } returns popularities
        // when
        val result = popularityRepositoryImpl.findTop5ByOrderByRankDesc()
        // then
        assertThat(result).isNotNull.hasSize(5)
        assertThat(result.stream().map(Popularity::popularityId)).containsAll(
            popularities.stream().map(Popularity::popularityId).toList()
        )
        verify(exactly = 1) { popularityJPARepository.findTop5ByOrderByRankDesc() }
    }

    @Test
    fun `happy - 정상적으로 저장 진행`() {
        // given
        val current = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
        val popularity = Popularity(1L, 1L, "p1", 1, 100, current)
        every { popularityJPARepository.save(any()) } returns popularity
        // when
        val result = popularityRepositoryImpl.save(popularity)
        // then
        assertThat(result).isNotNull
        assertThat(result.popularityId).isEqualTo(popularity.popularityId)
        assertThat(result.productId).isEqualTo(popularity.productId)
        assertThat(result.productName).isEqualTo(popularity.productName)
        assertThat(result.rank).isEqualTo(popularity.rank)
        assertThat(result.totalOrder).isEqualTo(popularity.totalOrder)
        assertThat(result.updateMills).isEqualTo(popularity.updateMills)
        verify(exactly = 1) { popularityJPARepository.save(any()) }
    }
}