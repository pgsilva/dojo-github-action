package com.konoha.dojo.input.resources

import com.konoha.dojo.domain.ticket.Ticket
import com.konoha.dojo.domain.ticket.TicketOperations
import com.konoha.dojo.domain.ticket.toEntity
import com.konoha.dojo.input.resources.req.TicketRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/v1/ticket")
class TicketController(
    val ops: TicketOperations
) {

    @GetMapping
    fun findAll():ResponseEntity<MutableIterable<Ticket>> = ResponseEntity.ok().body(ops.findAll())

    @GetMapping
    fun findById(@RequestParam id:UUID) :ResponseEntity<Ticket> =
        ops.findById(id).map { task ->
        ResponseEntity.ok(task)
    }.orElse(
        ResponseEntity
            .notFound()
            .build()
    )

    @PostMapping
    fun save(@RequestBody request: TicketRequest): ResponseEntity<Ticket> {
        val saved = ops.save(request)
        return ResponseEntity.ok(saved)
    }

    @PutMapping
    fun put( @RequestParam id:UUID,
             @RequestBody request: TicketRequest):ResponseEntity<Ticket> {

        val updated = ops.put(request, id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity
            .ok()
            .body(ops.put(request, id))
    }

    @DeleteMapping
    fun delete(
        @RequestParam id:UUID
    ): ResponseEntity<Void> {

        val deleted = ops.delete(id)

        if (!deleted) return ResponseEntity.notFound().build()

        return ResponseEntity<Void>(HttpStatus.ACCEPTED)
    }

}