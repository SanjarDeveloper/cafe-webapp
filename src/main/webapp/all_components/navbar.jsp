<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-custom">
    <a class="navbar-brand" href="index.jsp" style="font-weight: bolder">CAFE</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:if test="${empty userobj}" >
                <li class="nav-item active">
                    <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">Please Sign in to Order!</a>
                </li>
            </c:if>
            <c:if test="${userobj.user_type eq 'admin'}">
                <li class="nav-item">
                    <a class="nav-link" href="menu.jsp">Menu</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="view_jobs.jsp">Customers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="view_jobs.jsp">Orders</a>
                </li>
            </c:if>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <c:if test="${userobj.user_type eq 'admin'}" >
                <a href="#" class="btn btn-light mr-1"><i class="fas fa-user"></i>Admin</a>
                <a href="#" class="btn btn-light"><i class="fas fa-sign-in-alt"></i>Log out</a>
            </c:if>
            <c:if test="${empty userobj}" >
                <a href="login.jsp" class="btn btn-light mr-1"><i class="fas fa-sign-in-alt"></i>Sign in</a>
                <a href="signup.jsp" class="btn btn-light"><i class="fas fa-user"></i>Sign Up</a>
            </c:if>
        </form>
    </div>
</nav>