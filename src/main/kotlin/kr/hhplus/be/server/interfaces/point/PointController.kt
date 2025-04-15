package kr.hhplus.be.server.interfaces.point

import kr.hhplus.be.server.domain.point.PointCommand
import kr.hhplus.be.server.domain.point.PointService
import kr.hhplus.be.server.interfaces.point.PointRequest.Charge
import kr.hhplus.be.server.interfaces.point.PointResponse.ChargeResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/point")
class PointController(
    private val pointService: PointService,
) {
    @GetMapping("{id}")
    fun point(@PathVariable id: Long): ChargeResult = ChargeResult.from(pointService.find(PointCommand.Find(id)))

    @PatchMapping("{id}/charge")
    fun charge(@PathVariable id: Long, @RequestBody req: Charge): ChargeResult =
        ChargeResult.from(pointService.charge(PointCommand.Charge(id, req.amount)))
}