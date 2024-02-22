package max.userservice.service;

import max.userservice.dto.TweetDTO;
import max.userservice.dto.TweetToDTOMapper;
import max.userservice.exception.EntityNotFoundException;
import max.userservice.model.Tweet;
import max.userservice.repo.TweetRepository;
import max.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetService {
    private final TweetRepository tweetRepository;
    private final TweetToDTOMapper tweetMapper;

    @Autowired
    public TweetService(TweetRepository tweetRepository, TweetToDTOMapper tweetMapper) {
        this.tweetRepository = tweetRepository;
        this.tweetMapper = tweetMapper;
    }

    public TweetDTO createTweet(TweetDTO tweetDTO) {
        Tweet savedTweet = tweetRepository.save(tweetMapper.tweetDTOtoTweet(tweetDTO));
        return tweetMapper.tweetToTweetDTO(savedTweet);
    }

    public List<TweetDTO> getAllTweets() {
        List<Tweet> tweets = tweetRepository.findAll();
        return tweets.stream().map(tweetMapper::tweetToTweetDTO).collect(Collectors.toList());
    }

    public TweetDTO getTweet(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new EntityNotFoundException(tweetId));
        return tweetMapper.tweetToTweetDTO(tweet);
    }

    public void deleteTweet(Long tweetId) {
        tweetRepository.deleteById(tweetId);
    }
}
