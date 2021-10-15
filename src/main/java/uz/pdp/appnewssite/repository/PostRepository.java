package uz.pdp.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewssite.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
