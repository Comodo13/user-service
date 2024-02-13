package max.userservice.controller;


import lombok.AllArgsConstructor;
import max.userservice.dto.TweetDTO;
import max.userservice.dto.TweetToDTOMapper;
import max.userservice.model.Tweet;
import max.userservice.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tweet/")
public class TweetsController {


    TweetToDTOMapper tweetMapper;


    TweetService tweetService;

    @Autowired
    public TweetsController(TweetToDTOMapper tweetMapper, TweetService tweetService) {
        this.tweetMapper = tweetMapper;
        this.tweetService = tweetService;
    }

    @PostMapping("")
    public TweetDTO createTweet(@RequestBody TweetDTO tweetDTO) {
        return tweetMapper.tweetToTweetDTO(tweetService.createTweet(tweetMapper.tweetDTOtoTweet(tweetDTO)));
    }

    @GetMapping("/tweets")
    public Iterable<Tweet> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @GetMapping("{tweetId}")
    public Tweet getAllTweets(@PathVariable Long tweetId) {
        return tweetService.getTweet(tweetId);
    }

    @DeleteMapping("/tweets/{tweetId}")
    public void deleteTweet(@PathVariable Long tweetId) {
        tweetService.deleteTweet(tweetId);
    }


//    @GetMapping("/tweets/{tweetId}")
//    public String getTweet(@PathVariable Long tweetId) {
//        return twitterService.getTweet(tweetId);
//    }
}
