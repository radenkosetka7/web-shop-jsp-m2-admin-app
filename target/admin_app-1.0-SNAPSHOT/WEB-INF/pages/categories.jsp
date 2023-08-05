<%@ page import="com.example.admin_app.dto.Category" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp" %>


<jsp:useBean id="categoryBean" class="com.example.admin_app.beans.CategoryBean" scope="session"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Categories</title>
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

    <script>
        $(document).ready(function(){
            // Activate tooltips
            $('[data-toggle="tooltip"]').tooltip();

            // Filter table rows based on searched term
            $("#search").on("keyup", function() {
                var term = $(this).val().toLowerCase();
                $("table tbody tr").each(function(){
                    $row = $(this);
                    var name = $row.find("td:nth-child(2)").text().toLowerCase();
                    console.log(name);
                    if(name.search(term) < 0){
                        $row.hide();
                    } else{
                        $row.show();
                    }
                });
            });
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
                        <h2>Categories</h2>
                    </div>
                    <div class="col-sm-6">
                        <div class="search-box">
                            <button type="button" class="btn btn-success" onclick="location.href='?action=addCategory'">
                                <span class="fa fa-plus"></span>
                                Add new category
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <table id="myTable" class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Attributes</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <% for(Category category:categoryBean.getAllCategories()) {%>
                <tr>
                    <td><%=category.getId()%></td>
                    <td style="word-wrap: break-word"><%=category.getName()%></td>
                    <td>
                        <button class="btn btn-primary view-button" onclick="location.href='?action=viewAttributes&id=<%=category.getId()%>'" ><i class="fa fa-eye"></i>View</button>
                    </td>
                    <td>
                        <div class="d-flex flex-row">

                            <button title="Edit" style="width: fit-content" type="button" class="btn"
                                    onclick="location.href='?action=updateCategory&id=<%=category.getId()%>'">
                                <span style="width: fit-content" class="fa fa-pencil text-dark"></span>
                            </button>


                            <button title="Delete" style="width: fit-content" type="button" class="btn"
                                    onclick="location.href='?action=deleteCategory&id=<%=category.getId()%>'">
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
