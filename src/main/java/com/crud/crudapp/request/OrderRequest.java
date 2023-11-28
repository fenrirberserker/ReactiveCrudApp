package com.crud.crudapp.request;

import java.time.LocalDateTime;

public record OrderRequest(
        Double amount,
        LocalDateTime date
) {
}
