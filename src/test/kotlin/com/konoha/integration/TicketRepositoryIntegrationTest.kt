package com.konoha.integration

import com.konoha.datasource.jpa.ticket.TicketRepository
import com.konoha.domain.ticket.TicketOperations
import com.konoha.fixtureTicket
import com.konoha.input.resources.req.TicketRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TicketRepositoryIntegrationTest {

    @Autowired
    private lateinit var repository: TicketRepository

    companion object {
        @Container
        private val postgresContainer = PostgreSQLContainer<Nothing>("postgres").apply {
            withDatabaseName("dojodbIntegration")
            withUsername("mrmorales")
            withPassword("pass")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgresContainer::getJdbcUrl)
            registry.add("spring.datasource.username", postgresContainer::getUsername)
            registry.add("spring.datasource.password", postgresContainer::getPassword)
        }
    }

    @Test
    fun `should save a ticket in database`(){
        val operations = TicketOperations(repository)

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

        val actual = operations.save(request)

        assertEquals(ticket.copy(id = actual.id), actual)
    }

}