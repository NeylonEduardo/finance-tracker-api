package com.ney.financetrackerapi.exception;

import java.time.LocalDateTime;

public record CustomMessageError(
        int status,
        LocalDateTime timeStamp,
        String message,
        String details
) {
}
