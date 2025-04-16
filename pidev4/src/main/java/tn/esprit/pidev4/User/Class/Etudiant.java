package tn.esprit.pidev4.User.Class;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class Etudiant extends User {
    private String specialite;
    private String niveau;

    @DBRef
    private Enseignant enseignant;

}
