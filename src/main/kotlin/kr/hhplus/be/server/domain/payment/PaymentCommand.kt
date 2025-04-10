package kr.hhplus.be.server.domain.payment

class PaymentCommand {
    data class Payment(
        val orderId: Long,
        val userId: Long,
        val totalAmount: Long,
    ){
        init {
            require(orderId > 0) { "orderId must be positive." }
            require(userId > 0) { "userId must be positive." }
            require(totalAmount >= 0) { "totalAmount must be not negative." }
        }
    }
}