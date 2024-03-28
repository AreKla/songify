package com.songify.song.domain.repository;

import com.songify.song.domain.model.Song;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SongRepository {

    Map<Integer, Song> dataBase = new HashMap<>(
            Map.of(1, new Song("song1", "Podsiadło"),
                   2, new Song("song2", "Pezet"),
                   3, new Song("song3", "O.S.T.R."),
                   4, new Song("song4", "Magik")));

    public Song saveToDatabase(Song song) {
        dataBase.put(dataBase.size() + 1, song);
        return song;
    }

    public Map<Integer, Song> findAll() {
        return dataBase;
    }

//    public Song findSongById(Integer id) {
//        return dataBase.get(id);
//    }
//
//    public Song removeSong(Integer id) {
//        return dataBase.remove(id);
//    }
//
//    public Song updateSong(Integer id, Song newSong) {
//        return dataBase.put(id, newSong);
//    }
}