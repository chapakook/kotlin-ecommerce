package kr.hhplus.be.server.product

import kr.hhplus.be.server.swagger.ProductApi
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/products")
class ProductController : ProductApi {

    @GetMapping("/{id}")
    override fun find(@PathVariable id: Long): Product {
        return Product(
            productId = 1L,
            name = "test",
            price = 1000,
            createdAt = LocalDateTime.now()
        )
    }

    @GetMapping("/rank")
    override fun findRankProducts(): List<Product> {
        return listOf<Product>(
            Product(
                productId = 1L,
                name = "test",
                price = 1000,
                createdAt = LocalDateTime.now()
            ),
            Product(
                productId = 2L,
                name = "test2",
                price = 2000,
                createdAt = LocalDateTime.now()
            ),
            Product(
                productId = 3L,
                name = "test3",
                price = 3000,
                createdAt = LocalDateTime.now()
            )
        )
    }
}