package kr.hhplus.be.server.infrastructure.popularity

import kr.hhplus.be.server.domain.popularity.Popularity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PopularityJPARepository : JpaRepository<Popularity, Long> {
    @Query("SELECT p FROM Popularity p ORDER BY p.rank DESC")
    fun findTop5ByOrderByRankDesc(): List<Popularity>
    fun save(popularity: Popularity): Popularity
}