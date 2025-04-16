package tn.esprit.pidev4.User.Class;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentAdministratif extends User {
    private String service;

    @DBRef
    private List<User> managedUsers;
}
