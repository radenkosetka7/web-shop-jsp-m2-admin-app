 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ page import="com.example.admin_app.beans.UserBean" %>
 <%@ page import="com.example.admin_app.dto.CustomUser" %>
 <%@ page import="com.example.admin_app.dto.enums.Role" %>
 <%@ page import="com.example.admin_app.dto.enums.Status" %>
 <jsp:useBean id="userBean" type="com.example.admin_app.beans.UserBean" scope="session"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Update user</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<main role="main" class="container bg-white d-flex justify-content-center">
    <div class="card border-primary" style="width: 30rem;margin-top: 0.1rem">
        <div class="card-body">
            <div class="card-body">
                <h4 style="text-align: center;font-weight: bold";>Update user <%=userBean.getCustomUser().getUsername()%></h4>
                <form method="POST" action="?action=updateUser&id=<%=userBean.getCustomUser().getId()%>">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" value="<%=userBean.getCustomUser().getName()%>" id="name" name="name" placeholder="Enter name" required>
                    </div>
                    <div class="form-group">
                        <label for="surname">Surname</label>
                        <input type="text" class="form-control" value="<%=userBean.getCustomUser().getSurname()%>" id="surname" name="surname" placeholder="Enter surname"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" value="<%=userBean.getCustomUser().getUsername()%>" id="username" name="username"
                               placeholder="Enter username" required>
                    </div>
                    <div class="form-group">
                        <label for="city">City</label>
                        <input type="text" class="form-control" value="<%=userBean.getCustomUser().getCity()%>" id="city" name="city" placeholder="Enter city" required>
                    </div>
                    <div class="form-group">
                        <label for="mail">Email</label>
                        <input type="email" class="form-control" value="<%=userBean.getCustomUser().getMail()%>" id="mail" name="mail" placeholder="Enter email"
                               required>
                    </div>

                    <div class="form-group">
                        <label for="role">Role</label>
                        <select class="form-control" id="role" name="role" required>
                            <option value="0" <%=userBean.getCustomUser().getRole() == Role.ADMIN ? "selected" :""%> >ADMIN</option>
                            <option value="1" <%=userBean.getCustomUser().getRole() == Role.SUPPORT ? "selected" :""%> >SUPPORT</option>
                            <option value="2" <%=userBean.getCustomUser().getRole() == Role.CUSTOM_USER ? "selected" :""%> >CUSTOMER_SUPPORT</option>

                        </select>
                    </div>

                    <div class="form-group">
                        <label for="status">Status</label>
                        <select class="form-control" id="status" name="status" required>
                            <option value="0" <%= userBean.getCustomUser().getStatus() == Status.REQUESTED ? "selected" : "" %>>REQUESTED</option>
                            <option value="1" <%= userBean.getCustomUser().getStatus() == Status.ACTIVE ? "selected" : "" %>>ACTIVE</option>
                            <option value="2" <%= userBean.getCustomUser().getStatus() == Status.BLOCKED ? "selected" : "" %>>BLOCKED</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="avatar">Avatar</label>
                        <input type="file" class="form-control" value="<%=userBean.getCustomUser().getAvatar()%>" id="avatar" name="avatar"
                               data-validation-allowing="jpg,png,gif" data-validation="mime size"
                               data-validation-max-size="5M">
                    </div>
                    <button type="submit" name="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</main>
</body>
</html>
