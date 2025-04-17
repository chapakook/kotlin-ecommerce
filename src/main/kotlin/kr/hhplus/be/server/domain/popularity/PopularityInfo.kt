package kr.hhplus.be.server.domain.popularity

class PopularityInfo {
    class PopularityInfo(
        val popularityId: Long,
        val productId: Long,
        val productName: String,
        val rank: Int,
        val totalOrder: Int,
        val updateMills: Long,
    ) {
        companion object {
            fun ofList(popularity: List<Popularity>): List<PopularityInfo> = popularity.map { p ->
                PopularityInfo(
                    p.popularityId,
                    p.productId,
                    p.productName,
                    p.rank,
                    p.totalOrder,
                    p.updateMills
                )
            }
        }
    }
}