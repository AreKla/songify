package com.songify.song.infrastructure.controller;

import com.songify.song.model.SongEntity;
import com.songify.song.model.SongNotFoundException;
import com.songify.song.infrastructure.controller.dto.request.CreateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.response.*;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequestMapping("/songs")
public class SongRestController {

    Map<Integer, SongEntity> database = new HashMap<>(Map.of(
            1, new SongEntity("song1", "Podsiadło"),
            2, new SongEntity("song2", "Pezet"),
            3, new SongEntity("song3", "O.S.T.R."),
            4, new SongEntity("song4", "Magik")));

    @GetMapping
    public ResponseEntity<GetAllSongsResponseDto> getAllSongs(@RequestParam(required = false) Integer limit) {
        if (limit != null) {
            Map<Integer, SongEntity> limitedMap = database.entrySet()
                                                          .stream()
                                                          .limit(limit)
                                                          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            GetAllSongsResponseDto response = new GetAllSongsResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }
        GetAllSongsResponseDto response = SongMapper.mapFromSongToGetAllSongsResponseDto(database);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSongResponseDto> getSongById(@PathVariable Integer id,
                                                          @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        if (!database.containsKey(id)) {
            throw new SongNotFoundException("Song with id: " + id + " not found");
        }
        SongEntity song = database.get(id);
        GetSongResponseDto response = SongMapper.mapFromSongToGetSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid CreateSongRequestDto request) {
        SongEntity song = SongMapper.mapFromCreateSongRequestDtoToSong(request);
        log.info("Adding new song: " + song);
        database.put(database.size() + 1, song);
        CreateSongResponseDto body = SongMapper.mapFromSongToCreateSongResponseDto(song);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Integer id) {
        if (!database.containsKey(id)) {
            throw new SongNotFoundException("Song with id: " + id + " not found");
        }
        database.remove(id);
        DeleteSongResponseDto body = SongMapper.mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping
    public ResponseEntity<DeleteSongByRequestParamResponseDto> deleteSongByIdUsingQueryParam(
            @RequestParam("id") Integer id) {
        if (!database.containsKey(id)) {
            throw new SongNotFoundException("Song with id: " + id + " not found");
        }
        database.remove(id);
        DeleteSongByRequestParamResponseDto body = SongMapper.mapFromSongToDeleteSongByRequestParamResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateSongResponseDto> update(@PathVariable Integer id,
                                                        @RequestBody @Valid UpdateSongRequestDto request) {
        if (!database.containsKey(id)) {
            throw new SongNotFoundException("Song with id: " + id + " not found");
        }
        SongEntity newSong = SongMapper.mapFromUpdateSongResponseDtoToSong(request);
        SongEntity oldSong = database.put(id, newSong);
        log.info("Updated song with id: " + id +
                 " with oldSongName: " + oldSong.name() + " to new songName: " + newSong.name() +
                 " oldArtist: " + oldSong.artist() + " to newArtist: " + newSong.artist());
        UpdateSongResponseDto body = SongMapper.mapFromSongToUpdateSongResponseDto(newSong);
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdateSong(
            @PathVariable Integer id,
            @RequestBody PartiallyUpdateSongRequestDto request) {
        if (!database.containsKey(id)) {
            throw new SongNotFoundException("Song with id: " + id + " not found");
        }
        SongEntity songFromDatabase = database.get(id);
        SongEntity updatedSong = SongMapper.mapFromPartiallyUpdateSongResponseDtoToSong(request);
        SongEntity.SongEntityBuilder builder = SongEntity.builder();
        if (updatedSong.name() != null) {
            builder.name(updatedSong.name());
        } else {
            builder.name(songFromDatabase.name());
        }
        if (updatedSong.artist() != null) {
            builder.artist(updatedSong.artist());
        } else {
            builder.artist(songFromDatabase.artist());
        }
        database.put(id, updatedSong);
        PartiallyUpdateSongResponseDto body = SongMapper.mapFromSongToPartiallyUpdateSongResponseDto(updatedSong);
        return ResponseEntity.ok(body);
    }
}