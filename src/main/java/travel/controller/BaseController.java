package travel.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class BaseController {
    HttpServletRequest request;
    HttpServletResponse response;
    HttpSession session;
    ServletContext application;

    @ModelAttribute
    private void set(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        this.application = request.getServletContext();
//        this.response.setCharacterEncoding("UTF-8");

    }

}
