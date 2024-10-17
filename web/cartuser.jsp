<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cara</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
              integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/css/styleTT.css">
    </head>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        thead th {
            padding: 10px;
            text-align: center;
            border-top: 2px solid #000;
            border-bottom: 2px solid #000; /* Đường viền dày cho phần tiêu đề */
        }

        thead tr {
            border-bottom: 2px solid #000; /* Đường viền dày dưới cùng của thead */
        }

        tbody td {
            padding: 10px;
            text-align: center;
        }

        tr:hover {
            background-color: #f5f5f5; /* Tô màu khi di chuột qua hàng */
        }

    </style>
    <body>

        <%@ include file="./inc/header.jsp" %>
        <section id="page-header" class="about-header">
            <h2>#let's_talk</h2>
            <p>LEAVE A MESSAGE, We love to hear from you!</p>
        </section>

        <section id="cart" class="section-p1">
            <h4 class="text-center">List of your orders</h4>
            <table width="100%">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Code</th>
                        <th>Created_at</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty orderList}">
                            <c:forEach items="${orderList}" var="order">
                                <tr>
                                    <td>${order.id}</td>
                                    <td>${order.code}</td>
                                    <td><fmt:formatDate value="${order.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                    <td>${order.status}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" class="text-center">Không có đơn hàng nào.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </section>


        <%@ include file="./inc/footer.jsp" %>

    </body>

</html>
