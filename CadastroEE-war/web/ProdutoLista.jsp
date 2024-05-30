<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="cadastroee.model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Produtos</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>Lista de Produtos</h1>
        <a href="ServletProduto?acao=formIncluir" class="btn btn-primary mb-3">Incluir Produto</a>
        <c:if test="${not empty produtos}">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Quantidade</th>
                        <th>Preço</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="produto" items="${produtos}">
                        <tr>
                            <td>${produto.idProduto}</td>
                            <td>${produto.nome}</td>
                            <td>${produto.quantidade}</td>
                            <td>${produto.precoVenda}</td>
                            <td>
                                <a href="ServletProduto?acao=formAlterar&id=${produto.idProduto}" class="btn btn-warning">Alterar</a>
                                <a href="ServletProduto?acao=excluir&id=${produto.idProduto}" class="btn btn-danger">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    <c:if test="${empty produtos}">
        <p>Nenhum produto encontrado.</p>
    </c:if>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
