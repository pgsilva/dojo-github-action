package com.konoha.input.resources

import com.konoha.domain.ticket.TicketOperations
import com.konoha.fixtureTickets
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class TicketControllerTest{

    @Test
    fun `should find all tickets in database`(){
        val ops = mockk<TicketOperations>()
        val tickets = fixtureTickets()

        every { ops.findAll() } returns tickets

        val controller = TicketController(ops)

        assertEquals(ResponseEntity.ok().body(tickets),controller.findAll())

        verify(exactly = 1) {
            ops.findAll()
        }
    }
}