package kr.hhplus.be.server.infrastructure.popularity

import kr.hhplus.be.server.domain.popularity.Popularity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PopularityJPARepository : JpaRepository<Popularity, Long> {
    fun findTop5ByOrderByPopularityDesc(): List<Popularity>
    fun findProductStockByUserId(userId: String): List<Popularity>
}