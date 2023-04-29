package com.konoha.dojo.input.resources.req

data class TicketRequest(
    val movie: String,
    val theater: String,
    val room:String,
    val time: String?,
    val language: String?,
    val subtitles: Boolean?,
    val exhibition: String?
)