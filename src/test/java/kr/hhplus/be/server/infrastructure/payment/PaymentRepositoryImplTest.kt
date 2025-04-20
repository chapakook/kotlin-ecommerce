package kr.hhplus.be.server.infrastructure.payment

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PaymentRepositoryImplTest {
    private val paymentJPARepository = mockk<PaymentJPARepository>()
    private val paymentRepository = PaymentRepositoryImpl(paymentJPARepository)

    @Test
    fun `happy - 저장하면 결제를 반환`() {
        // given
        val entity = PaymentEntity(1L, 2L, 100, System.currentTimeMillis())
        every { paymentJPARepository.save(any()) } returns entity

        // when
        val result = paymentRepository.save(entity.to())

        // then
        assertThat(result.paymentId).isEqualTo(entity.paymentId)
        assertThat(result.orderId).isEqualTo(entity.orderId)
        assertThat(result.amount).isEqualTo(entity.amount)
        assertThat(result.createMillis).isEqualTo(entity.createMillis)
        verify(exactly = 1) { paymentJPARepository.save(any()) }
    }
}