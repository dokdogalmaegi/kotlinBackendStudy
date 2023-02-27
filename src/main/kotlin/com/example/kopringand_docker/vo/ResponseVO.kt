package com.example.kopringand_docker.vo

enum class ResultStatus(val value: String) {
    SUCCESS("Success"),
    FAIL("Fail")
}
data class RespoonseVO(
        val resultMessage: String,
        val status: ResultStatus
) {
}