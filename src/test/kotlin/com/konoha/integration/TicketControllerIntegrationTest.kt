package com.konoha.integration

import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@WebMvcTest(TicketController::class)
class TicketControllerIntegrationTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun `should find all tickets in database with mock mvc`() {

        mvc.get("/v1/ticket") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }
    }

    @Test
    fun `should find one ticket in database with mock mvc`() {

        mvc.get("/v1/ticket/c7cbc04d-a89a-4515-9dcb-8193cc0452b3") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content {
                json(
                    """
                        {
                            "id":"c7cbc04d-a89a-4515-9dcb-8193cc0452b3",
                            "movie":"Guardiões da Galaxia vol 3",
                            "theater":"Cinemark Aricanduva",
                            "room":"9",
                            "time":"21h30m"
                            ,"language":"DUB",
                            "subtitles":false,
                            "exhibition":"XD 3D"
                        }
                    """.trimIndent()
                )
            }
        }
    }
}