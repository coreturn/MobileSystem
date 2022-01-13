package com.example.momentweb.servlet;

import com.example.momentweb.dao.Dao;
import com.example.momentweb.entity.Comment;
import com.example.momentweb.response.CommentResponseBody;
import com.example.momentweb.response.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/comment/get")
public class GetComments extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String url = req.getParameter("url");
        System.out.println("[GetComments] url : " + url);

        Dao dao = new Dao();
        ObjectMapper mapper = new ObjectMapper();
        CommentResponseBody body = new CommentResponseBody();
        ArrayList<String> res = dao.selectComment(url);
        System.out.println("comment list: " + res);
        if ( res != null ) {
            body.setCode(200);
            body.setComments(res);
        } else {
            body.setCode(404);
            body.setComments(null);
        }
        mapper.writeValue(resp.getWriter(), body);
    }

}
