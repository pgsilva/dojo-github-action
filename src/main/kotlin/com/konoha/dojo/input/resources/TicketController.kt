package com.konoha.dojo.input.resources

import com.konoha.dojo.domain.ticket.TicketOperations
import com.konoha.dojo.input.resources.req.TicketRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/ticket")
class TicketController(
    val ops: TicketOperations
) {

    @GetMapping
    fun findAll() = ops.findAll()

    @PostMapping
    fun save(@RequestBody request: TicketRequest) = ops.put(request)

}