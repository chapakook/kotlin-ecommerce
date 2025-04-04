package kr.hhplus.be.server.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import kr.hhplus.be.server.payment.PaymentRequest
import kr.hhplus.be.server.payment.PaymentResponse

@Tag(name = "결제 API")
interface PaymentApi {
    @Operation(summary = "결제요청 API", description = "주문을 요청합니다..")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "결제요청 성공",
            content = [
                Content(
                    array = ArraySchema(
                        schema = Schema(
                            implementation = PaymentResponse::class
                        )
                    )
                )
            ]
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
    fun payment(request: PaymentRequest): PaymentResponse
}