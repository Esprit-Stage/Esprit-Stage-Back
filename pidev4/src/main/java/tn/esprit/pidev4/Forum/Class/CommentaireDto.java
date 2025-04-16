package tn.esprit.pidev4.Forum.Class;

public class CommentaireDto {

    private String content; // Contenu du commentaire
    private String postId;  // L'ID du post auquel le commentaire est associé

    // Constructeur
    public CommentaireDto(String content, String postId) {
        this.content = content;
        this.postId = postId;
    }

    // Getters et Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
