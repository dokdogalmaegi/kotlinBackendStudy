package com.example.kopringand_docker.vo

enum class ResultStatus(val value: String) {
    SUCCESS("Success"),
    FAIL("Fail")
}
abstract class ResponseVO(open val resultMessage: String, open val status: ResultStatus)
