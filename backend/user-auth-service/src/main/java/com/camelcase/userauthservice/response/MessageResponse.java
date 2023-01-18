package com.camelcase.userauthservice.response;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class MessageResponse {
	@NonNull
    private String message;
    private final LocalDateTime apiTime = LocalDateTime.now();

}
