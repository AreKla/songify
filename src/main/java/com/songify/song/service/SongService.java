
package com.songify.song.service;

import com.songify.song.repository.SongRepository;
import com.songify.song.model.SongEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Log4j2
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Map<Integer, SongEntity> getSongs() {
        log.info("retreving all songs");
        return songRepository.findAll();
    }

    public SongEntity getSongById(Integer id) {
        log.info("Finded song on id: " + id);
        return songRepository.findSongById(id);
    }

    public SongEntity addSong(SongEntity song) {
        log.info("adding new song: " + song);
        songRepository.saveToDatabase(song);
        return song;
    }

    public SongEntity removeSong(Integer id) {
        log.info("Removing song on id: " + id);
        return songRepository.removeSong(id);
    }

    public SongEntity updateSong(Integer id, SongEntity newSong) {
        log.info("Updating song on id: " + id);
        return songRepository.updateSong(id, newSong);
    }
}