package com.example.kopringand_docker.vo

data class FailResponseVO(
    override val resultMessage: String,
    val errorMessage: String,
    override val status: ResultStatus = ResultStatus.FAIL,
) : ResponseVO(resultMessage, status)
