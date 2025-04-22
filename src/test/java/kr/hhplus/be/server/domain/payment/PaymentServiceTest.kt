package kr.hhplus.be.server.domain.payment

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PaymentServiceTest {
    private lateinit var paymentService: PaymentService
    private lateinit var paymentRepository: PaymentRepository

    @BeforeEach
    fun setUp() {
        paymentRepository = mockk()
        paymentService = PaymentService(paymentRepository)
    }

    @Nested
    inner class Pay {
        @Test
        fun `happy - paymentCmd 이용 정상적인 결제 요청시 주문된다`() {
            // given
            val orderId = 1L
            val paymentId = 2L
            val amount = 10000L
            val paymentCmd = PaymentCommand.Pay(orderId, 10000L)
            val payment = Payment(paymentId, orderId, amount)
            every { paymentRepository.save(any()) } returns payment
            // when
            val result = paymentService.pay(paymentCmd)
            // then
            assertThat(result.amount).isEqualTo(payment.amount)
        }
    }
}