<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Produto</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <c:if test="${produto != null}">
            <h1>Alteração de Produto</h1>
        </c:if>
        <c:if test="${produto == null}">
            <h1>Cadastro de Produto</h1>
        </c:if>
        <form action="ServletProduto" method="post">
            <input type="hidden" name="acao" value="${produto != null ? 'alterar' : 'incluir'}">
            <c:if test="${produto != null}">
                <input type="hidden" name="id" value="${produto.idProduto}">
            </c:if>
            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" class="form-control" id="nome" name="nome" value="${produto != null ? produto.nome : ''}" required>
            </div>
            <div class="form-group">
                <label for="quantidade">Quantidade</label>
                <input type="number" class="form-control" id="quantidade" name="quantidade" value="${produto != null ? produto.quantidade : ''}" required>
            </div>
            <div class="form-group">
                <label for="precoVenda">Preço de Venda</label>
                <input type="number" step="0.01" class="form-control" id="precoVenda" name="precoVenda" value="${produto != null ? produto.precoVenda : ''}" required>
            </div>
            <button type="submit" class="btn btn-primary">${produto != null ? 'Alterar' : 'Incluir'}</button>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
