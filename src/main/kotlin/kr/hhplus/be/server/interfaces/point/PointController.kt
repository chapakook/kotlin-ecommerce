package kr.hhplus.be.server.interfaces.point

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/point")
class PointController {

    @GetMapping("{id}")
    fun point(@PathVariable id: Long): PointResponse.Point {
        return PointResponse.Point()
    }

    @PatchMapping("charge")
    fun charge(@RequestBody req: PointRequest.Charge):PointResponse.Point{
        return PointResponse.Point()
    }

    @PatchMapping("use")
    fun use(@RequestBody req: PointRequest.Use):PointResponse.Point{
        return PointResponse.Point()
    }
}