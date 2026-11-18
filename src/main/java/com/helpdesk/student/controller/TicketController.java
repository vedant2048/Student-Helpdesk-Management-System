package com.helpdesk.student.controller;

import com.helpdesk.student.dto.StatusUpdateRequest;
import com.helpdesk.student.model.Ticket;
import com.helpdesk.student.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller exposing API endpoints for student support ticket management.
 */
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    // Constructor-based dependency injection
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * 1. View all tickets
     * GET /api/tickets
     */
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    /**
     * 2. View a ticket using its ID
     * GET /api/tickets/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        return ResponseEntity.ok(ticket);
    }

    /**
     * 3. Create a new support ticket
     * POST /api/tickets
     */
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket createdTicket = ticketService.createTicket(ticket);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    /**
     * 4. Update ticket information
     * PUT /api/tickets/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket updatedTicket) {
        Ticket ticket = ticketService.updateTicket(id, updatedTicket);
        return ResponseEntity.ok(ticket);
    }

    /**
     * 5. Change ticket status
     * PATCH /api/tickets/{id}/status
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Ticket> updateTicketStatus(
            @PathVariable Long id,
            @RequestBody StatusUpdateRequest request) {
        Ticket updatedTicket = ticketService.updateTicketStatus(id, request.getStatus());
        return ResponseEntity.ok(updatedTicket);
    }

    /**
     * 6. Delete a ticket
     * DELETE /api/tickets/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Ticket deleted successfully with id: " + id);
        return ResponseEntity.ok(response);
    }
}

