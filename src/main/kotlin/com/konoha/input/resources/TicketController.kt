package com.konoha.input.resources

import com.konoha.domain.ticket.Ticket
import com.konoha.domain.ticket.TicketOperations
import com.konoha.input.resources.req.TicketRequest
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
    fun findAll(): ResponseEntity<out MutableIterable<Ticket>> = ResponseEntity.ok().body(ops.findAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<out Ticket> =
        ops.findById(id).map { task ->
            ResponseEntity.ok(task)
        }.orElse(
            ResponseEntity
                .notFound()
                .build()
        )

    @PostMapping
    fun save(@RequestBody request: TicketRequest): ResponseEntity<out Ticket> {
        val saved = ops.save(request)
        return ResponseEntity.ok(saved)
    }

    @PutMapping
    fun put(
        @RequestParam id: UUID,
        @RequestBody request: TicketRequest
    ): ResponseEntity<out Ticket> {

        val updated = ops.put(request, id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity
            .ok(updated)
    }

    @DeleteMapping
    fun delete(
        @RequestParam id: UUID
    ): ResponseEntity<out Void> {

        val deleted = ops.delete(id)

        if (!deleted) return ResponseEntity.notFound().build()

        return ResponseEntity<Void>(HttpStatus.ACCEPTED)
    }

}