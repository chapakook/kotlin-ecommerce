package kr.hhplus.be.server.application.order

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kr.hhplus.be.server.domain.coupon.CouponInfo
import kr.hhplus.be.server.domain.coupon.CouponService
import kr.hhplus.be.server.domain.order.OrderInfo
import kr.hhplus.be.server.domain.order.OrderService
import kr.hhplus.be.server.domain.payment.PaymentInfo
import kr.hhplus.be.server.domain.payment.PaymentService
import kr.hhplus.be.server.domain.point.PointInfo
import kr.hhplus.be.server.domain.point.PointService
import kr.hhplus.be.server.domain.product.ProductInfo
import kr.hhplus.be.server.domain.product.ProductService
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


class OrderFacadeTest {
    @MockK private lateinit var productService: ProductService
    @MockK private lateinit var couponService: CouponService
    @MockK private lateinit var orderService: OrderService
    @MockK private lateinit var pointService: PointService
    @MockK private lateinit var paymentService: PaymentService
    private lateinit var orderFacade: OrderFacade

    @BeforeEach
    fun setup(){
        MockKAnnotations.init(this)
        orderFacade = OrderFacade(productService, couponService, orderService, pointService, paymentService)
    }

    @Test
    fun `happy - 상품재고 충분, 포인트 충분 시 정상동작한다`() {
        // Given
        val productId = 1L
        val orderId = 3L
        val quantity = 2
        val pointId = 4L
        val userId = 5L
        val cri = OrderCriteria.Order(userId, productId,1000L, 2, null)
        val product = ProductInfo.Product(productId,"test",2000L, 100)
        val order = OrderInfo.Order(orderId, productId, quantity, 2000L, LocalDateTime.now())
        val point = PointInfo.Point(pointId, userId , 100000000)
        val payment = PaymentInfo.Payment(1L, 2000L, 2000L, 0L)
        val fakeOrder = OrderResult.Order(orderId)
        every { productService.find(any()) } returns product
        every { productService.reduce(any()) } returns product
        every { orderService.order(any()) } returns order
        every { couponService.use(any()) } returns null
        every { pointService.use(any()) } returns point
        every { paymentService.payment(any()) } returns payment
        // When
        val result = orderFacade.order(cri)
        // Then
        assertThat(result).isEqualTo(fakeOrder)
    }
}