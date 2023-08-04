<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+Z2lKjyRV7zF7Xjz7DjB1BmkLqbo5P9Q1roMgPMbbPu34Df" crossorigin="anonymous">
    <link rel="stylesheet" href="styles/login.css">

    <script>
        function hideNotification() {
            var notificationElement = document.getElementById('notificationMessage');
            if (notificationElement.textContent.trim() !== '') {
                notificationElement.style.display = 'none';
            }
        }

        setTimeout(hideNotification, 2000);
    </script>


</head>
<body>

<div class="login-box">
    <h2>Login Page</h2>
    <form method="post" action="?action=login">
        <div class="user-box">
            <input type="text" id="username" name="username" required>
            <label>Username</label>
        </div>
        <div class="user-box">
            <input type="password" id="password" name="password" required>
            <label>Password</label>
        </div>
        <button class="btn" name="submit" type="submit">Sign in</button>
        <h5 id="notificationMessage" class="h5"><%=session.getAttribute("notification").toString()%>
        </h5>
    </form>
</div>
</body>
</html>