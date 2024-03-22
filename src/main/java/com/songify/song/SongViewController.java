package com.songify.song;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
public class SongViewController {

    private Map<Integer, String> database = new HashMap<>();

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/view/songs")
    public String songs(Model model) {
        database.put(1, "Podsiadło song1");
        database.put(2, "Pezet song2");
        database.put(3, "O.S.T.R. song3");
        database.put(4, "Magik song4");
        model.addAttribute("songMap", database);
        return "songs";
    }
}
