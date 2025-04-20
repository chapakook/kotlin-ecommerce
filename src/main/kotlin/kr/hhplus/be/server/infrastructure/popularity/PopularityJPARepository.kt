package kr.hhplus.be.server.infrastructure.popularity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PopularityJPARepository : JpaRepository<PopularityEntity, Long> {
    @Query("SELECT p FROM PopularityEntity p ORDER BY p.rank DESC")
    fun findTop5ByOrderByRankDesc(): List<PopularityEntity>
    fun save(popularity: PopularityEntity): PopularityEntity
}