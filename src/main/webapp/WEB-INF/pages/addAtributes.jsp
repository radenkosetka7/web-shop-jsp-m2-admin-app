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
    <link rel="stylesheet" href="styles/category.css">

    <script>
        $(document).ready(function () {
            $('.add-one').click(function () {
                $('.dynamic-element').first().clone().appendTo('.dynamic-stuff').show();
                attach_delete();
            });

            function attach_delete() {
                $('.delete').off();
                $('.delete').click(function () {
                    console.log("click");
                    $(this).closest('.form-group').remove();
                });
            }
        });
    </script>
</head>
<body>
<%@include file="header.jsp" %>

<div class="form-group dynamic-element" style="display:none">
    <div class="row d-flex align-items-center">
        <div class="col-md-5">
            <label for="attributeName">Attribute name</label>
            <input type="text" class="form-control" id="attributeName" name="attributeName[]" required>
        </div>
        <div class="col-md-5">
            <label for="attributeType">Attribute type</label>
            <select class="form-control" id="attributeType" name="attributeType[]" required>
                <option value="0">STRING</option>
                <option value="1">INT</option>
                <option value="2">DOUBLE</option>
            </select>
        </div>

        <div class="col-md-2">
            <p style="margin-top: 40px;width: 30px;height: 30px" class="delete">x</p>
        </div>
    </div>
</div>

<main role="main" class="container bg-white d-flex justify-content-center">
    <div class="card border-primary" style="width: 30rem;margin-top: 0.1rem">
        <div class="card-body">
            <div class="card-body">
                <h4 style="text-align: center;font-weight: bold";>Add attributes for <%=categoryBean.getCategory().getName()%></h4>
                <form method="POST" action="?action=addAtributes&id=<%=categoryBean.getCategory().getId()%>">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" disabled value="<%=categoryBean.getCategory().getName()%>" id="name" name="name" placeholder="Enter name" required>
                    </div>
                    <div class="dynamic-stuff">
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-12">
                                <p class="add-one">+ Add attributes</p>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label style="font-size: 22px;font-weight: bold">Attributes</label>
                    </div>

                    <% for (Attribute attribute : categoryBean.getCategory().getAttributes()) { %>
                    <div class="form-group"  style="display: inline-block;">
                        <label for="attrName">Name</label>
                        <input type="text" class="form-control" disabled value="<%=attribute.getName()%>" id="attrName" name="attrName" required>
                    </div>
                    <div class="form-group"  style="display: inline-block;">
                        <label for="type">Type</label>
                        <select disabled value="<%=attribute.getType()%>" class="form-control" id="type" name="type" required>
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
