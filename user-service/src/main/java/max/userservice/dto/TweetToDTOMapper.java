package max.userservice.dto;

import max.userservice.model.Tweet;
import max.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface TweetToDTOMapper {

    Tweet tweetDTOtoTweet(TweetDTO tweetDTO);
    TweetDTO tweetToTweetDTO(Tweet tweet);
}
