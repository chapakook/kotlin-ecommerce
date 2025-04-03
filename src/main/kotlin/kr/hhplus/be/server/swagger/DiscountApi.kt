package kr.hhplus.be.server.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import kr.hhplus.be.server.discount.Discount
import kr.hhplus.be.server.discount.IssueCouponRequest

@Tag(name = "할인 API")
interface DiscountApi {
    @Operation(summary = "선착순 쿠폰 발급", description = "선착순으로 쿠폰을 발급합니다")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "발급 성공",
            content = [Content(
                schema = Schema(implementation = Discount::class),
                examples = [
                    ExampleObject(
                        name = "UpdateNameOnly",
                        value = """{"discountId": "100","productId": "1", "discountType": "fixed", "value":2000, "expirationDate":"2025-10-03T12:00:00Z"}"""
                    ),
                    ExampleObject(
                        name = "UpdateEmailOnly",
                        value = """{"discountId": "200","productId": "2", "discountType": "fixed", "value":2000, "expirationDate":"2025-10-03T12:00:00Z"}"""
                    )
                ]
            )]
        ),
        ApiResponse(
            responseCode = "404",
            description = "유저를 찾을 수 없음",
            content = [Content(
                schema = Schema(implementation = ErrorResponse::class),
                examples = [ExampleObject(
                    name = "NotFoundExample",
                    value = """{"status": 404, "error": "Not Found", "message": "User with given ID does not exist"}"""
                )]
            )]
        ),
        ApiResponse(
            responseCode = "500",
            description = "서버 내부 오류",
            content = [Content(
                schema = Schema(implementation = ErrorResponse::class),
                examples = [ExampleObject(
                    name = "ServerErrorExample",
                    value = """{"status": 500, "error": "Internal Server Error", "message": "Something went wrong on the server"}"""
                )]
            )]
        )
    )
    fun issueCoupon(request: IssueCouponRequest): Discount
}