<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.admin_app.beans.CategoryBean" %>
<%@ page import="com.example.admin_app.dto.Category" %>
<%@ page import="com.example.admin_app.dto.Attribute" %>
<%@ page import="com.example.admin_app.dto.enums.Type" %>
<jsp:useBean id="categoryBean" type="com.example.admin_app.beans.CategoryBean" scope="session"/>
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
                <h4 style="text-align: center;font-weight: bold";>Update category <%=categoryBean.getCategory().getName()%></h4>
                <form method="POST" action="?action=updateCategory&id=<%=categoryBean.getCategory().getId()%>">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" value="<%=categoryBean.getCategory().getName()%>" id="name" name="name" placeholder="Enter name" required>
                    </div>
                    <div class="form-group">
                        <label style="font-size: 22px;font-weight: bold">Attributes</label>
                    </div>
                    <% for (Attribute attribute : categoryBean.getCategory().getAttributes()) { %>
                    <div class="form-group"  style="display: inline-block;">
                        <label for="city">Name</label>
                        <input type="text" class="form-control" value="<%=attribute.getName()%>" id="city" name="city" placeholder="Enter city" required>
                    </div>
                    <div class="form-group"  style="display: inline-block;">
                        <label for="type">Type</label>
                        <select class="form-control" id="type" name="type" required>
                         <option value="0" <%= attribute.getType() == Type.STRING ? "selected" : "" %>>STRING</option>
                       <option value="1" <%= attribute.getType() == Type.INT ? "selected" : "" %>>INT</option>
                        <option value="2" <%= attribute.getType() == Type.DOUBLE ? "selected" : "" %>>DOUBLE</option>
                        </select>
                      </div>
                    <% } %>

                    <button type="submit" name="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</main>
</body>
</html>
