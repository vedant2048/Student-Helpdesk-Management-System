package com.helpdesk.student.dto;

import com.helpdesk.student.model.TicketStatus;

/**
 * Request payload DTO for updating only the ticket status.
 */
public class StatusUpdateRequest {

    private TicketStatus status;

    public StatusUpdateRequest() {
    }

    public StatusUpdateRequest(TicketStatus status) {
        this.status = status;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}

