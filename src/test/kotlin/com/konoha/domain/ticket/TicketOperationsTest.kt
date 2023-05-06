package com.konoha.domain.ticket

import com.konoha.datasource.jpa.ticket.TicketRepository
import com.konoha.fixtureTicket
import com.konoha.fixtureTickets
import com.konoha.input.resources.req.TicketRequest
import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class TicketOperationsTest {
    @Test
    fun `should find all tickets in database`() {
        val repository = mockk<TicketRepository>()
        val tickets = fixtureTickets()

        every { repository.findAll() } returns tickets

        val ops = TicketOperations(repository)

        assertEquals(tickets, ops.findAll())

        verify(exactly = 1) {
            repository.findAll()
        }
    }

    @Test
    fun `should find one tickets in database`() {
        val repository = mockk<TicketRepository>()
        val tickets = fixtureTicket()

        every { repository.findById(tickets.id) } returns Optional.of(tickets)

        val ops = TicketOperations(repository)

        assertEquals(Optional.of(tickets), ops.findById(tickets.id))

        verify(exactly = 1) {
            repository.findById(tickets.id)
        }
    }

    @Test
    fun `should return null when ticket do not exists in get by id`() {
        val repository = mockk<TicketRepository>()
        val tickets = fixtureTicket()

        every { repository.findById(tickets.id) } returns Optional.empty()

        val ops = TicketOperations(repository)

        val optional: Optional<Ticket> = Optional.empty()
        assertEquals(optional, ops.findById(tickets.id))

        verify(exactly = 1) {
            repository.findById(tickets.id)
        }
    }

    @Test
    fun `should save ticket in database`() {
        val repository = mockk<TicketRepository>()
        val id = UUID.randomUUID()
        val ops = TicketOperations(repository)
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

        mockkStatic(UUID::randomUUID) {
            every { UUID.randomUUID() } returns id

            val entity = request.toEntity()

            every { repository.save(entity) } returns ticket

            assertEquals(ticket, ops.save(request))

            verify(exactly = 1) {
                repository.save(entity)
            }
        }
    }

    @Test
    fun `should put ticket in database`() {
        val repository = mockk<TicketRepository>()
        val ops = TicketOperations(repository)
        val ticket = fixtureTicket()
        val id = ticket.id

        val request = TicketRequest(
            movie = ticket.movie,
            theater = ticket.theater,
            room = ticket.room,
            time = ticket.time,
            language = ticket.language,
            subtitles = ticket.subtitles,
            exhibition = ticket.exhibition
        )

        every { repository.findById(id) } returns Optional.of(ticket)

        every { repository.save(ticket) } returns ticket

        assertEquals(ticket, ops.put(request, id))

        verify(exactly = 1) {
            repository.save(ticket)
        }

    }

    @Test
    fun `should return null when ticket do not exists in put`() {
        val repository = mockk<TicketRepository>()
        val ops = TicketOperations(repository)
        val ticket = fixtureTicket()
        val id = ticket.id

        val request = TicketRequest(
            movie = ticket.movie,
            theater = ticket.theater,
            room = ticket.room,
            time = ticket.time,
            language = ticket.language,
            subtitles = ticket.subtitles,
            exhibition = ticket.exhibition
        )

        every { repository.findById(id) } returns Optional.empty()

        assertNull(ops.put(request, id))

        verify(exactly = 0) {
            repository.save(ticket)
        }

    }

    @Test
    fun `should delete ticket in database`() {
        val repository = mockk<TicketRepository>()
        val ops = TicketOperations(repository)
        val ticket = fixtureTicket()
        val id = ticket.id

        every { repository.findById(id) } returns Optional.of(ticket)

        justRun { repository.delete(ticket) }

        assertTrue(ops.delete(id))

        verify(exactly = 1) {
            repository.delete(ticket)
        }
    }

    @Test
    fun `should return false when ticket do not exists in delete`() {
        val repository = mockk<TicketRepository>()
        val ops = TicketOperations(repository)
        val ticket = fixtureTicket()
        val id = ticket.id

        every { repository.findById(id) } returns Optional.empty()

        justRun { repository.delete(ticket) }

        assertFalse(ops.delete(id))

        verify(exactly = 0) {
            repository.delete(ticket)
        }
    }
}