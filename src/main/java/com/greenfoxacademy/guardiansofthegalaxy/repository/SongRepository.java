package com.greenfoxacademy.guardiansofthegalaxy.repository;

import com.greenfoxacademy.guardiansofthegalaxy.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByAuthorContainingIgnoreCase(@Param("author") String author);
    List<Song> findByGenreContainingIgnoreCase(@Param("genre") String genre);
    List<Song> findByYear(@Param("year") Integer year);
    List<Song> findByOrderByRatingDesc();
}
