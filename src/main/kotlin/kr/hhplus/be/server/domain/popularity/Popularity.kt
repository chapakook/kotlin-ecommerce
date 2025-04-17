package kr.hhplus.be.server.domain.popularity

class Popularity(
    val popularityId: Long,
    val productId: Long,
    val productName: String,
    val rank: Int,
    val totalOrder: Int,
    val updateMills: Long,
) {
}