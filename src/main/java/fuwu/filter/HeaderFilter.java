package fuwu.filter;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LJW on 2018/5/13 - 14:03
 */
public class HeaderFilter extends CharacterEncodingFilter {

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,DELETE,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        super.doFilterInternal(request, response, filterChain);
    }
}
