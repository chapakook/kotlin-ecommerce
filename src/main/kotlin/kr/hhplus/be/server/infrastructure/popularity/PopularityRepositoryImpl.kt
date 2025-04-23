package kr.hhplus.be.server.infrastructure.popularity

import kr.hhplus.be.server.domain.popularity.Popularity
import kr.hhplus.be.server.domain.popularity.PopularityRepository
import org.springframework.stereotype.Repository

@Repository
class PopularityRepositoryImpl(
    private val popularityJPARepository: PopularityJPARepository,
) : PopularityRepository {
    override fun findTop5ByOrderByRankDesc(): List<Popularity> = popularityJPARepository.findTop5ByOrderByRankDesc()

    override fun save(popularity: Popularity): Popularity = popularityJPARepository.save(popularity)
}