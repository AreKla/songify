package com.songify.song.domain.service;

import com.songify.song.domain.model.Song;
import com.songify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
public class SongRetriever {
    private final SongRepository songRepository;

    public SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Map<Integer, Song> findAll() {
        log.info("retrieving all songs");
        return songRepository.findAll();
    }

    public Map<Integer, Song> findAllLimitedBy(Integer limit) {
        return songRepository.findAll()
                             .entrySet()
                             .stream()
                             .limit(limit)
                             .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

//    public Song getSongById(Integer id) {
//        log.info("Finded song on id: " + id);
//        return songRepository.findSongById(id);
//    }
//
//    public Song addSong(Song song) {
//        log.info("adding new song: " + song);
//        songRepository.saveToDatabase(song);
//        return song;
//    }
//
//    public Song removeSong(Integer id) {
//        log.info("Removing song on id: " + id);
//        return songRepository.removeSong(id);
//    }
//
//    public Song updateSong(Integer id, Song newSong) {
//        log.info("Updating song on id: " + id);
//        return songRepository.updateSong(id, newSong);
//    }
}