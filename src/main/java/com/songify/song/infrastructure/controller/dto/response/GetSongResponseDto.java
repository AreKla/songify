package com.songify.song.infrastructure.controller.dto.response;

import com.songify.song.model.SongEntity;

public record GetSongResponseDto(SongEntity song) {
}