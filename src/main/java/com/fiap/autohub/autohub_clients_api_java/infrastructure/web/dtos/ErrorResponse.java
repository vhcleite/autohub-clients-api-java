package com.fiap.autohub.autohub_clients_api_java.infrastructure.web.dtos; // Ajuste o pacote

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ErrorResponse {
    private OffsetDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;


    public ErrorResponse(int status, String error, String message, String path) {
        this.timestamp = OffsetDateTime.now(ZoneOffset.UTC);
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}