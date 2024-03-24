package com.songify.song.infrastructure.controller.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteSongByRequestParamResponseDto(String message, HttpStatus status) {
}