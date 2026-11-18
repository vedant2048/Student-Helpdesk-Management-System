package com.helpdesk.student.service;

import com.helpdesk.student.exception.ResourceNotFoundException;
import com.helpdesk.student.model.Ticket;
import com.helpdesk.student.model.TicketPriority;
import com.helpdesk.student.model.TicketStatus;
import com.helpdesk.student.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service layer containing business logic for managing student support tickets.
 */
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    // Constructor-based dependency injection
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    /**
     * Retrieves all support tickets from the database.
     */
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    /**
     * Retrieves a single support ticket by its ID.
     * Throws ResourceNotFoundException if no ticket exists with the given ID.
     */
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));
    }

    /**
     * Creates and saves a new support ticket.
     * Sets sensible defaults if optional fields are missing.
     */
    public Ticket createTicket(Ticket ticket) {
        if (ticket.getStatus() == null) {
            ticket.setStatus(TicketStatus.OPEN);
        }
        if (ticket.getPriority() == null) {
            ticket.setPriority(TicketPriority.MEDIUM);
        }
        if (ticket.getCreatedAt() == null) {
            ticket.setCreatedAt(LocalDateTime.now());
        }
        return ticketRepository.save(ticket);
    }

    /**
     * Updates an existing ticket's information.
     * Throws ResourceNotFoundException if the ticket is not found.
     */
    public Ticket updateTicket(Long id, Ticket updatedDetails) {
        Ticket existingTicket = getTicketById(id);

        if (updatedDetails.getStudentName() != null) {
            existingTicket.setStudentName(updatedDetails.getStudentName());
        }
        if (updatedDetails.getEmail() != null) {
            existingTicket.setEmail(updatedDetails.getEmail());
        }
        if (updatedDetails.getTitle() != null) {
            existingTicket.setTitle(updatedDetails.getTitle());
        }
        if (updatedDetails.getDescription() != null) {
            existingTicket.setDescription(updatedDetails.getDescription());
        }
        if (updatedDetails.getCategory() != null) {
            existingTicket.setCategory(updatedDetails.getCategory());
        }
        if (updatedDetails.getPriority() != null) {
            existingTicket.setPriority(updatedDetails.getPriority());
        }
        if (updatedDetails.getStatus() != null) {
            existingTicket.setStatus(updatedDetails.getStatus());
        }

        return ticketRepository.save(existingTicket);
    }

    /**
     * Updates only the status of an existing ticket.
     * Throws ResourceNotFoundException if the ticket is not found.
     */
    public Ticket updateTicketStatus(Long id, TicketStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        Ticket existingTicket = getTicketById(id);
        existingTicket.setStatus(newStatus);
        return ticketRepository.save(existingTicket);
    }

    /**
     * Deletes a ticket by its ID.
     * Throws ResourceNotFoundException if the ticket does not exist.
     */
    public void deleteTicket(Long id) {
        Ticket ticket = getTicketById(id);
        ticketRepository.delete(ticket);
    }
}

