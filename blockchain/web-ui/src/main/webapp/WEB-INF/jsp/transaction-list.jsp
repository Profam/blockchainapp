<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"/>
<h1>Transaction-list:</h1>

<table class="table">
    <thead>
    <tr>
        <th scope="col">transaction ID</th>
        <th scope="col">walletId</th>
        <th scope="col">receiverWalletId</th>
        <th scope="col">value</th>
        <th scope="col">status</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${transactions}" var="transaction">
        <tr>
            <th scope="row">${transaction.id}</th>
            <td>${transaction.walletId}</td>
            <td>${transaction.receiverWalletId}</td>
            <td>${transaction.value}</td>
            <td>${transaction.transactionStatus}</td>

            <td>
                <c:if test="${(transaction.transactionStatus == 'Pending') && (walletId == transaction.receiverWalletId)}">
                    <form action="/web-ui/wallet-list/${walletId}/transaction-list.html" method="post">
                        <div class="form-group" style="display: flex">
                            <label for="formGroupExampleInput3"></label>
                            <input type="hidden" name="id" value="${transaction.id}">
                            <input type="text" class="form-control" name="senderSecretKey" id="formGroupExampleInput3"
                                   placeholder="secret key">
                            <button type="submit" class="btn btn-primary">Verify</button>
                        </div>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
