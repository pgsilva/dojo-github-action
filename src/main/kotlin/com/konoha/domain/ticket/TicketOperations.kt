package com.konoha.domain.ticket

import com.konoha.datasource.jpa.ticket.TicketRepository
import com.konoha.input.resources.req.TicketRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*


@Service
class TicketOperations(val repository: TicketRepository) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun findAll(): MutableIterable<Ticket> {
        log.info("getting data ...")
        return repository.findAll()
    }

    fun findById(id: UUID): Optional<Ticket> {
        log.info("getting data ...")
        return repository.findById(id)

    }

    fun save(request: TicketRequest): Ticket {
        log.info("saving data ...")
        return repository.save(request.toEntity())
    }

    fun put(request: TicketRequest, id: UUID): Ticket? {
        log.info("saving data ...")

        return repository.findById(id).map { currentTask ->
            val updatedTask: Ticket =
                currentTask
                    .copy(
                        movie = request.movie,
                        theater = request.theater,
                        time = request.time,
                        language = request.language,
                        subtitles = request.subtitles,
                        exhibition = request.exhibition
                    )
            updatedTask
        }.orElse(
            null
        )
    }

    fun delete(id: UUID): Boolean {
        log.info("deleting data ...")

        return repository.findById(id).map { task ->
            repository.delete(task)
            true
        }.orElse(
            false
        )
    }

}

fun TicketRequest.toEntity(): Ticket =
    Ticket(
        UUID.randomUUID(),
        this.movie,
        this.theater,
        this.room,
        this.time,
        this.language,
        this.subtitles,
        this.exhibition
    )
