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
import kr.hhplus.be.server.domain.product.ProductInfo
import kr.hhplus.be.server.domain.product.ProductService
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class OrderFacadeTest {
    @MockK private lateinit var productService: ProductService
    @MockK private lateinit var couponService: CouponService
    @MockK private lateinit var orderService: OrderService
    @MockK private lateinit var paymentService: PaymentService
    private lateinit var orderFacade: OrderFacade

    @BeforeEach
    fun setup(){
        MockKAnnotations.init(this)
        orderFacade = OrderFacade(productService, couponService, orderService, paymentService)
    }

    @Test
    fun `happy - 상품의 재고가 있을때 정상 주문된다`() {
        // Given
        val cri = OrderCriteria.Order(1L,2L,2)
        val product = ProductInfo.Product(1L,"test",2000L, 100)
        val order = OrderInfo.Order(1L)
        val payment = PaymentInfo.Payment(1L)
        val fakeOrder = OrderResult.Order(1L)
        every { productService.getProduct(any()) } returns product
        every { orderService.order(any()) } returns order
        every { paymentService.payment(any()) } returns payment
        // When
        val result = orderFacade.order(cri)
        // Then
        assertThat(result).isEqualTo(fakeOrder)
    }

    @Test
    fun `happy - 주문 수량이 재고보다 작을때 주문된다`() {
        // Given
        val cri = OrderCriteria.Order(1L,2L,2)
        val product = ProductInfo.Product(1L,"test",2000L, 100)
        val order = OrderInfo.Order(1L)
        val payment = PaymentInfo.Payment(1L)
        val fakeOrder = OrderResult.Order(1L)
        every { productService.getProduct(any()) } returns product
        every { orderService.order(any()) } returns order
        every { paymentService.payment(any()) } returns payment
        // When
        val result = orderFacade.order(cri)
        // Then
        assertThat(result).isEqualTo(fakeOrder)
    }

    @Test
    fun `happy - 주문 수량이 재고랑 같을때 주문된다`() {
        // Given
        val cri = OrderCriteria.Order(1L,2L,2)
        val product = ProductInfo.Product(1L,"test",2000L, 2)
        val order = OrderInfo.Order(1L)
        val payment = PaymentInfo.Payment(1L)
        val fakeOrder = OrderResult.Order(1L)
        every { productService.getProduct(any()) } returns product
        every { orderService.order(any()) } returns order
        every { paymentService.payment(any()) } returns payment
        // When
        val result = orderFacade.order(cri)
        // Then
        assertThat(result).isEqualTo(fakeOrder)
    }

    @Test
    fun `bad - 상품 재고가 없는 경우 정상적으로 주문이 되지않는다`(){
        // Given
        val cri = OrderCriteria.Order(1L,2L,2)
        val product = ProductInfo.Product(1L,"test",2000L, 0)
        every { productService.getProduct(any()) } returns product
        // When & Then
        assertThatThrownBy { orderFacade.order(cri) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `bad - 주문수량이 재고량을 초과할 경우 주문이 되지않는다`(){
        // Given
        val cri = OrderCriteria.Order(1L,2L,2)
        val product = ProductInfo.Product(1L,"test",2000L, 1)
        every { productService.getProduct(any()) } returns product
        // When & Then
        assertThatThrownBy { orderFacade.order(cri) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}