package max.userservice.repo;


import max.userservice.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    // Add custom query methods if needed
}
