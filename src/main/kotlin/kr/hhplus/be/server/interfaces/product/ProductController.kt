package kr.hhplus.be.server.interfaces.product

import kr.hhplus.be.server.application.product.ProductCriteria
import kr.hhplus.be.server.application.product.ProductFacade
import kr.hhplus.be.server.interfaces.product.ProductResponse.ProductV1
import kr.hhplus.be.server.interfaces.product.ProductResponse.RankV1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController(
    private val productFacade: ProductFacade,
) {
    @GetMapping("{id}")
    fun product(@PathVariable id: Long): ProductV1 = ProductV1.of(productFacade.find(ProductCriteria.Find(id)))

    @GetMapping("ranks")
    fun ranks(): List<RankV1> = RankV1.ofList(productFacade.ranks())
}