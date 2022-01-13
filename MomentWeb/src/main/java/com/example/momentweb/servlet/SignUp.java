package com.example.momentweb.servlet;


import com.example.momentweb.dao.Dao;
import com.example.momentweb.entity.User;
import com.example.momentweb.response.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign/up")
public class SignUp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Dao dao = new Dao();
        User user = new User(name, password);
        ResponseBody body = new ResponseBody();
        ObjectMapper mapper = new ObjectMapper();
        if (dao.insert(user)) {
            body.setCode(200);
            body.setData("success");
        } else {
            body.setCode(500);
            body.setData("failed");
        }
        mapper.writeValue(resp.getWriter(), body);
    }
}