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
    override fun find(@PathVariable id: Long) : Product{
        return Product(
            productId = 1L,
            name = "test",
            price = 1000,
            createdAt = LocalDateTime.now()
        )
    }
}