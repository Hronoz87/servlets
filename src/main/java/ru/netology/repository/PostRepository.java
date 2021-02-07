package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Stub
public class PostRepository {

    AtomicInteger count;

    ConcurrentHashMap<Long, Post> user = new ConcurrentHashMap<Long, Post>();
    List<Post> listPost = new ArrayList<>();

    Post user1 = new Post(1, "Hi");
    Post user2 = new Post(2, "Hello");
    Post user3 = new Post(3, "Hay");
    Post user4 = new Post(4, "He");
    Post user5 = new Post(5, "Ho");

    public List<Post> all() {
        listPost.add(1, user1);
        listPost.add(2, user2);
        listPost.add(3, user3);
        listPost.add(4, user4);
        listPost.add(5, user5);
        return listPost;
    }

    public Optional<Post> getById(long id) {
        Post postCurrent = null;
        for (Post post : listPost) {
            if (post.getId() == id) {
                postCurrent = post;
            } else {
                System.out.println("Нет такого ID!");
            }
        }
        return Optional.ofNullable(postCurrent);
    }


    public Post save(Post post) {
        if (post.getId() == 0) {
            Integer newId = count.getAndAdd(1);
            post.setId(newId);
        }
        user.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
    }
}
