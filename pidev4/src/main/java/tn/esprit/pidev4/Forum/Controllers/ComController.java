package tn.esprit.pidev4.Forum.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4.Forum.Class.Commentaire;
import tn.esprit.pidev4.Forum.Services.ComService;

import java.util.*;

@RestController
@RequestMapping("/commentaires")
@CrossOrigin(origins = "http://localhost:4200") // Permet les requêtes depuis Angular
public class ComController {

    private final ComService commentaireService;

    public ComController(ComService commentaireService) {
        this.commentaireService = commentaireService;
    }

    // 🔞 Liste des gros mots à filtrer
    private static final Set<String> BAD_WORDS = new HashSet<>(Arrays.asList(
            "abruti", "andouille", "arnaqueur", "batard", "bouffon", "casse-couilles",
            "connard", "con", "conne", "crétin", "débile", "emmerdeur", "enculé",
            "foutre", "garce", "gros con", "idiot", "imbécile", "merde", "naze",
            "ordure", "pédé", "pute", "putain", "salaud", "salope", "tafiole",
            "trouduc", "va te faire", "branleur", "chiant", "chiotte", "niquer"
    ));

    // ⚙️ Fonction pour remplacer les gros mots par ***
    private String filterBadWords(String content) {
        for (String badWord : BAD_WORDS) {
            String regex = "(?i)\\b" + badWord + "\\b"; // Insensible à la casse, mot entier
            content = content.replaceAll(regex, "***");
        }
        return content;
    }

    // ➕ Ajouter un commentaire
    @PostMapping("/post/{postId}")
    public ResponseEntity<Commentaire> addCommentToPost(@PathVariable String postId, @RequestBody Commentaire commentaire) {
        commentaire.setContent(filterBadWords(commentaire.getContent()));
        Commentaire savedComment = commentaireService.createCommentForPost(postId, commentaire);
        return ResponseEntity.ok(savedComment);
    }

    // 📌 Récupérer tous les commentaires
    @GetMapping("/list")
    public ResponseEntity<List<Commentaire>> getAllComments() {
        return ResponseEntity.ok(commentaireService.getAllComments());
    }

    // 📌 Récupérer les commentaires d’un post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Commentaire>> getCommentsByPost(@PathVariable String postId) {
        return ResponseEntity.ok(commentaireService.getCommentsByPostId(postId));
    }

    // ✏️ Mettre à jour un commentaire
    @PutMapping("/update/{commentId}")
    public ResponseEntity<Commentaire> updateComment(@PathVariable String commentId, @RequestBody Commentaire updatedComment) {
        updatedComment.setContent(filterBadWords(updatedComment.getContent()));
        Commentaire updated = commentaireService.updateComment(commentId, updatedComment);
        return ResponseEntity.ok(updated);
    }

    // 🗑️ Supprimer un commentaire
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String commentId) {
        commentaireService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
