package kr.hhplus.be.server.interfaces.point

import jakarta.validation.constraints.Positive
import kr.hhplus.be.server.domain.point.PointCommand
import kr.hhplus.be.server.domain.point.PointService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/point")
class PointController (
    private val pointService: PointService,
){
    @GetMapping("{id}")
    fun point(@PathVariable id: Long): PointResponse.Point {
        val point = pointService.find(PointCommand.Get(id))
        return PointResponse().ofPoint(point)
    }

    @PatchMapping("charge")
    fun charge(@RequestBody req: PointRequest.Charge):PointResponse.Point{
        return PointResponse.Point(1L)
    }

    @PatchMapping("use")
    fun use(@RequestBody req: PointRequest.Use):PointResponse.Point{
        return PointResponse.Point(1L)
    }
}