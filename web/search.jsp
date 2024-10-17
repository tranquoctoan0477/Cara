<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="./assets/css/style.css">
    </head>

    <body>
        <%@include file="./inc/header.jsp" %>

        <section id="page-header">
            <h2>${keywork}</h2>
            <p>Save more with coupons & up to 70% off!</p>
            <div class="search-from">
                    <form action="SearchServlet" class="w-full" method="post">
                        <div class="from-control flex item-center relative w-full">
                            <input name="keyword" placeholder="Search...." type="text" class="search-input w-full bg-white border-[1px] rounded-full h-[40px] border-[#b3b3b3] pl-[40px]">
                        </div>
                    </form>
                </div>
        </section>

        <section id="product1" class="section-p1">
            <div class="row">
                <form action="CategoryServlet" class="filter">
                    <strong>Sort by:</strong>
                    <select name="property" id="" class="from-control property">
                        <option value="name">Name</option>
                        <option value="price">Price</option>
                        <option value="time">Time</option>
                    </select>
                    <strong>Order by:</strong>
                    <select name="order" id="" class="from-control order">
                        <option value="az">A-Z</option>
                        <option value="za">Z-A</option>
                    </select>
                    <button class="filter-submit btn btn-primary">Filter</button>
                </form>
            </div>
            <div class="prod-cont">
                <c:forEach items="${productList}" var="product">
                    <div class="prod">
                        <img src="${pageContext.request.contextPath}${product.thumbnail.replace('D:/webproject/Cara/web', '')}" alt="">
                        <div class="des">
                            <span>adidas</span>
                            <h5>${product.name}</h5>
                            <div class="star">
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                            </div>
                            <h4>${product.price}</h4>
                        </div>
                        <a href="#"><i class="fa-solid fa-cart-shopping cart"></i></a>
                    </div>
                </c:forEach>
        </section>

        <section id="pagination" class="section-p1">
            <a href="#">1</a>
            <a href="#">2</a>
            <a href="#"><i class="fa-solid fa-arrow-right"></i></a>
        </section>

        <%@include file="./inc/footer.jsp" %>

    </body>

</html>
