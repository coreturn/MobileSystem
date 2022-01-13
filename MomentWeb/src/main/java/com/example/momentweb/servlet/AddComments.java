package com.example.momentweb.servlet;

import com.example.momentweb.dao.Dao;
import com.example.momentweb.entity.Comment;
import com.example.momentweb.response.CommentResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/comment/add")
public class AddComments extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String url = req.getParameter("url");
        String comment = req.getParameter("comment");
        System.out.printf("[AddComments] url : %s, comment %s \n", url, comment);

        Dao dao = new Dao();
        Comment c = new Comment(url, comment);
        ObjectMapper mapper = new ObjectMapper();
        CommentResponseBody body = new CommentResponseBody();

        if (dao.insertComment(c)) {
            body.setCode(200);
        } else {
            body.setCode(404);
        }
        body.setComments(null);
        mapper.writeValue(resp.getWriter(), body);
    }

}
