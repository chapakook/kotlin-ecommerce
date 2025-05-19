package kr.hhplus.be.server.application

import kr.hhplus.be.server.application.order.OrderCriteria
import kr.hhplus.be.server.application.order.OrderFacade
import kr.hhplus.be.server.application.product.ProductFacade
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OrderProductFacadeIntegrationTest(
    @Autowired private val orderFacade: OrderFacade,
    @Autowired private val productFacade: ProductFacade,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun `happy - 인기상품이 조회된다`() {
        // given
        orderFacade.order(OrderCriteria.Order(1L, 1L, 10, null))
        orderFacade.order(OrderCriteria.Order(2L, 2L, 2, null))
        orderFacade.order(OrderCriteria.Order(3L, 3L, 3, null))
        orderFacade.order(OrderCriteria.Order(4L, 4L, 5, null))
        // when
        val result = productFacade.rank()
        // then
        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result).hasSize(4)
    }
}