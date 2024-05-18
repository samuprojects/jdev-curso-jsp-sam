package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*"}) // intercepta todas as requisições que vierem do projeto ou mapeamento
public class FilterAutenticacao implements Filter {
	
	private static Connection connection;
       
    public FilterAutenticacao() {
    }

    // encerra processos quando o servidor é parado, exemplo, conexão com o banco
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// intercepta e responde todas as requisições do sistema, exemplo, validação autenticação, commit/ rollback banco, redirecionamento de páginas, etc
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			String usuarioLogado = (String) session.getAttribute("usuario");
			
			String urlParaAutenticar = req.getServletPath(); // url que está sendo acessada
			
			// validação de login ou redirecionamento paginas
			if (usuarioLogado == null &&															
					!urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) { // não está logado
				
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login!");
				redireciona.forward(request, response);
				return; // Vai parar a execução e redirecionar ao login
			} else {
				chain.doFilter(request, response);
			}
			
			connection.commit(); // Se deu tudo certo o commit é realizado
			
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}				
	}

	// 	inicia processos ou recursos quando o servidor é carregado, exemplo, conexão com banco	
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
