package com.songify.song.infrastructure.controller.dto.response;

import com.songify.song.model.SongEntity;

import java.util.Map;

public record GetAllSongsResponseDto(Map<Integer, SongEntity> song) {
}