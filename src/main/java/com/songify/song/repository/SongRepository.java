package com.songify.song.repository;

import com.songify.song.model.SongEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SongRepository {

    Map<Integer, SongEntity> dataBase = new HashMap<>(
            Map.of(1, new SongEntity("song1", "Podsiadło"), 2, new SongEntity("song2", "Pezet"), 3,
                   new SongEntity("song3", "O.S.T.R."), 4, new SongEntity("song4", "Magik")));

    public Map<Integer, SongEntity> findAll() {
        return dataBase;
    }

    public SongEntity saveToDatabase(SongEntity song) {
        dataBase.put(dataBase.size() + 1, song);
        return song;
    }

    public SongEntity findSongById(Integer id) {
        return dataBase.get(id);
    }

    public SongEntity removeSong(Integer id) {
        return dataBase.remove(id);
    }

    public SongEntity updateSong(Integer id, SongEntity newSong) {
        return dataBase.put(id, newSong);
    }
}