package com.konoha.dojo.datasource.jpa.ticket

import com.konoha.dojo.domain.ticket.Ticket
import jakarta.transaction.Transactional
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface TicketRepository: CrudRepository<Ticket, UUID>