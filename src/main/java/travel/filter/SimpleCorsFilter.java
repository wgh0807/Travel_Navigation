package travel.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//异域访问
@Component
public class SimpleCorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;

        response1.setHeader("Access-Control-Allow-Origin", request1.getHeader("Origin"));
        response1.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response1.setHeader("Access-Control-Max-Age", "3600");
        response1.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response1.setHeader("Access-Control-Allow-Credentials","true");
        response1.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response1.setHeader("XDomainRequestAllowed","1");
        response.setContentType("charset=UTF-8");

        chain.doFilter(request, response);


    }

    @Override
    public void destroy() {

    }
}
