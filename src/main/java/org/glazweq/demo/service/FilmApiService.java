package org.glazweq.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.glazweq.demo.domain.MovieCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class FilmApiService {
    private static final  String url = "https://api.kinopoisk.dev/v1.4/movie";
    private static final String header1 = "H1CX9MG-T83MTC4-PPV7CQE-SYP1474";
    private static final String header2 = "application/json";

    @Autowired
    private RestTemplate restTemplate;
    public String getMoviesByRequest(String request){
        try {
            //header value is set
            HttpHeaders headers = new HttpHeaders();

            headers.set("X-API-KEY", header1);
            headers.set("accept", header2);
            //make GET  call

            ResponseEntity<String> response = restTemplate.exchange(url + request, HttpMethod.GET,new HttpEntity<>(headers),String.class);

            log.info("Output from KinoApi:{}", response.getBody());

            return response.getBody();

        }catch (Exception e){
            log.error("something went wrong while getting value from KINOAPI", e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling end poing from KinoApi",
                    e
            );
        }
    }

   /* curl --request GET \
            --url 'https://api.kinopoisk.dev/v1.4/movie?page=1&limit=10&selectFields=id&selectFields=name&selectFields=year&selectFields=rating&selectFields=poster&notNullFields=name&notNullFields=poster.url&sortField=rating.kp&sortType=-1' \
            --header 'X-API-KEY: H1CX9MG-T83MTC4-PPV7CQE-SYP1474' \
            --header 'accept: application/json'


    */

   /* public MovieCard getTenMovies(String request) throws JsonProcessingException {
        String jsonSource = getMoviesByRequest(request);
        JsonNode node = JsonDecoderService.parse(jsonSource);
        String name = node.get("name").asText();
        String type = node.get("type").asText();
        String previewImg = node.get("poster").get("previewUrl").asText();
        return new MovieCard(name, type, previewImg);
    }

    */


    public List<MovieCard> getTenMoviesList(int page) throws JsonProcessingException {
        String urlSecondPart = "?page="+ page +"&limit=24&selectFields=id&selectFields=enName&selectFields=year&selectFields=rating&selectFields=poster&notNullFields=enName&notNullFields=poster.url&sortField=rating.kp&sortType=-1";
        String answerFromApi = getMoviesByRequest(urlSecondPart);

        JsonNode rootNode = JsonDecoderService.parse(answerFromApi);
        JsonNode docsNode = rootNode.get("docs"); // Получаем узел "docs"

        List<MovieCard> moviesCards = new ArrayList<>();

        if (docsNode != null && docsNode.isArray()) {
            for (JsonNode movieNode : docsNode) {
                String enName = movieNode.get("enName").asText();
                String previewImg = movieNode.get("poster").get("previewUrl").asText();

                moviesCards.add(new MovieCard(enName, previewImg));
                System.out.println(enName + "--------------");
            }
        }

        return moviesCards;
    }


}
