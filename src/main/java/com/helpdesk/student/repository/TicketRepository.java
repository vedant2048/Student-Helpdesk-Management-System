package com.helpdesk.student.repository;

import com.helpdesk.student.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA Repository for Ticket entity.
 * Provides standard CRUD operations: save, findById, findAll, deleteById, etc.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}

