package com.konoha.input.resources

import com.konoha.domain.ticket.Ticket
import com.konoha.domain.ticket.TicketOperations
import com.konoha.fixtureString
import com.konoha.fixtureTicket
import com.konoha.fixtureTickets
import com.konoha.input.resources.req.TicketRequest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

class TicketControllerTest {

    @Test
    fun `should find all tickets in database`() {
        val ops = mockk<TicketOperations>()
        val tickets = fixtureTickets()

        every { ops.findAll() } returns tickets

        val controller = TicketController(ops)

        assertEquals(ResponseEntity.ok().body(tickets), controller.findAll())

        verify(exactly = 1) {
            ops.findAll()
        }
    }

    @Test
    fun `should find one tickets in database`() {
        val ops = mockk<TicketOperations>()
        val ticket = fixtureTicket()
        val id = UUID.randomUUID()

        every { ops.findById(id) } returns Optional.of(ticket)

        val controller = TicketController(ops)

        assertEquals(ResponseEntity.ok().body(ticket), controller.findById(id))

        verify(exactly = 1) {
            ops.findById(id)
        }
    }

    @Test
    fun `should return not found when ticket do not exists in get by id operation`() {
        val ops = mockk<TicketOperations>()
        val id = UUID.randomUUID()

        every { ops.findById(id) } returns Optional.empty()

        val controller = TicketController(ops)

        val notFound: ResponseEntity<Ticket> = ResponseEntity.notFound().build()
        assertEquals(notFound, controller.findById(id))

        verify(exactly = 1) {
            ops.findById(id)
        }
    }

    @Test
    fun `should save ticket in database`() {
        val ops = mockk<TicketOperations>()
        val ticket = fixtureTicket()

        val request = TicketRequest(
            movie = ticket.movie,
            theater = ticket.theater,
            room = ticket.room,
            time = ticket.time,
            language = ticket.language,
            subtitles = ticket.subtitles,
            exhibition = ticket.exhibition
        )


        every { ops.save(request) } returns ticket

        val controller = TicketController(ops)

        assertEquals(ResponseEntity.ok().body(ticket), controller.save(request))

        verify(exactly = 1) {
            ops.save(request)
        }
    }

    @Test
    fun `should put ticket in database`() {
        val ops = mockk<TicketOperations>()
        val ticket = fixtureTicket().copy(
            movie = fixtureString()
        )

        val request = TicketRequest(
            movie = ticket.movie,
            theater = ticket.theater,
            room = ticket.room,
            time = ticket.time,
            language = ticket.language,
            subtitles = ticket.subtitles,
            exhibition = ticket.exhibition
        )


        every { ops.put(request, ticket.id) } returns ticket

        val controller = TicketController(ops)

        assertEquals(ResponseEntity.ok().body(ticket), controller.put(ticket.id, request))

        verify(exactly = 1) {
            ops.put(request, ticket.id)
        }
    }

    @Test
    fun `should return not found when ticket do not exists in put operation`() {
        val ops = mockk<TicketOperations>()
        val ticket = fixtureTicket().copy(
            movie = fixtureString()
        )

        val request = TicketRequest(
            movie = ticket.movie,
            theater = ticket.theater,
            room = ticket.room,
            time = ticket.time,
            language = ticket.language,
            subtitles = ticket.subtitles,
            exhibition = ticket.exhibition
        )


        every { ops.put(request, ticket.id) } returns null

        val controller = TicketController(ops)

        val notFound: ResponseEntity<Ticket> = ResponseEntity.notFound().build()
        assertEquals(notFound, controller.put(ticket.id, request))

        verify(exactly = 1) {
            ops.put(request, ticket.id)
        }
    }

    @Test
    fun `should delete ticket in database`() {
        val ops = mockk<TicketOperations>()
        val id =UUID.randomUUID()

        every { ops.delete(id) } returns true

        val controller = TicketController(ops)

        val accepted: ResponseEntity<Void> = ResponseEntity<Void>(HttpStatus.ACCEPTED)
        assertEquals(accepted, controller.delete(id))

        verify(exactly = 1) {
            ops.delete(id)
        }
    }

    @Test
    fun `should return not found when ticket do not exists in delete operation`() {
        val ops = mockk<TicketOperations>()
        val id =UUID.randomUUID()

        every { ops.delete(id) } returns false

        val controller = TicketController(ops)

        val notFound: ResponseEntity<Ticket> = ResponseEntity.notFound().build()
        assertEquals(notFound, controller.delete(id))

        verify(exactly = 1) {
            ops.delete(id)
        }
    }
}