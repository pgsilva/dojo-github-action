package com.konoha.datasource.jpa.ticket

import com.konoha.domain.ticket.Ticket
import jakarta.transaction.Transactional
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface TicketRepository: CrudRepository<Ticket, UUID>