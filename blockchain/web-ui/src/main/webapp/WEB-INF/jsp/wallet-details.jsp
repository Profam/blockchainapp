<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="header.jsp"/>
<h1>Wallet details:</h1>

<table class="table">
    <thead>
    <tr>
        <th scope="col">Wallet ID</th>
        <th scope="col">Wallet Name</th>
        <sec:authorize access="hasRole('USER')">
            <th scope="col">Secret key</th>
        </sec:authorize>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${wallet.walletId}</td>
        <td>${wallet.walletName}</td>
        <sec:authorize access="hasRole('USER')">
            <td>${wallet.secretKey}</td>
        </sec:authorize>
    </tr>
    </tbody>

</table>


<jsp:include page="footer.jsp"/>