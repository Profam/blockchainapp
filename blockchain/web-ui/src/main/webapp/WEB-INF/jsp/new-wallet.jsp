<jsp:include page="header.jsp"/>
<h1>New wallet:</h1>

<form action="/web-ui/new-wallet.html" method="post">
    <div class="form-group">
        <label for="formGroupExampleInput1">Wallet name</label>
        <input type="text" class="form-control" name="walletName" id="formGroupExampleInput1" placeholder="Wallet name">
    </div>
    <div class="form-group">
        <label for="formGroupExampleInput2">Secret key</label>
        <input type="text" class="form-control" name="secretKey" id="formGroupExampleInput2" placeholder="Secret key">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<jsp:include page="footer.jsp"/>