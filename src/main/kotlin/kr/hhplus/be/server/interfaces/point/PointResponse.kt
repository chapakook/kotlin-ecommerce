package kr.hhplus.be.server.interfaces.point

import kr.hhplus.be.server.domain.point.PointInfo

class PointResponse {
    data class Point (val balance: Long)
    fun ofPoint(point: PointInfo.Point): Point = Point(point.balance)
}