package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;

// Stub
public class PostRepository {

  long count = 5;

  List<Post> user = new ArrayList<>();

  Post user1 = new Post(1, "Hi");
  Post user2 = new Post(2, "Hello");
  Post user3 = new Post(3, "Hay");
  Post user4 = new Post(4, "He");
  Post user5 = new Post(5, "Ho");

  public List<Post> all() {
    user.add(user1);
    user.add(user2);
    user.add(user3);
    user.add(user4);
    user.add(user5);
    return user;
  }

  public long getById(long id) {
    return id;
  }

  public Post save(Post post) {
    if(post.getId() == 0) {
      post.setId(count);
    } else if(post.getId() != 0) {
      for (Post posts : user) {
        posts.getId();
        post.setContent("");
      }
    }
    return post;
  }

  public void removeById(long id) {
  }
}
