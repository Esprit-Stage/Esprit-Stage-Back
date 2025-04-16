package tn.esprit.pidev4.Forum.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tn.esprit.pidev4.Forum.Class.Post;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {


    List<Post> findByIsApprovedTrue();
    List<Post> findByIsApprovedFalse();
    List<Post> findByIsApprovedNull();



}
