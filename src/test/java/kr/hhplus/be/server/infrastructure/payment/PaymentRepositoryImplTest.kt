package kr.hhplus.be.server.infrastructure.payment

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.hhplus.be.server.domain.payment.Payment
import kr.hhplus.be.server.domain.payment.PaymentStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PaymentRepositoryImplTest {
    private val jpaPaymentRepository = mockk<JpaPaymentRepository>()
    private val paymentRepository = PaymentRepositoryImpl(jpaPaymentRepository)

    @Test
    fun `happy - 저장하면 결제를 반환`() {
        // given
        val payment = Payment(1L, 2L, 100, PaymentStatus.Pending, 0)
        every { jpaPaymentRepository.save(any()) } returns payment

        // when
        val result = paymentRepository.save(payment)

        // then
        assertThat(result.paymentId).isEqualTo(payment.paymentId)
        assertThat(result.orderId).isEqualTo(payment.orderId)
        assertThat(result.amount).isEqualTo(payment.amount)
        assertThat(result.createMillis).isEqualTo(payment.createMillis)
        verify(exactly = 1) { jpaPaymentRepository.save(any()) }
    }
}