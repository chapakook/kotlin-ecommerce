package kr.hhplus.be.server.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import kr.hhplus.be.server.product.Product

@Tag(name = "상품 API")
interface ProductApi {
    @Operation(summary = "상품조회 API", description = "userId 이용 상품을 조회합니다.")
    @Parameter(name = "id", description = "상품 아이디", example = "1")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "상품조회 성공",
            content = [
                Content(
                    array = ArraySchema(
                        schema = Schema(
                            implementation = Product::class
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
    fun find(id:Long): Product
}