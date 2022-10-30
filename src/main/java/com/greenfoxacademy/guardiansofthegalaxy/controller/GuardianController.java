package com.greenfoxacademy.guardiansofthegalaxy.controller;

import com.greenfoxacademy.guardiansofthegalaxy.model.Arrow;
import com.greenfoxacademy.guardiansofthegalaxy.model.Error;
import com.greenfoxacademy.guardiansofthegalaxy.model.Groot;
import com.greenfoxacademy.guardiansofthegalaxy.model.RocketShip;
import com.greenfoxacademy.guardiansofthegalaxy.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GuardianController {
    private final RestService restService;

    @Autowired
    public GuardianController(RestService restService) {
        this.restService = restService;
    }

    @GetMapping("/groot")
    public ResponseEntity grootTranslate(@RequestParam(required = false) String message) {
        if (message == null) return ResponseEntity.status(400).body(new Error("I am Groot!"));
        return ResponseEntity.status(200).body(new Groot(message));
    }

    @GetMapping("/yondu")
    public ResponseEntity calculateArrowSpeed(@RequestParam(required = false) Double distance, @RequestParam(required = false) Double time) {
        if (distance == null && time == null) return ResponseEntity.status(400).body(new Error("Please provide distance and time!"));
        if (distance == null) return ResponseEntity.status(400).body(new Error("Please provide distance!"));
        if (time == null) return ResponseEntity.status(400).body(new Error("Please provide time!"));
        return ResponseEntity.status(200).body(new Arrow(distance, time));
    }

    @GetMapping("/rocket")
    public ResponseEntity getCargo() {
        return ResponseEntity.status(200).body(new RocketShip());
    }

    @GetMapping("/rocket/fill")
    public ResponseEntity fillCargo(@RequestParam(required = false) String caliber, @RequestParam(required = false) Integer amount) {
        if (caliber == null && amount == null) return ResponseEntity.status(400).body(new Error("Please provide caliber and amount!"));
        if (caliber == null) return ResponseEntity.status(400).body(new Error("Please provide caliber!"));
        if (amount == null) return ResponseEntity.status(400).body(new Error("Please provide amount!"));
        return ResponseEntity.status(200).body(this.restService.getRocketShipDTO(caliber, amount));
    }

    @GetMapping("/drax")
    public ResponseEntity getFoods() {
        return ResponseEntity.status(200).body(this.restService.getFood());
    }

    @PostMapping("/drax/add")
    public ResponseEntity getFoods(@RequestParam String name, @RequestParam Integer amount, @RequestParam Integer calorie) {
        this.restService.addFood(name, amount, calorie);
        return ResponseEntity.status(200).body(this.restService.getFood());
    }

    @PostMapping("/drax/remove/{id}")
    public ResponseEntity removeFood(@PathVariable Long id) {
        this.restService.removeFood(id);
        return ResponseEntity.status(200).body(this.restService.getFood());
    }

    @PostMapping("/drax/change/{id}")
    public ResponseEntity changeAmount(@PathVariable Long id, @RequestParam Integer amount) {
        this.restService.changeAmount(id, amount);
        return ResponseEntity.status(200).body(this.restService.getFood());
    }

    @GetMapping("/awesome")
    public ResponseEntity getSongs(@RequestParam(required = false) String author, @RequestParam(required = false) String genre, @RequestParam(required = false) Integer year) {
        if (author != null) return ResponseEntity.status(200).body(this.restService.getSongsByAuthor(author));
        if (genre != null) return ResponseEntity.status(200).body(this.restService.getSongsByGenre(genre));
        if (year != null) return ResponseEntity.status(200).body(this.restService.getSongsByYear(year));
        return ResponseEntity.status(200).body(this.restService.getSongs());
    }

    @PostMapping("/awesome/add")
    public ResponseEntity addSong(@RequestParam String author, @RequestParam String title, @RequestParam String genre, @RequestParam Integer year, @RequestParam Double rating) {
        this.restService.addSong(author, title, genre, year, rating);
        return ResponseEntity.status(200).body(this.restService.getSongs());
    }

    @PostMapping("/awesome/remove/{id}")
    public ResponseEntity removeSong(@PathVariable Long id) {
        this.restService.removeSong(id);
        return ResponseEntity.status(200).body(this.restService.getSongs());
    }

    @PostMapping("/awesome/change/{id}")
    public ResponseEntity changeAmount(@PathVariable Long id, @RequestParam Double rating) {
        this.restService.changeRating(id, rating);
        return ResponseEntity.status(200).body(this.restService.getSongs());
    }

    @GetMapping("/awesome/favorite")
    public ResponseEntity getFavoriteSongs(@RequestParam Integer top) {
        return ResponseEntity.status(200).body(this.restService.getFavoriteSongs(top));
    }
}