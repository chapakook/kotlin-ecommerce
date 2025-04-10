package kr.hhplus.be.server.domain.payment

class PaymentCommand {
    data class Payment(
        val userId: Long,
        val totalAmount: Long,
    ){
        init {
            require(userId > 0) { "userId must be positive." }
            require(totalAmount > 0) { "totalAmount must be positive." }
        }
    }
}