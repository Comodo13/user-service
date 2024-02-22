package max.userservice.controller;


import lombok.AllArgsConstructor;
import max.userservice.dto.TweetDTO;
import max.userservice.dto.TweetToDTOMapper;
import max.userservice.model.Tweet;
import max.userservice.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/tweets")
public class TweetsController {

    private final TweetService tweetService;

    @Autowired
    public TweetsController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping("")
    public ResponseEntity<TweetDTO> createTweet(@RequestBody TweetDTO tweetDTO) {
        TweetDTO createdTweet = tweetService.createTweet(tweetDTO);
        return new ResponseEntity<>(createdTweet, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<TweetDTO>> getAllTweets() {
        List<TweetDTO> tweetDTOs = tweetService.getAllTweets();
        return new ResponseEntity<>(tweetDTOs, HttpStatus.OK);
    }

    @GetMapping("{tweetId}")
    public ResponseEntity<TweetDTO> getTweet(@PathVariable Long tweetId) {
        TweetDTO tweetDTO = tweetService.getTweet(tweetId);
        return new ResponseEntity<>(tweetDTO, HttpStatus.OK);
    }

    @DeleteMapping("{tweetId}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long tweetId) {
        tweetService.deleteTweet(tweetId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}