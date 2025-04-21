package kr.hhplus.be.server.infrastructure.point

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PointJPARepository : JpaRepository<PointEntity, Long> {
    fun findByUserId(userId: Long): PointEntity?
    fun save(point: PointEntity): PointEntity
}