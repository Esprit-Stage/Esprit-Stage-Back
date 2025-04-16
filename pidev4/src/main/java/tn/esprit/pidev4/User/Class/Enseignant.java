package tn.esprit.pidev4.User.Class;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
public class Enseignant extends User {
    private String departement;

    @DBRef
    private List<Etudiant> students;
}
