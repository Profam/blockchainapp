<jsp:include page="header.jsp"/>

<form action="/web-ui/wallet-list/${walletId}/new-transaction.html" method="post">

    <h2>New transaction:</h2>
    <div class="form-group">
        <label for="formGroupExampleInput1">receiver Wallet ID</label>
        <input type="text" class="form-control" name="receiverWalletId" id="formGroupExampleInput1"
               placeholder="Wallet ID">
    </div>
    <div class="form-group">
        <label for="formGroupExampleInput2">value</label>
        <input type="text" class="form-control" name="value" id="formGroupExampleInput2" placeholder="value">
    </div>
    <div class="form-group">
        <label for="formGroupExampleInput3">secret key</label>
        <input type="text" class="form-control" name="senderSecretKey" id="formGroupExampleInput3"
               placeholder="secret key">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<jsp:include page="footer.jsp"/>
