package tn.esprit.pidev4.Forum.Class;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.pidev4.User.Class.Enseignant;
import tn.esprit.pidev4.User.Class.Etudiant;

import java.time.LocalDateTime;

@Document(collection = "commentaires")
@Data
public class Commentaire {
    @Id
    private String id;

    private String content; // Le contenu du commentaire
    private LocalDateTime timestamp; // Date et heure du commentaire

    @DBRef
    private Enseignant enseignant; // L'utilisateur qui a écrit le commentaire
    private Etudiant etudiant;

    @DBRef
    private Post post; // La publication à laquelle le commentaire est associé

    // ✅ Constructeur
    public Commentaire(String content, LocalDateTime timestamp, Enseignant enseignant, Etudiant etudiant, Post post) {
        this.content = content;
        this.timestamp = timestamp;
        this.enseignant = enseignant;
        this.etudiant = etudiant;
        this.post = post;
    }

    // ✅ Getter et Setter pour content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // ✅ Getter et Setter pour timestamp
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Getter et Setter pour post
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    // Getter et Setter pour enseignant
    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    // Getter et Setter pour etudiant
    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
