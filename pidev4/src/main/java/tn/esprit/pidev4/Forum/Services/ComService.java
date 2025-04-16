package tn.esprit.pidev4.Forum.Services;

import org.springframework.stereotype.Service;
import tn.esprit.pidev4.Forum.Class.Commentaire;
import tn.esprit.pidev4.Forum.Class.Post;
import tn.esprit.pidev4.Forum.Repository.ComRepository;
import tn.esprit.pidev4.Forum.Repository.PostRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ComService {

    private final ComRepository commentaireRepository;
    private final PostRepository postRepository;

    // ✅ Liste des gros mots (à compléter selon tes besoins)
    private static final Set<String> BAD_WORDS = new HashSet<>(Arrays.asList(
            "abruti", "andouille", "arnaqueur", "batard", "bouffon", "casse-couilles",
            "connard", "con", "conne", "crétin", "débile", "emmerdeur", "enculé",
            "foutre", "garce", "gros con", "idiot", "imbécile", "merde", "naze",
            "ordure", "pédé", "pute", "putain", "salaud", "salope", "tafiole",
            "trouduc", "va te faire", "branleur", "chiant", "chiotte", "niquer"
    ));

    // ✅ Constructeur
    public ComService(ComRepository commentaireRepository, PostRepository postRepository) {
        this.commentaireRepository = commentaireRepository;
        this.postRepository = postRepository;
    }

    // 🔐 Méthode pour filtrer les gros mots
    private String filtrerBadWords(String input) {
        if (input == null) return null;

        String[] mots = input.split(" ");
        StringBuilder resultat = new StringBuilder();

        for (String mot : mots) {
            String motClean = mot.toLowerCase().replaceAll("[^a-zA-Z]", "");
            if (BAD_WORDS.contains(motClean)) {
                resultat.append("*".repeat(mot.length())).append(" ");
            } else {
                resultat.append(mot).append(" ");
            }
        }

        return resultat.toString().trim();
    }

    // ➕ Ajouter un commentaire à un post
    public Commentaire createCommentForPost(String postId, Commentaire commentaire) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            throw new RuntimeException("Post non trouvé avec ID : " + postId);
        }

        commentaire.setPost(postOptional.get());
        commentaire.setTimestamp(LocalDateTime.now());
        commentaire.setContent(filtrerBadWords(commentaire.getContent()));
        return commentaireRepository.save(commentaire);
    }

    // 🔍 Récupérer tous les commentaires
    public List<Commentaire> getAllComments() {
        return commentaireRepository.findAll();
    }

    // 📌 Récupérer les commentaires d’un post spécifique
    public List<Commentaire> getCommentsByPostId(String postId) {
        return commentaireRepository.findByPostId(postId);
    }

    // ✏️ Mettre à jour un commentaire
    public Commentaire updateComment(String commentId, Commentaire updatedComment) {
        Commentaire commentaire = commentaireRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Commentaire non trouvé avec ID : " + commentId));

        if (updatedComment.getPost() != null &&
                !updatedComment.getPost().getId().equals(commentaire.getPost().getId())) {
            throw new RuntimeException("Le commentaire n'appartient pas au post spécifié.");
        }

        commentaire.setContent(filtrerBadWords(updatedComment.getContent()));
        commentaire.setTimestamp(LocalDateTime.now());
        return commentaireRepository.save(commentaire);
    }

    // 🗑️ Supprimer un commentaire
    public void deleteComment(String commentId) {
        commentaireRepository.deleteById(commentId);
    }
}
