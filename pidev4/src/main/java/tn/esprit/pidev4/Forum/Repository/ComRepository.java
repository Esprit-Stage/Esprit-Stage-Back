package tn.esprit.pidev4.Forum.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.pidev4.Forum.Class.Commentaire;

import java.util.List;

public interface ComRepository extends MongoRepository<Commentaire, String> {
    List<Commentaire> findByPostId(String postId);

}
