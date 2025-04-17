package kr.hhplus.be.server.domain.popularity

import kr.hhplus.be.server.domain.popularity.PopularityInfo.PopularityInfo
import org.springframework.stereotype.Service

@Service
class PopularityService(
    val popularityRepository: PopularityRepository,
) {
    fun ranks(): List<PopularityInfo> = PopularityInfo.ofList(popularityRepository.findTop5())
}