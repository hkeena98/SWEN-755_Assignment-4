<!DOCTYPE html>
<html>
<head>
    <meta charset="US-ASCII">
    <title>Login Page</title>
    <script>
        function closeDown() {
            alert('closing browser');
            <%
                session.invalidate();
            %>
            document.location = "/servlet/MyServlet?useraction=logout";
        }

        function alertInactiveSession() {
            <%
                Cookie[] cookies = request.getCookies();
                boolean sessionExisted = false;
                boolean loggedOut = false;
                if(cookies != null) {
                    for(Cookie cookie: cookies) {
                        if(cookie.getName().equals("session")){
                            sessionExisted = true;
                        }
                        if(cookie.getName().equals("logOut")){
                            loggedOut = true;
                        }
                    }
                }
                if(request.getSession(false) == null && sessionExisted) {%>
            alert("Session has expired!");
            console.log(document.cookie)
            document.cookie = "session=; expires=Thu, 01 Jan 1970 00:00:00 UTC"
            <%

            }%>
        }

    </script>
</head>
<body onUnload="closeDown()" onload="alertInactiveSession()">
<form action="DemoServlet" method="post">

    Username: <input type="text" name="username">
    <br>
    Password: <input type="password" name="password">
    <br>
    <input type="submit" value="Login">
</form>
</body>
</html>