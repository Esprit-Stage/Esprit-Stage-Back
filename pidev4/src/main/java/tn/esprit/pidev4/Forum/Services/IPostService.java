package tn.esprit.pidev4.Forum.Services;

import tn.esprit.pidev4.Forum.Class.Post;

public interface IPostService  {
    Post createPost(Post post);
    Post getAllPosts (Post post);
    Post deletePost (Post post);
    Post updatePost (Post post);

}



