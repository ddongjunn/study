package hello.servlet.web.servlet;

import hello.servlet.web.domain.member.Member;
import hello.servlet.web.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter pw = resp.getWriter();
        pw.write("<html>");
        pw.write("<head>");
        pw.write("    <meta charset=\"UTF-8\">");
        pw.write("    <title>Title</title>");
        pw.write("</head>");
        pw.write("<body>");
        pw.write("<a href=\"/index.html\">메인</a>");
        pw.write("<table>");
        pw.write("    <thead>");
        pw.write("    <th>id</th>");
        pw.write("    <th>username</th>");
        pw.write("    <th>age</th>");
        pw.write("    </thead>");
        pw.write("    <tbody>");

        for (Member member : members) {
            pw.write("    <tr>");
            pw.write("        <td>" + member.getId() + "</td>");
            pw.write("        <td>" + member.getUsername() + "</td>");
            pw.write("        <td>" + member.getAge() + "</td>");
            pw.write("    </tr>");
        }

        pw.write("    </tbody>");
        pw.write("</table>");
        pw.write("</body>");
        pw.write("</html>");

    }
}
