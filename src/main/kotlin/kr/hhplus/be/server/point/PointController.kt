package kr.hhplus.be.server.point

import kr.hhplus.be.server.swagger.PointApi
import org.springframework.web.bind.annotation.*
import kotlin.random.Random

@RestController
@RequestMapping("/points")
class PointController : PointApi {
    @GetMapping("/{id}")
    override fun find(@PathVariable id:Long): UserPoint {
        return UserPoint(1L, Random.nextLong(10L, 1000L))
    }

    @PatchMapping("/{id}/charge")
    override fun charge(@PathVariable id: Long, @RequestBody amount: Long): UserPoint{
        return UserPoint(1L, Random.nextLong(10L, 1000L))
    }
}