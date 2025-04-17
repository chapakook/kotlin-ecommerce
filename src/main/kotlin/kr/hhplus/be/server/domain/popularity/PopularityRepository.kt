package kr.hhplus.be.server.domain.popularity

import org.springframework.stereotype.Repository

@Repository
interface PopularityRepository {
    fun findTop5ByOrderByPopularityDesc(): List<Popularity>
    fun save(popularity: Popularity): Popularity
}
