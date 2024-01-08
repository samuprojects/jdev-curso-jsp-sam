package filter;

import java.io.IOException;

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
       
    public FilterAutenticacao() {
    }

    // encerra processos quando o servidor é parado, exemplo, conexão com o banco
	public void destroy() {
	}

	// intercepta e responde todas as requisições do sistema, exemplo, validação autenticação, commit/ rollback banco, redirecionamento de páginas, etc
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
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
	}

	// 	inicia processos ou recursos quando o servidor é carregado, exemplo, conexão com banco	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
