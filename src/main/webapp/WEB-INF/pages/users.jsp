<%@ page import="com.example.admin_app.dto.CustomUser" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp" %>


<jsp:useBean id="userBean" class="com.example.admin_app.beans.UserBean" scope="session"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Users</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="styles/table.css">

    <script>
        $(document).ready(function() {
            $('#myTable').DataTable();
        });
    </script>

</head>
<body>
<%@include file="header.jsp"%>
<div class="container-lg">
    <div class="table-responsive">
        <div class="table-wrapper" style="width: 100%">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Users</h2>
                    </div>
                    <div class="col-sm-6">
                        <div class="search-box">
                            <button type="button" class="btn btn-success" onclick="location.href='?action=addUser'">
                                <span class="fa fa-plus"></span>
                                Add new user
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <table id="myTable" class="table table-striped">
                <thead>
                <tr>
                    <th style="width: 5%;">Id</th>
                    <th style="width: 20%;">Name</th>
                    <th style="width: 20%;">Surname</th>
                    <th style="width: 30%;">Username</th>
                    <th style="width: 25%;">E-mail</th>
                    <th style="width: 15%;">City</th>
                    <th style="width: 15%;">Status</th>
                    <th style="width: 15%;">Actions</th>
                </tr>
                </thead>
                <tbody>
                <% for(CustomUser customUser:userBean.getAllUsers()) {%>
                <tr>
                    <td><%=customUser.getId()%></td>
                    <td style="word-wrap: break-word"><%=customUser.getName()%></td>
                    <td style="word-wrap: break-word"><%=customUser.getSurname()%></td>
                    <td style="word-wrap: break-word"><%=customUser.getUsername()%></td>
                    <td style="word-wrap: break-word"><%=customUser.getMail()%></td>
                    <td style="word-wrap: break-word"><%=customUser.getCity()%></td>
                    <td><%=customUser.getStatus()%></td>
                    <td>
                        <div class="d-flex flex-row">

                            <button title="Edit" style="width: fit-content" type="button" class="btn"
                                    onclick="location.href='?action=updateUser&id=<%=customUser.getId()%>'">
                                <span style="width: fit-content" class="fa fa-pencil text-dark"></span>
                            </button>


                            <button title="Delete" style="width: fit-content" type="button" class="btn"
                                    onclick="location.href='?action=deleteUser&id=<%=customUser.getId()%>'">
                                <span style="width: fit-content" class="fa fa-trash text-danger"></span>
                            </button>
                        </div>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
