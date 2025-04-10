package kr.hhplus.be.server.interfaces.product

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController {

    @GetMapping("{id}")
    fun product(@PathVariable id: Long):ProductResponse.Product{
        return ProductResponse.Product()
    }

    @GetMapping("rank")
    fun rank(): ProductResponse.Rank{
        return ProductResponse.Rank()
    }
}