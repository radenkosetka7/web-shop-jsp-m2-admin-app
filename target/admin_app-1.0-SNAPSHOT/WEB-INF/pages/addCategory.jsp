
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<main role="main" class="container bg-white d-flex justify-content-center">
    <div class="card border-primary" style="width: 30rem;margin-top: 0.1rem">
        <div class="card-body">
            <div class="card-body">
                <h4 style="text-align: center;font-weight: bold";>Add new category</h4>
                <form method="POST" action="?action=addUser">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="Enter name" required>
                    </div>
                </form>
            </div>

        </div>
    </div>
</main>
</body>
</html>
