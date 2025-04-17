package kr.hhplus.be.server.domain.stock

import kr.hhplus.be.server.support.ErrorCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StockTest {
    @Test
    fun `happy - 요구수량보가 재고가 많으면 정상 재고차감할 수 있다`() {
        // given
        val total = 100
        val stock = Stock(
            1L,
            2L,
            total,
            0
        )
        val quantity = 10
        // when
        stock.deduct(quantity)
        // then
        assertThat(stock.quantity).isEqualTo(total - quantity)
    }

    @Test
    fun `happy - 요구수량보가 재고와 같아도 정상 재고차감할 수 있다`() {
        // given
        val total = 100
        val stock = Stock(
            1L,
            2L,
            total,
            0
        )
        val quantity = 100
        // when
        stock.deduct(quantity)
        // then
        assertThat(stock.quantity).isEqualTo(total - quantity)
    }

    @Test
    fun `bad - 요구수량보이 재고를 초과하면 IllegalArgumentException 반환한다`() {
        // given
        val total = 100
        val stock = Stock(
            1L,
            2L,
            total,
            0
        )
        val quantity = 1000
        // when & then
        val exception = assertThrows<IllegalArgumentException> { stock.deduct(quantity) }
        assertThat(exception.message).isEqualTo(ErrorCode.OUT_OF_STOCK.message)
    }

    @Test
    fun `bad - 요청수량과 상관없이 재고 없으면 IllegalArgumentException 반환한다`() {
        // given
        val total = 0
        val stock = Stock(
            1L,
            2L,
            total,
            0
        )
        val quantity = 1000
        // when & then
        val exception = assertThrows<IllegalArgumentException> { stock.deduct(quantity) }
        assertThat(exception.message).isEqualTo(ErrorCode.OUT_OF_STOCK.message)
    }

    @Test
    fun `bad - 요청수량 음수면 IllegalArgumentException 반환한다`() {
        // given
        val total = 100
        val stock = Stock(
            1L,
            2L,
            total,
            0
        )
        val quantity = -1000
        // when & then
        val exception = assertThrows<IllegalArgumentException> { stock.deduct(quantity) }
        assertThat(exception.message).isEqualTo(ErrorCode.QUANTITY_MUST_BE_POSITIVE.message)
    }

    @Test
    fun `bad - 요청수량 0 이면 IllegalArgumentException 반환한다`() {
        // given
        val total = 100
        val stock = Stock(
            1L,
            2L,
            total,
            0
        )
        val quantity = 0
        // when & then
        val exception = assertThrows<IllegalArgumentException> { stock.deduct(quantity) }
        assertThat(exception.message).isEqualTo(ErrorCode.QUANTITY_MUST_BE_POSITIVE.message)
    }
}