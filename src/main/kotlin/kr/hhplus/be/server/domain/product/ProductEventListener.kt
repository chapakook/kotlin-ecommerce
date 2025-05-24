package kr.hhplus.be.server.domain.product

import kr.hhplus.be.server.domain.stock.StockEvent
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ProductEventListener(
    private val productService: ProductService,
) {
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handler(event: StockEvent.StockDeducted) {
        productService.increaseRank(with(event) { ProductCommand.Rank(productId, quantity) })
    }
}