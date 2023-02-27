package com.example.kopringand_docker.vo

import com.fasterxml.jackson.annotation.JsonUnwrapped

data class SuccessResponseVO(
    override val resultMessage: String,
    @JsonUnwrapped
    val resultData: Any,
    override val status: ResultStatus = ResultStatus.SUCCESS,
) : ResponseVO(resultMessage, status)
