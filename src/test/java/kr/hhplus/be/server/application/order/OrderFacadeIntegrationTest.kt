package kr.hhplus.be.server.application.order

import com.ninjasquad.springmockk.SpykBean
import io.mockk.verify
import kr.hhplus.be.server.domain.order.ExternalNotify
import kr.hhplus.be.server.domain.product.ProductRankingRepository
import kr.hhplus.be.server.support.ErrorCode.OUT_OF_POINT
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OrderFacadeIntegrationTest(
    @Autowired private val orderFacade: OrderFacade,
    @Autowired private val productRankRepository: ProductRankingRepository
) {
    @SpykBean
    private lateinit var externalNotify: ExternalNotify

    @Test
    fun `happy - 주문에 성공하고 랭킹이 적용된다`() {
        // given
        val productId = 1L
        val quantity = 10
        val cri = OrderCriteria.Order(1L, productId, quantity, null)
        // when
        val before = productRankRepository.findByProductId(productId)
        val result = orderFacade.order(cri)
        val after = productRankRepository.findByProductId(productId)
        // then
        assertThat(result.productId).isEqualTo(productId)
        assertThat(after).isNotNull
        after?.let {
            assertThat(it.productId).isEqualTo(productId)
            if (before != null) assertThat(it.score).isEqualTo(before.score + quantity.toDouble())
            if (before == null) assertThat(it.score).isEqualTo(quantity.toDouble())
        }
    }

    @Test
    fun `bad - 주문에 실패하고 랭킹이 적용되지않는다`() {
        // given
        val productId = 1L
        orderFacade.order(OrderCriteria.Order(1L, productId, 10, null))
        val expected = productRankRepository.findByProductId(productId)
        val cri = OrderCriteria.Order(1L, productId, 999999, null)
        // when & then
        assertThatThrownBy { orderFacade.order(cri) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContainingAll(OUT_OF_POINT.message)
        val result = productRankRepository.findByProductId(productId)
        assertThat(result!!.score).isEqualTo(expected!!.score)
    }

    @Test
    fun `happy - OrderEvent_OrderCompleted 이벤트를 통해 주문 후 외부 데이터 플랫폼에 notify 한다`() {
        // given
        val cri = OrderCriteria.Order(1L, 1L, 10, null)
        // when
        orderFacade.order(cri)
        // then
        verify(timeout = 1000) { externalNotify.notify(any()) }
    }
}