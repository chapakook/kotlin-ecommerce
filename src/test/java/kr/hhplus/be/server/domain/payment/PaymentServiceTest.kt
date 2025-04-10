package kr.hhplus.be.server.domain.payment

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested

class PaymentServiceTest {
    private lateinit var paymentService: PaymentService
    @BeforeEach
    fun setUp() {
        paymentService = PaymentService()
    }
    @Nested
    inner class Payment {
        @Test
        fun `happy - paymentCmd 이용 정상적인 결제 요청시 주문된다`() {
            // given
            val paymentCmd = PaymentCommand.Payment()
            val fakePayment = PaymentInfo.Payment(1L)
            // when
            val result = paymentService.payment(paymentCmd)
            // then
            assertThat(result).isEqualTo(fakePayment)
        }
    }

}