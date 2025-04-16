package tn.esprit.pidev4.Forum.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4.Forum.Class.Post;
import tn.esprit.pidev4.Forum.Services.PostService;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins="http://localhost:4200") // Permet les requêtes depuis d'autres domaines
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // ➕ Ajouter un post
    @PostMapping("/add")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
            post.setApproved(null);
        return ResponseEntity.ok(postService.createPost(post));
    }

    // 📌 Récupérer tous les posts
    @GetMapping("/list")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
    //
    @GetMapping("/list/accepted")
    public ResponseEntity<List<Post>> getAllPostsaccepted() {
        return ResponseEntity.ok(postService.getAllPostsacepted());
    }

    @GetMapping("/list/refused")
    public ResponseEntity<List<Post>> getAllPostsrefused() {
        return ResponseEntity.ok(postService.getAllPostsrefused());
    }

    @GetMapping("/list/pending")
    public ResponseEntity<List<Post>> getAllPostspending() {
        return ResponseEntity.ok(postService.getAllPostspending());
    }

    @PutMapping("/refuse/{id}")
    public ResponseEntity<Post> refuse(@PathVariable String id) {
        return ResponseEntity.ok(postService.refusepost(id));
    }

    @PutMapping("/accepte/{id}")
    public ResponseEntity<Post> accepte(@PathVariable String id) {
        return ResponseEntity.ok(postService.acceptpost(id));
    }


    //
    // 📌 Récupérer un post par ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) {
        Optional<Post> post = postService.getPostById(id);
        return post.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✏️ Mettre à jour un post
    @PutMapping("/update/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePost(id, post));
    }
//
    // 🗑️ Supprimer un post
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{postId}/rating")
    public ResponseEntity<Post> updateRating(@PathVariable String postId, @RequestParam int rating) {
        Post updatedPost = postService.updateRating(postId, rating);
        if (updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    // 👍 Liker un post
    @PutMapping("/{id}/like")
    public Post likePost(@PathVariable String id) {
        return postService.likePost(id);
    }

    // 👎 Disliker un post
    @PutMapping("/{id}/dislike")
    public Post dislikePost(@PathVariable String id) {
        return postService.dislikePost(id);
    }


}
