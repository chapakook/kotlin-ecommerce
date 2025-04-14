package kr.hhplus.be.server.interfaces.point

import kr.hhplus.be.server.domain.point.PointCommand
import kr.hhplus.be.server.domain.point.PointService
import kr.hhplus.be.server.interfaces.point.PointResponse.Balance
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/point")
class PointController(
    private val pointService: PointService,
) {
    @GetMapping("{id}")
    fun point(@PathVariable id: Long): Balance {
        val point = pointService.find(PointCommand.Get(id))
        return Balance.from(point)
    }

    @PatchMapping("{id}/charge")
    fun charge(@PathVariable id: Long, @RequestBody req: PointRequest.Charge): Balance {
        val point = pointService.charge(PointCommand.Charge(id, req.amount))
        return Balance.from(point)
    }
}