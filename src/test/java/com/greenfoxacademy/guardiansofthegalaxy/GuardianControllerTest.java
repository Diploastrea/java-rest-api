package com.greenfoxacademy.guardiansofthegalaxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class GuardianControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void GETgrootWithInput() throws Exception {
        this.mockMvc.perform(get("/groot?message=somemessage"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.received", is("somemessage")))
                .andExpect(jsonPath("$.translated", is("I am Groot!")));
    }

    @Test
    public void GETgrootWithoutInput() throws Exception {
        this.mockMvc.perform(get("/groot"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.error", is("I am Groot!")));
    }

    @Test
    public void GETyonduWithInput() throws Exception {
        this.mockMvc.perform(get("/yondu?distance=100.0&time=10.0"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.distance", is(100.0)))
                .andExpect(jsonPath("$.time", is(10.0)))
                .andExpect(jsonPath("$.speed", is(10.0)));
    }

    @Test
    public void GETyonduWithoutInput() throws Exception {
        this.mockMvc.perform(get("/yondu"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.error", is("Please provide distance and time!")));
    }

    @Test
    public void GETrocket() throws Exception {
        this.mockMvc.perform(get("/rocket"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.caliber25", is(0)))
                .andExpect(jsonPath("$.caliber30", is(0)))
                .andExpect(jsonPath("$.caliber50", is(0)))
                .andExpect(jsonPath("$.shipstatus", is("empty")))
                .andExpect(jsonPath("$.ready", is(false)));
    }

    @Test
    public void GETrocketFillWith0Ammo() throws Exception {
        this.mockMvc.perform(get("/rocket/fill?caliber=.50&amount=0"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.received", is(".50")))
                .andExpect(jsonPath("$.amount", is(0)))
                .andExpect(jsonPath("$.shipstatus", is("empty")))
                .andExpect(jsonPath("$.ready", is(false)));
    }

    @Test
    public void GETrocketFillWith5000Ammo() throws Exception {
        this.mockMvc.perform(get("/rocket/fill?caliber=.25&amount=5000"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.received", is(".25")))
                .andExpect(jsonPath("$.amount", is(5000)))
                .andExpect(jsonPath("$.shipstatus", is("40%")))
                .andExpect(jsonPath("$.ready", is(false)));
    }

    @Test
    public void GETrocketFillWith12500Ammo() throws Exception {
        this.mockMvc.perform(get("/rocket/fill?caliber=.30&amount=12500"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.received", is(".30")))
                .andExpect(jsonPath("$.amount", is(12500)))
                .andExpect(jsonPath("$.shipstatus", is("full")))
                .andExpect(jsonPath("$.ready", is(true)));
    }

    @Test
    public void GETrocketFillWith50000Ammo() throws Exception {
        this.mockMvc.perform(get("/rocket/fill?caliber=.30&amount=50000"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.received", is(".30")))
                .andExpect(jsonPath("$.amount", is(50000)))
                .andExpect(jsonPath("$.shipstatus", is("overloaded")))
                .andExpect(jsonPath("$.ready", is(false)));
    }

    @Test
    public void GETrocketFillWithoutInput() throws Exception {
        this.mockMvc.perform(get("/rocket/fill"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.error", is("Please provide caliber and amount!")));
    }

    @Test
    public void POSTaddFood() throws Exception {
        this.mockMvc.perform(post("/drax/add?name=pizza&amount=5&calorie=2400"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.[0].name", is("pizza")))
                .andExpect(jsonPath("$.[0].amount", is(5)))
                .andExpect(jsonPath("$.[0].calorie", is(2400)));
    }

    @Test
    public void POSTremoveFood() throws Exception {
        this.mockMvc.perform(post("/drax/remove/1"))
                .andExpect(status().is(200));
    }

    @Test
    public void POSTchangeAmount() throws Exception {
        this.mockMvc.perform(post("/drax/change/1?amount=5"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.[0].amount", is(5)));
    }

    @Test
    public void POSTaddSong() throws Exception {
        this.mockMvc.perform(post("/awesome/add?author=Oasis&title=Whatever&genre=rock&year=1996&rating=5"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.[0].author", is("Oasis")))
                .andExpect(jsonPath("$.[0].title", is("Whatever")))
                .andExpect(jsonPath("$.[0].genre", is("rock")))
                .andExpect(jsonPath("$.[0].year", is(1996)))
                .andExpect(jsonPath("$.[0].rating", is(5.0)));
    }

    @Test
    public void GETsongsByAuthor() throws Exception {
        this.mockMvc.perform(get("/awesome?author=Oasis"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.[0].author", is("Oasis")))
                .andExpect(jsonPath("$.[1].author", is("Oasis")))
                .andExpect(jsonPath("$.[2].author", is("Oasis")));
    }

    @Test
    public void GETsongsByGenre() throws Exception {
        this.mockMvc.perform(get("/awesome?genre=electronic"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.[0].genre", is("electronic")))
                .andExpect(jsonPath("$.[1].genre", is("electronic")));
    }

    @Test
    public void GETsongsByYear() throws Exception {
        this.mockMvc.perform(get("/awesome?year=1996"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.[0].year", is(1996)))
                .andExpect(jsonPath("$.[1].year", is(1996)));
    }

    @Test
    public void POSTremoveSong() throws Exception {
        this.mockMvc.perform(post("/awesome/remove/1"))
                .andExpect(status().is(200));
    }

    @Test
    public void POSTchangeRating() throws Exception {
        this.mockMvc.perform(post("/awesome/change/6?rating=4.5"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.[0].rating", is(4.5)));
    }
}