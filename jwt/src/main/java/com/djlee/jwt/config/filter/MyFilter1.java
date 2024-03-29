package com.djlee.jwt.config.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class MyFilter1 implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //토큰 : cos 이걸 만들어줘야 함.
        // 언제????  id,pw정상적으로 들어와서 로그인이 완료 되면 토큰을 만들어주고 그걸 응답해준다.
        //요청할 때 마다 header에 Authorization에 value값으로 토큰을 가지고 온다.
        //그때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지만 검증만 하면 됨.(RSA, HS256)
        res.setCharacterEncoding("UTF-8");
        if(req.getMethod().equals("POST")){
            String headerAuth = req.getHeader("Authorization");
            log.info("header Authorization={}",headerAuth);

            /*if(headerAuth.equals("cos")){
                filterChain.doFilter(request, response);
            }else{
                PrintWriter out = res.getWriter();
                out.println("인증안됨");
            }*/
        }
        filterChain.doFilter(request, response);
    }
}
