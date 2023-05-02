package com.konoha

import com.konoha.domain.ticket.Ticket
import java.util.*
import kotlin.random.Random

fun fixtureTickets(): MutableIterable<Ticket> {
    val list = mutableListOf<Ticket>()

    repeat(Random.nextInt(10)) {
        list.add(fixtureTicket())
    }

    return list
}

fun fixtureTicket(): Ticket = Ticket(
    UUID.randomUUID(),
    fixtureString(),
    fixtureString(),
    fixtureString(),
    fixtureString(),
    fixtureString(),
    Random.nextBoolean(),
    fixtureString()
)

fun fixtureString(range: Int = Random.nextInt(10)): String {
    val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return (1..range)
        .map { charset.random() }
        .joinToString("")
}