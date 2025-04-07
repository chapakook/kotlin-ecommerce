package kr.hhplus.be.server.swagger

data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String
)
