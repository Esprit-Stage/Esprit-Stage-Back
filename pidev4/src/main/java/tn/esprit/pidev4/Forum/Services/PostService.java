package tn.esprit.pidev4.Forum.Services;

import org.springframework.stereotype.Service;
import tn.esprit.pidev4.Forum.Class.Post;
import tn.esprit.pidev4.Forum.Repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service  // ✅ Ajout de l'annotation @Service
public class PostService {
    private final PostRepository postRepository;

    // ✅ Ajout de @Autowired (ou utiliser explicitement dans le constructeur)
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // ➕ Ajouter un post
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // 📌 Récupérer tous les posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getAllPostsacepted() {
        return postRepository.findByIsApprovedTrue();
    }
    public List<Post> getAllPostsrefused() {
        return postRepository.findByIsApprovedFalse();
    }
    public List<Post> getAllPostspending() {
        return postRepository.findByIsApprovedNull();
    }

    //
    // 📌 Récupérer un post par ID
    public Optional<Post> getPostById(String id) {
        return postRepository.findById(id);
    }

    //✏️ Mettre à jour un post
    public Post updatePost(String id, Post postDetails) {
        return postRepository.findById(id).map(post -> {
            post.setTitre(postDetails.getTitre());
            post.setContent(postDetails.getContent());  // ✅ Vérifie si 'content' existe
            post.setImageUrl(postDetails.getImageUrl());  // ✅ Vérifie si 'imageUrl' existe
            post.setLink(postDetails.getLink());  // ✅ Vérifie si 'link' existe
            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post non trouvé"));
    }




    public Post acceptpost(String id) {
        return postRepository.findById(id).map(post -> {
            post.setApproved(true);

            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post non trouvé"));
    }
    public Post refusepost(String id) {
        return postRepository.findById(id).map(post -> {
            post.setApproved(false);

            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post non trouvé"));
    }

    //  🗑️ Supprimer un post
    public void deletePost(String id) {
        postRepository.deleteById(id);
    }

    //Ratting
    public Post updateRating(String postId, int rating) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post updatedPost = post.get();
            updatedPost.setRating(rating);
            return postRepository.save(updatedPost);
        }
        return null;
    }
    // 👍 Incrémenter les likes
    public Post likePost(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post non trouvé"));
        post.setLikeCount(post.getLikeCount() + 1);
        return postRepository.save(post);
    }

    // 👎 Incrémenter les dislikes
    public Post dislikePost(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post non trouvé"));
        post.setDislikeCount(post.getDislikeCount() + 1);
        return postRepository.save(post);
    }
}