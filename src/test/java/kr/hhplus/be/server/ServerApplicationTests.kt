package kr.hhplus.be.server

import com.ninjasquad.springmockk.MockkBean
import kr.hhplus.be.server.domain.coupon.CouponRepository
import kr.hhplus.be.server.domain.order.OrderRepository
import kr.hhplus.be.server.domain.payment.PaymentRepository
import kr.hhplus.be.server.domain.point.PointRepository
import kr.hhplus.be.server.domain.product.ProductRepository
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

//@SpringBootTest
class ServerApplicationTests {
	@MockkBean
	private lateinit var couponRepository: CouponRepository
	@MockkBean
	private lateinit var orderRepository: OrderRepository
	@MockkBean
	private lateinit var paymentRepository: PaymentRepository
	@MockkBean
	private lateinit var pointRepository: PointRepository
	@MockkBean
	private lateinit var productRepository: ProductRepository
	@Test
	fun contextLoads() {}
}
