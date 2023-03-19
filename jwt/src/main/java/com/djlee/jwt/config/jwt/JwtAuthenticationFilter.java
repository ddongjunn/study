package com.djlee.jwt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.djlee.jwt.config.auth.PrincipalDetails;
import com.djlee.jwt.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

//스프링 시큐리티에 UsernamePasswordAuthenticationFilter 가 있다.
// /login 요청해서 username, password 전송하면 (post)
// usernamePasswordAuthenticationFilter 가 동작한다.

// formLogin().disable()해서 직접 만들어서 등록해야한다.

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter : 로그인 시도");

        //1. username, password 받아서
        try {
            /*BufferedReader br = request.getReader();
            String input = null;
            while((input = br.readLine()) != null){
                System.out.println(input);
            }*/
            //System.out.println(re 3quest.getInputStream().toString());

            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(),User.class);
            System.out.println("user = " + user);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            //PrincipalDetailService의 loadUserByUsername() 함수가 실행된 후 정상이면 authentication return
            //로그인이 됨 -> DB에 있는 username과 password가 일치
            Authentication authentication = authenticationManager.authenticate(token);

            // authentication 로그인 정보가 담긴다.
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("로그인 완료됨 : " + principalDetails.getUser().getUsername()); //로그인이 정상적으로 되었다는 뜻
            //authentication 객체가 session 영역에 저장을 해야하고, 그 방법이 return 해주면 됨
            //리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 하는거다.
            //굳이 JWT 토큰을 사용하면서 세션을 만들 이유가 없음. 근데 단지 권한 처리 때문에 session 넣어 준다.
            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //2. 정상인지 로그인 시도를 해본다.
        // authenticationManager로 로그인 시도를 하면
        // PrincipalDetailsService 호출 -> loadUserByUsername()함수 실행됨.

        //3. PrincipalDetail를 세션에 담고
        //(권한관리를 위해서 필요없으면 담지 않아도 된다.)

        //4. JWT토큰을 만들어서 응답해주면 됨
    }

    //attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행된다.
    //JWT 토큰을 만들어서 request요청한 사용자에게 JWT토큰을 response해주면 됨
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication 실행됨 : 인증 완료");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject("cos토큰")
                .withExpiresAt(new Date(System.currentTimeMillis() + (600000*30)))
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username",principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512("cos"));

        response.addHeader("Authorization","Bearer " + jwtToken);
    }
}
