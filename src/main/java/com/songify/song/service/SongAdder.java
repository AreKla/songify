package com.songify.song.service;

import com.songify.song.model.SongEntity;
import com.songify.song.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SongAdder {

    private final SongRepository songRepository;

    public SongAdder(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public SongEntity addSong(SongEntity song) {
        log.info("Adding new song: " + song);
        songRepository.saveToDatabase(song);
        return song;
    }
}