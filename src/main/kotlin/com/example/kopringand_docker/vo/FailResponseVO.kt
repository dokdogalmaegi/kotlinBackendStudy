package com.example.kopringand_docker.vo

data class FailResponseVO(
    override val resultMessage: String,
    override val status: ResultStatus = ResultStatus.FAIL,
    val errorMessage: String
) : ResponseVO(resultMessage, status)
