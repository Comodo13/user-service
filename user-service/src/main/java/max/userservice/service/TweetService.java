package max.userservice.service;

import max.userservice.model.Tweet;
import max.userservice.repo.TweetRepository;
import max.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TweetService {


    private final TweetRepository tweetRepository;

    @Autowired
    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }


    public Tweet createTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    public Tweet getTweet(Long tweetId) {
        return tweetRepository.findById(tweetId).orElse(null);
    }

    public void deleteTweet(Long tweetId) {
        tweetRepository.deleteById(tweetId);
    }

}
