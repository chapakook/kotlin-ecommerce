package kr.hhplus.be.server.interfaces.product

import kr.hhplus.be.server.domain.product.ProductCommand
import kr.hhplus.be.server.domain.product.ProductService
import kr.hhplus.be.server.interfaces.product.ProductResponse.ProductResult
import kr.hhplus.be.server.interfaces.product.ProductResponse.RankResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController(
    private val productService: ProductService,
) {

    @GetMapping("{id}")
    fun product(@PathVariable id: Long): ProductResult =
        ProductResult.from(productService.find(ProductCommand.Find(id)))

    @GetMapping("rank")
    fun rank(): List<RankResult> = RankResult.of(productService.ranks())
}