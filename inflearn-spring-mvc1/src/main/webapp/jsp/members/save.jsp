<%@ page import="hello.servlet.web.domain.member.Member" %>
<%@ page import="hello.servlet.web.domain.member.MemberRepository" %>
<%
    //request, response 사용 가능
    //내부적으로 자동으로 servlet으로 변환된다.
    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("MemberSaveServlet");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
