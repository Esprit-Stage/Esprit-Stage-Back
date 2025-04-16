package tn.esprit.pidev4.Forum.Services;

import org.springframework.stereotype.Service;
import tn.esprit.pidev4.Forum.Class.Commentaire;
import tn.esprit.pidev4.Forum.Class.Post;
import tn.esprit.pidev4.Forum.Repository.ComRepository;
import tn.esprit.pidev4.Forum.Repository.PostRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComService {

    private final ComRepository commentaireRepository;
    private final PostRepository postRepository;


    // ✅ Constructeur pour injection de dépendances
    public ComService(ComRepository commentaireRepository,PostRepository postRepository) {
        this.commentaireRepository = commentaireRepository;
        this.postRepository = postRepository;

    }

    // ➕ Ajouter un commentaire
    public Commentaire createCommentForPost(String postId, Commentaire commentaire) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            throw new RuntimeException("Post non trouvé avec ID : " + postId);
        }

        commentaire.setPost(postOptional.get());
        commentaire.setTimestamp(LocalDateTime.now());
        return commentaireRepository.save(commentaire);
    }
    public List<Commentaire> getAllComments() {
        return commentaireRepository.findAll();
    }

    // 📌 Récupérer un commentaire par ID
    public List<Commentaire> getCommentsByPostId(String postId) {
        return commentaireRepository.findByPostId(postId);
    }

    // ✏️ Mettre à jour un commentaire
    // ✏️ Mettre à jour un commentaire
    public Commentaire updateComment(String commentId, Commentaire updatedComment) {
        Commentaire commentaire = commentaireRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Commentaire non trouvé avec ID : " + commentId));

        // Vérification que le commentaire appartient bien au post (facultatif)
        if (updatedComment.getPost() != null && !updatedComment.getPost().getId().equals(commentaire.getPost().getId())) {
            throw new RuntimeException("Le commentaire n'appartient pas au post spécifié.");
        }

        commentaire.setContent(updatedComment.getContent());
        commentaire.setTimestamp(LocalDateTime.now());
        return commentaireRepository.save(commentaire);
    }



    // 🗑️ Supprimer un commentaire
    public void deleteComment(String commentId) {
        commentaireRepository.deleteById(commentId);
    }


}
