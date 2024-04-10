package org.glazweq.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Text;

import java.util.List;

@Getter
@Setter
@Entity
public class MovieCard {

    @JsonProperty("name")
    private String name;

    @JsonProperty("previewUrl")
    private String previewUrlImg;
    @JsonProperty("kinopoiskRating")
    private double kinopoiskRating;
    @JsonProperty("description")
    @Column(length = 15000)
    private String description ;
    @JsonProperty("imdbRating")
    private double imdbRating;
    @JsonProperty("year")
    private int year;
    @JsonProperty("genres")
    private String genres;
    @Setter
    @Id
    @JsonProperty("id")
    private int id;

    public MovieCard(String name, String previewUrlImg, double kinopoiskRating, String description, double imdbRating, int year, int id) {
        this.name = name;

        this.previewUrlImg = previewUrlImg;
        this.kinopoiskRating = kinopoiskRating;
        this.description = description;
        this.imdbRating = imdbRating;
        this.year = year;
        this.id = id;
    }

    public MovieCard(int id, String name, String previewUrlImg, double kinopoiskRating, double imdbRating, String description, int year) {
        this.id = id;
        this.name = name;
        this.previewUrlImg = previewUrlImg;
        this.kinopoiskRating = kinopoiskRating;
        this.description = description;
        this.imdbRating = imdbRating;
        this.year = year;
    }

    public MovieCard() {
    }


}
