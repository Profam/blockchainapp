<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>Blockchain app!</title>
</head>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/web-ui/home.html">Home <span class="sr-only">(current)</span></a>
            </li>
            <sec:authorize access="!isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link" href="/web-ui/login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/web-ui/register">Register</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ADMIN')">
                <li class="nav-item">
                    <a class="nav-link" href="/web-ui/register">Register</a>
                </li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Wallets
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/web-ui/new-wallet.html">New wallet</a>
                        <a class="dropdown-item" href="/web-ui/wallet-list.html">Wallet list</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/web-ui/logout">Logout</a>
                </li>
            </sec:authorize>
        </ul>
        <sec:authorize access="hasRole('USER')">
            <a href="#">Welcome user, <b><sec:authentication property="principal.username"/></b>!</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
            <a href="#">Welcome Administrator, <b><sec:authentication property="principal.username"/></b>!</a>
        </sec:authorize>
    </div>
</nav>