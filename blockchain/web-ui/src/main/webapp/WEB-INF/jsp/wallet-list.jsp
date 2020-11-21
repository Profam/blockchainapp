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
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${wallets}" var="wallet">
            <tr>
                <td>
                    <p><a href="/web-ui/${wallet.walletId}/wallet-details.html"
                          class="text-primary">${wallet.walletId}</a></p>
                </td>
                <td>
                        ${wallet.walletName}
                </td>
                <td>
                    <p><a href="/web-ui/wallet-list/${wallet.walletId}/transaction-list.html"
                          class="btn btn-primary btn-sm" role="button" aria-pressed="true">history</a></p>
                </td>
                <td><p><a href="/web-ui/wallet-list/${wallet.walletId}/new-transaction.html"
                          class="btn btn-primary btn-sm" role="button" aria-pressed="true">send funds</a></p>
                </td>
                <td>
                    <p><a href="http://localhost:8081/mine/${wallet.walletId}" class="btn btn-primary btn-sm"
                          role="button" aria-pressed="true">mine</a></p>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
<jsp:include page="footer.jsp"/>