<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="header.jsp"/>
<h1>Wallet list:</h1>
<form action="localhost:8080/web-ui/${wallet.walletId}" method="post" enctype="multipart/form-data">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Wallet ID</th>
            <th scope="col">Wallet name</th>
            <th scope="col">secret key</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${wallets}" var="wallet">
            <tr>
                <th scope="row">${wallet.walletId}</th>
                <td>${wallet.walletName}</td>
                <td>${wallet.secretKey}</td>
            </tr>
            <tr>
                <td><p><a href="/web-ui/${wallet.walletId}/wallet-details.html"
                          class="text-primary">${wallet.walletId}</a></p>
                <td>
                <td>${wallet.walletOwner}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<jsp:include page="footer.jsp"/>