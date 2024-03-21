package com.songify;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
public class SongsController {

    Map<Integer, String> database = new HashMap<>();

    @GetMapping("/songs")
    public ResponseEntity<SongResponseDto> getAllSongs() {
        database.put(1, "Podsiadło song1");
        database.put(2, "Pezet song2");
        SongResponseDto response = new SongResponseDto(database);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<SingleSongResponseDto> getSongById(@PathVariable Integer id) {
        String song = database.get(id);
        if (song == null) {
            return ResponseEntity.notFound().build();
        }
        SingleSongResponseDto response = new SingleSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/songs?id=")
    public ResponseEntity<SongResponseDto> getSongByParam(@RequestParam Integer limit) {
        database.put(1, "Podsiadło song1");
        database.put(2, "Pezet song2");
        database.put(3, "O.S.T.R. song3");
        database.put(4, "Magik song4");
        if (limit != null) {
            Map<Integer, String> limitedMap = database.entrySet().stream().limit(limit).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            SongResponseDto response = new SongResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }
        SongResponseDto response = new SongResponseDto(database);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/songsHeaders/{headerId}")
    public ResponseEntity<SingleSongResponseDto> getByHeader(@PathVariable Integer headerId, @RequestHeader(
            name = "Request-Id",
            required = false) String requestId) {
        log.info(requestId);
        String song = database.get(headerId);
        if (song == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        SingleSongResponseDto response = new SingleSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

}