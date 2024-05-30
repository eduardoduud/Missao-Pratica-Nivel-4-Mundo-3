package cadastroee.servlets;

import cadatroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletProduto", urlPatterns = {"/ServletProduto"})
public class ServletProduto extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal produtoFacade;

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {
        String acaoRequisicao = requisicao.getParameter("acao");
        if (acaoRequisicao == null || acaoRequisicao.isEmpty()) {
            acaoRequisicao = "listar";
        }
        switch (acaoRequisicao) {
            case "excluir":
                excluirProduto(requisicao, resposta);
                break;
            case "formAlterar":
                exibirFormularioAlteracao(requisicao, resposta);
                break;
            case "formIncluir":
                exibirFormularioInclusao(requisicao, resposta);
                break;
            default:
                listarProdutos(requisicao, resposta);
        }
    }

    @Override
    protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {
        String acaoRequisicao = requisicao.getParameter("acao");
        if (acaoRequisicao != null && !acaoRequisicao.isEmpty()) {
            switch (acaoRequisicao) {
                case "incluir":
                    incluirProduto(requisicao, resposta);
                    break;
                case "alterar":
                    alterarProduto(requisicao, resposta);
                    break;
            }
        } else {
            resposta.sendRedirect("ServletProduto");
        }
    }

    private void listarProdutos(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {
        List<Produto> listaProdutos = produtoFacade.findAll();
        requisicao.setAttribute("produtos", listaProdutos);
        RequestDispatcher despachante = requisicao.getRequestDispatcher("ProdutoLista.jsp");
        despachante.forward(requisicao, resposta);
    }

    private void excluirProduto(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {
        try {
            int idProdutoExcluir = Integer.parseInt(requisicao.getParameter("id"));
            produtoFacade.remove(produtoFacade.find(idProdutoExcluir));
            resposta.sendRedirect("ServletProduto");
        } catch (NumberFormatException | NullPointerException e) {
            resposta.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void exibirFormularioAlteracao(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {
        try {
            int idProdutoAlterar = Integer.parseInt(requisicao.getParameter("id"));
            Produto produto = produtoFacade.find(idProdutoAlterar);
            requisicao.setAttribute("produto", produto);
            RequestDispatcher despachante = requisicao.getRequestDispatcher("ProdutoDados.jsp");
            despachante.forward(requisicao, resposta);
        } catch (NumberFormatException | NullPointerException e) {
            resposta.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void incluirProduto(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {
        try {
            String nomeProduto = requisicao.getParameter("nome");
            int quantidadeProduto = Integer.parseInt(requisicao.getParameter("quantidade"));
            Float precoProduto = Float.valueOf(requisicao.getParameter("precoVenda"));
            Produto novoProduto = new Produto();
            novoProduto.setNome(nomeProduto);
            novoProduto.setQuantidade(quantidadeProduto);
            novoProduto.setPrecoVenda(precoProduto);
            produtoFacade.create(novoProduto);
            resposta.sendRedirect("ServletProduto");
        } catch (NumberFormatException | NullPointerException e) {
            resposta.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void alterarProduto(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {
        try {
            int idProduto = Integer.parseInt(requisicao.getParameter("id"));
            String nomeProduto = requisicao.getParameter("nome");
            int quantidadeProduto = Integer.parseInt(requisicao.getParameter("quantidade"));
            Float precoProduto = Float.valueOf(requisicao.getParameter("precoVenda"));
            Produto produtoAlterado = produtoFacade.find(idProduto);
            produtoAlterado.setNome(nomeProduto);
            produtoAlterado.setQuantidade(quantidadeProduto);
            produtoAlterado.setPrecoVenda(precoProduto);
            produtoFacade.edit(produtoAlterado);
            resposta.sendRedirect("ServletProduto");
        } catch (NumberFormatException | NullPointerException e) {
            resposta.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void exibirFormularioInclusao(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {
        RequestDispatcher despachante = requisicao.getRequestDispatcher("ProdutoDados.jsp");
        despachante.forward(requisicao, resposta);
    }
}
