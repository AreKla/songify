package com.songify.song.infrastructure.controller;

import com.songify.song.model.SongEntity;
import com.songify.song.infrastructure.controller.dto.request.CreateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.response.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class SongMapper {

    public static GetAllSongsResponseDto mapFromSongToGetAllSongsResponseDto(Map<Integer, SongEntity> database) {
        return new GetAllSongsResponseDto(database);
    }

    public static GetSongResponseDto mapFromSongToGetSongResponseDto(SongEntity song) {
        return new GetSongResponseDto(song);
    }

    public static SongEntity mapFromCreateSongRequestDtoToSong(CreateSongRequestDto dto) {
        return new SongEntity(dto.songName(), dto.artist());
    }

    public static CreateSongResponseDto mapFromSongToCreateSongResponseDto(SongEntity song) {
        return new CreateSongResponseDto(song);
    }

    public static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Integer id) {
        return new DeleteSongResponseDto("You deleted song with id: " + id, HttpStatus.OK);
    }

    public static DeleteSongByRequestParamResponseDto mapFromSongToDeleteSongByRequestParamResponseDto(Integer id) {
        return new DeleteSongByRequestParamResponseDto("You deleted song with id: " + id, HttpStatus.OK);
    }

    public static SongEntity mapFromUpdateSongResponseDtoToSong(UpdateSongRequestDto dto) {
        return new SongEntity(dto.songName(), dto.artist());
    }

    public static UpdateSongResponseDto mapFromSongToUpdateSongResponseDto(SongEntity newSong) {
        return new UpdateSongResponseDto(newSong.name(), newSong.artist());
    }

    public static SongEntity mapFromPartiallyUpdateSongResponseDtoToSong(PartiallyUpdateSongRequestDto dto) {
        return new SongEntity(dto.songName(), dto.artist());
    }

    public static PartiallyUpdateSongResponseDto mapFromSongToPartiallyUpdateSongResponseDto(SongEntity updatedSong) {
        return new PartiallyUpdateSongResponseDto(updatedSong);
    }
}