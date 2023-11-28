package com.crud.crudapp.response;

import java.time.LocalDateTime;

public record OrderResponse(
        Long id,
        Double amount,
        LocalDateTime time

) {


}
