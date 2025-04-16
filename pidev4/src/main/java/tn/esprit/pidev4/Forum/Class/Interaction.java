package tn.esprit.pidev4.Forum.Class;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import tn.esprit.pidev4.User.Class.Enseignant;
import tn.esprit.pidev4.User.Class.Etudiant;

public class Interaction {
    @Id
    private String id;
    private Interaction interactionType; // Le type de l'interaction (like, dislike, haha, angry)

    @DBRef
    private Enseignant enseignant; // L'utilisateur qui a réagi
    private Etudiant etudiant; // L'utilisateur qui a réagi

    @DBRef
    private Post post; // La publication sur laquelle l'utilisateur a réagi// La publication sur laquelle l'utilisateur a réagi
}
