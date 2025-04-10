package kr.hhplus.be.server.domain.payment

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested

class PaymentServiceTest {
    private lateinit var paymentService: PaymentService
    private lateinit var paymentRepository: PaymentRepository
    @BeforeEach
    fun setUp() {
        paymentRepository = mockk()
        paymentService = PaymentService(paymentRepository)
    }
    @Nested
    inner class Payment {
        @Test
        fun `happy - paymentCmd 이용 정상적인 결제 요청시 주문된다`() {
            // given
            val paymentCmd = PaymentCommand.Payment(1L,1L,10000L)
            val fake = PaymentInfo.Payment(1L, 10000L)
            every { paymentRepository.insert() } returns fake
            // when
            val result = paymentService.payment(paymentCmd)
            // then
            assertThat(result).isEqualTo(fake)
        }
    }
}