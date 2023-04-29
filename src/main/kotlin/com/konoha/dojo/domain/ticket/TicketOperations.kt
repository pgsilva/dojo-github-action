package com.konoha.dojo.domain.ticket

import com.konoha.dojo.datasource.jpa.ticket.TicketRepository
import com.konoha.dojo.input.resources.req.TicketRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*


@Service
class TicketOperations(val repository: TicketRepository) {

    private val log = LoggerFactory.getLogger(this.javaClass)
    fun findAll(): MutableIterable<Ticket> = repository.findAll()

    fun put(request: TicketRequest): UUID {
        log.info("saving data ...")

        val saved = repository.save(request.toEntity())

        return saved.id
    }

}

fun TicketRequest.toEntity(): Ticket =
    Ticket(
        UUID.randomUUID(),
        this.movie,
        this.theater,
        this.time,
        this.language,
        this.subtitles,
        this.exhibition
    )
