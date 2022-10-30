package com.greenfoxacademy.guardiansofthegalaxy.service;

import com.greenfoxacademy.guardiansofthegalaxy.model.Food;
import com.greenfoxacademy.guardiansofthegalaxy.model.RocketShip;
import com.greenfoxacademy.guardiansofthegalaxy.DTO.RocketShipDTO;
import com.greenfoxacademy.guardiansofthegalaxy.model.Song;
import com.greenfoxacademy.guardiansofthegalaxy.repository.FoodRepository;
import com.greenfoxacademy.guardiansofthegalaxy.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class RestService {
    private final FoodRepository foodRepository;
    private final SongRepository songRepository;

    @Autowired
    public RestService(FoodRepository foodRepository, SongRepository songRepository) {
        this.foodRepository = foodRepository;
        this.songRepository = songRepository;
    }

    public RocketShipDTO getRocketShipDTO(String caliber, Integer amount) {
        RocketShipDTO rocketShipDTO = new RocketShipDTO();
        RocketShip rocketShip = this.getRocketShip(caliber, amount);
        rocketShipDTO.setReceived(caliber);
        rocketShipDTO.setAmount(amount);
        rocketShipDTO.setShipstatus(rocketShip.getShipstatus());
        rocketShipDTO.setReady(rocketShip.getReady());
        return rocketShipDTO;
    }

    private RocketShip getRocketShip(String caliber, Integer amount) {
        if (caliber.equals(".25")) return new RocketShip(amount, 0, 0);
        if (caliber.equals(".30")) return new RocketShip(0, amount, 0);
        return new RocketShip(0, 0, amount);
    }

    public List<Food> getFood() {
        return this.foodRepository.findAll();
    }

    public void addFood(String name, Integer amount, Integer calorie) {
        this.foodRepository.save(new Food(name, amount, calorie));
    }

    public void removeFood(Long id) {
        this.foodRepository.deleteById(id);
    }

    public void changeAmount(Long id, Integer amount) {
        Food food = this.foodRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        food.setAmount(amount);
        this.foodRepository.save(food);
    }

    public List<Song> getSongs() {
        return this.songRepository.findAll();
    }

    public List<Song> getSongsByAuthor(String author) {
        return this.songRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Song> getSongsByGenre(String genre) {
        return this.songRepository.findByGenreContainingIgnoreCase(genre);
    }

    public List<Song> getSongsByYear(Integer year) {
        return this.songRepository.findByYear(year);
    }

    public void addSong(String author, String title, String genre, Integer year, Double rating) {
        this.songRepository.save(new Song(author, title, genre, year, rating));
    }

    public void removeSong(Long id) {
        this.songRepository.deleteById(id);
    }

    public void changeRating(Long id, Double rating) {
        Song song = this.songRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        song.setRating(rating);
        this.songRepository.save(song);
    }

    public List<Song> getFavoriteSongs(Integer top) {
        List<Song> songs = this.songRepository.findByOrderByRatingDesc();
        return songs.stream().limit(top).toList();
    }
}