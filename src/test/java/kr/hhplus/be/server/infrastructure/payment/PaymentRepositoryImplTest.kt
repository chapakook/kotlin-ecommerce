package kr.hhplus.be.server.infrastructure.payment

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.hhplus.be.server.domain.payment.Payment
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class PaymentRepositoryImplTest {
    private val paymentJPARepository = mockk<PaymentJPARepository>()
    private val paymentRepository = PaymentRepositoryImpl(paymentJPARepository)

    @Test
    fun `happy - 저장하면 결제를 반환`() {
        // given
        val payment = Payment(1L, 2L, 100)
        every { paymentJPARepository.save(payment) } returns payment

        // when
        val result = paymentRepository.save(payment)

        // then
        assertThat(result, equalTo(payment))
        verify(exactly = 1) { paymentJPARepository.save(payment) }
    }
}