package com.example.kopringand_docker.vo

import com.fasterxml.jackson.annotation.JsonUnwrapped

data class SuccessResponseVO(
    override val resultMessage: String,
    override val status: ResultStatus,
    @JsonUnwrapped
    val resultData: Any
) : ResponseVO(resultMessage, status)
