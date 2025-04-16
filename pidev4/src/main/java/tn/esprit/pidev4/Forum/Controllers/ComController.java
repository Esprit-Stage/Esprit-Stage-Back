package tn.esprit.pidev4.Forum.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4.Forum.Class.Commentaire;
import tn.esprit.pidev4.Forum.Services.ComService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commentaires")
@CrossOrigin(origins="http://localhost:4200") // Permet les requêtes depuis d'autres domaines

public class ComController {

    private final ComService commentaireService;

    // ✅ Constructeur pour injection de dépendances
    public ComController(ComService commentaireService) {
        this.commentaireService = commentaireService;
    }

    // ➕ Ajouter un commentaire
    @PostMapping("/post/{postId}")
    public ResponseEntity<Commentaire> addCommentToPost(@PathVariable String postId, @RequestBody Commentaire commentaire) {
        Commentaire savedComment = commentaireService.createCommentForPost(postId, commentaire);
        return ResponseEntity.ok(savedComment);
    }

    // 📌 Récupérer tous les commentaires
    @GetMapping("/list")
    public ResponseEntity<List<Commentaire>> getAllComments() {
        return ResponseEntity.ok(commentaireService.getAllComments());
    }

    // 📌 Récupérer un commentaire par ID
    // GET /api/comments/post/{postId}
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Commentaire>> getCommentsByPost(@PathVariable String postId) {
        return ResponseEntity.ok(commentaireService.getCommentsByPostId(postId));
    }


    // ✏️ Mettre à jour un commentaire
    @PutMapping("/update/{commentId}")
    public Commentaire updateComment(@PathVariable String commentId, @RequestBody Commentaire updatedComment) {
        return commentaireService.updateComment(commentId, updatedComment);
    }


    // 🗑️ Supprimer un commentaire
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String commentId) {
        commentaireService.deleteComment(commentId);
        return ResponseEntity.noContent().build();    }

}
