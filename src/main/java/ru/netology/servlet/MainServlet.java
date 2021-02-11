package ru.netology.servlet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.controller.PostController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    private PostController controller;

    @Override
    public void init() {
        // отдаём класс конфигурации
        final var context = new AnnotationConfigApplicationContext("ru.netology");

        controller = context.getBean(PostController.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        final String path = request.getRequestURI();
        final String method = request.getMethod();
        try {
            if (method.equals("GET") && path.equals("/api/posts")) {
                controller.all(response);
                return;
            }
            if (method.equals("GET") && path.matches("/api/posts/\\d+")) {
                // easy way
                final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
                controller.getById(id, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String path = request.getRequestURI();
        final String method = request.getMethod();
        if (method.equals("POST") && path.equals("/api/posts")) {
            controller.save(request.getReader(), response);
            return;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        final String path = request.getRequestURI();
        final String method = request.getMethod();
        if (method.equals("DELETE") && path.matches("/api/posts/\\d+")) {
            final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
            controller.removeById(id, response);
            return;
        }
    }
}