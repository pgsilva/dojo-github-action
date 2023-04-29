package com.konoha.dojo.input.resources

import com.konoha.dojo.domain.login.SignIn
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1")
class LoginController(
    private val processor: SignIn
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("login")
    fun login(@RequestBody access: Access) {
        log.info("request to access received from [$access]")

        processor.signIn(access)
    }
}

data class Access(
    val user: String,
    val password: String
){
    override fun toString(): String {
        return "Access(user='$user', password='${password.map { "*" }.joinToString(separator = "")}')"
    }
}