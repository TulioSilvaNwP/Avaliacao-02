package com.backend.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
