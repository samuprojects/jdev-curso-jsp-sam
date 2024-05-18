package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco = "jdbc:postgresql://localhost:5432/curso_jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "admin";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static { // de qualquer lugar que for chamado a classe será fornecido essa conexão existente
		conectar();
	}
	
	public SingleConnectionBanco() { // quando existir uma instância vai conectar
		conectar();

	}
	
	private static void conectar() { // caso não exista será criada
		
		try {
			
			if (connection == null) {
				Class.forName("org.postgresql.Driver"); // para carregar o driver de conexão do banco
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false); // para não efetuar alterações no banco sem nosso comando
			}
			
		} catch (Exception e) {
			e.printStackTrace(); // mostrar erros no momento da execução
		}
	}

}
