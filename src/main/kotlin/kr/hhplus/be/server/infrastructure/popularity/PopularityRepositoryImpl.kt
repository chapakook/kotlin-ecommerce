package kr.hhplus.be.server.infrastructure.popularity

import kr.hhplus.be.server.domain.popularity.Popularity
import kr.hhplus.be.server.domain.popularity.PopularityRepository
import org.springframework.stereotype.Repository

@Repository
class PopularityRepositoryImpl(
    private val popularityRepository: PopularityRepository,
) : PopularityRepository {
    override fun findTop5ByOrderByPopularityDesc(): List<Popularity> =
        popularityRepository.findTop5ByOrderByPopularityDesc()

    override fun save(popularity: Popularity): Popularity = popularityRepository.save(popularity)

}