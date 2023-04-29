package com.konoha.dojo.domain.login

import com.konoha.dojo.input.resources.Access
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class SignIn {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun signIn(access: Access) {
        log.info("login service")
    }
}