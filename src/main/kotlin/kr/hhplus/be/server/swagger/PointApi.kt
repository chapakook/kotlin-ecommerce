package kr.hhplus.be.server.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import kr.hhplus.be.server.point.ChargePointRequest
import kr.hhplus.be.server.point.UserPoint

@Tag(name = "포인트 API")
interface PointApi {
    @Operation(summary = "잔액조회 API", description = "userId 이용 잔액을 조회합니다.")
    @Parameter(name = "id", description = "사용자 아이디", example = "1")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "잔액조회 성공",
            content = [
                Content(
                    array = ArraySchema(
                        schema = Schema(
                            implementation = UserPoint::class
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
    fun find(id: Long): UserPoint

    @Operation(summary = "잔액충전 API", description = "userId와 amount을 받아 잔액을 충전합니다.")
    @Parameter(name = "id", description = "사용자 아이디", example = "1")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "잔액조회 성공",
            content = [Content(
                schema = Schema(implementation = ChargePointRequest::class),
                examples = [
                    ExampleObject(
                        name = "UpdateNameOnly",
                        value = """{"amount": "100"}"""
                    ),
                    ExampleObject(
                        name = "UpdateEmailOnly",
                        value = """{"amount": "400"}"""
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
    fun charge(
        id: Long, @RequestBody(
            description = "충전할 포인트 정보",
            required = true,
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ChargePointRequest::class),
                    examples = [
                        ExampleObject(
                            name = "UpdateNameOnly",
                            value = """{"amount": "100"}"""
                        ),
                        ExampleObject(
                            name = "UpdateEmailOnly",
                            value = """{"amount": "400"}"""
                        )
                    ]
                )
            ]
        ) amount: Long
    ): UserPoint
}