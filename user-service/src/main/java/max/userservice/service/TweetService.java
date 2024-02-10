package max.userservice.service;

import max.userservice.repo.TweetRepository;
import max.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetService {


    private final TweetRepository tweetRepository;

    @Autowired
    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }
}
