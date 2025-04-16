package tn.esprit.pidev4.Forum.Class;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.pidev4.User.Class.Enseignant;
import tn.esprit.pidev4.User.Class.Etudiant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "posts")
public class Post {

    @Id
    private String id;

    private String titre;             // Le titre du post
    private String content;           // Le contenu du post
    private String imageUrl;          // L'URL de l'image (si applicable)
    private String link;              // Lien externe (si applicable)
    private LocalDateTime timestamp;  // Date et heure de publication

    @DBRef
    private Etudiant etudiant;        // Étudiant auteur

    @DBRef
    private Enseignant enseignant;    // Enseignant auteur
    private Boolean isApproved;       // Pas de sérialisation personnalisée ici

    private int rating;               // Note du post

    private int likeCount = 0;        // Nombre de likes
    private int dislikeCount = 0;     // Nombre de dislikes

    @DBRef
    private List<Commentaire> commentaires = new ArrayList<>(); // Liste des commentaires liés au post

    public Post(String id, String titre, String content, String imageUrl, String link, LocalDateTime timestamp, Etudiant etudiant, Enseignant enseignant, Boolean isApproved, int rating, int likeCount, int dislikeCount, List<Commentaire> commentaires) {
        this.id = id;
        this.titre = titre;
        this.content = content;
        this.imageUrl = imageUrl;
        this.link = link;
        this.timestamp = timestamp;
        this.etudiant = etudiant;
        this.enseignant = enseignant;
        this.isApproved = isApproved;
        this.rating = rating;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.commentaires = commentaires;
    }

    // Constructeur par défaut
    public Post() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
}
