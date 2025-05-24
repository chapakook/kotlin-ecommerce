package kr.hhplus.be.server.support

enum class RedisPrefix(val prefix: String) {
    CACHE_PRODUCTS("products"),

    CACHE_RANK("rank"),
    CACHE_PRODUCTS_RANK("${CACHE_RANK.prefix}:${CACHE_PRODUCTS.prefix}"),
    CACHE_PRODUCT_RANK_DAILY("${CACHE_PRODUCTS_RANK.prefix}:daily"),
}