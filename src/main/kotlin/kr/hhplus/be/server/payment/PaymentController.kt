package kr.hhplus.be.server.payment

import jakarta.validation.Valid
import kr.hhplus.be.server.order.OrderResponse
import kr.hhplus.be.server.swagger.PaymentApi
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/payments")
class PaymentController: PaymentApi {

    @PostMapping("/payment")
    override fun payment(@RequestBody @Valid request: PaymentRequest): PaymentResponse{
        return PaymentResponse(
            paymentId = 1L,
            orderId = 2L,
            amount = 5000,
            paymentStatus = PaymentStatus.SUCCESS,
            createdAt = LocalDateTime.now()
        )
    }
}