package kr.hhplus.be.server.support

enum class RedisPrefix(val prefix: String) {
    CACHE_PRODUCTS("products"),

    CACHE_RANK("rank"),
    CACHE_PRODUCTS_RANK("${CACHE_RANK.prefix}:${CACHE_PRODUCTS.prefix}"),
    CACHE_PRODUCT_RANK_DAILY("${CACHE_PRODUCTS_RANK.prefix}:daily"),

    CACHE_QUEUE("queue"),
    CACHE_COUPONS("coupons"),
    CACHE_COUPON_EVENTS("coupon-events"),
    CACHE_QUEUE_COUPON_EVENTS("${CACHE_QUEUE.prefix}:${CACHE_COUPON_EVENTS.prefix}"),
}