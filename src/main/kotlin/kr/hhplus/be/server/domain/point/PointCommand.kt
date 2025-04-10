package kr.hhplus.be.server.domain.point

class PointCommand {
    data class Get(
        val userId  : Long,
    ){
        init {
            require(userId > 0){"userId must be positive."}
        }
    }
    data class Charge(
        val userId : Long,
        val amount: Long,
    ){
        init {
            require(userId > 0){"userId must be positive."}
            require(amount > 0){"amount must be positive."}
        }
    }
    data class Use(
        val userId : Long,
        val amount: Long,
        val code: String?,
    ){
        init {
            require(userId > 0){"userId must be positive."}
            require(amount >= 0){"amount must be not negative."}
        }
    }
}