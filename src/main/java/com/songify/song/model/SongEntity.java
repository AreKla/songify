package com.songify.song.model;

import lombok.Builder;

@Builder
public record SongEntity(String name, String artist) {
}