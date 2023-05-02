package com.konoha.domain.ticket

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "ticket")
data class Ticket (
    @Id
    val id: UUID,
    val movie: String,
    val theater: String,
    val room: String,
    val time: String? = null,
    val language: String? = null,
    val subtitles: Boolean? = null,
    val exhibition: String? = null
)
