<%-- 
    Document   : header
    Created on : Sep 27, 2024, 8:37:46 PM
    Author     : tranq
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
/* CSS cho menu */
#navbar {
    list-style: none;
    padding: 0;
    margin: 0;
    display: flex;
}

/* Định dạng cho các menu item */
#navbar .menu-item {
    position: relative; /* Định vị tương đối để giúp submenu được căn chỉnh theo nó */
    margin-right: 20px; /* Khoảng cách giữa các mục */
}

/* Định dạng cho submenu */
.sub-menu {
    display: none; /* Ẩn submenu mặc định */
    position: absolute; /* Định vị tuyệt đối dựa theo menu item cha */
    top: 100%; /* Đặt submenu ngay dưới menu item */
    left: 0; /* Căn trái submenu so với menu item */
    list-style: none;
    padding: 10px 0; /* Khoảng cách bên trong submenu */
    margin: 0;
    background: #ffffff; /* Màu nền trắng */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Bóng đổ nhẹ */
    z-index: 1000; /* Đảm bảo submenu luôn nằm trên cùng */
}

/* Định dạng các mục con của submenu */
.sub-menu-item {
    padding: 10px 20px;
    white-space: nowrap; /* Ngăn không cho ngắt dòng nếu tên dài */
}

/* Định dạng liên kết trong submenu */
.sub-menu-item a {
    text-decoration: none;
    color: #333;
    display: block;
}

/* Hiển thị submenu khi rê chuột vào menu item */
.menu-item:hover .sub-menu {
    display: block; /* Hiển thị submenu */
}

/* Thêm hiệu ứng chuyển đổi */
.sub-menu {
    transition: opacity 0.3s ease, visibility 0.3s ease;
}

</style>
<header id="header">
    <a href="HomeServlet"><img src="./assets/img/logo.png" class="logo" alt=""></a>
    <ul id="navbar">
        <li><a class="active" href="index.html">Home</a></li>
        <li class="menu-item">
            <a href="ShopServlet">Shop</a>
            <ul class="sub-menu">
                <c:forEach items="${categorysList}" var="category">
                    <li class="sub-menu-item">
                        <a href="CategoryServlet?categoryId=${category.id}">${category.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </li>
        <li><a href="blog.jsp">Blog</a></li>
        <li><a href="about.jsp">About</a></li>
        <li><a href="contact.jsp">Contact</a></li>
        <li><a href="CartUserServlet"><i class="fa-solid fa-bag-shopping"></i></a></li>
        <li><a href="DashboardServlet"><i class="fa-solid fa-user"></i></a></li>
        <li>
            <c:if test="${sessionScope.user == null}">
                <a href="LoginServlet">Login</a>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <a href="LogoutServlet">Logout</a>
            </c:if>
        </li>
    </ul>
</header>

