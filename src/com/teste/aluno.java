package com.teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class aluno {

	public static final String SenhaDB = "oracle";
	public static final String UsuarioDB = "system";
	
	// declara variável global para guardar a conexão com o banco
	public static Connection conn; 
	
	public aluno() throws ClassNotFoundException, SQLException {
		// cria um construtor para aluno, para conectar no banco toda vez que instanciar
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
		// coloca a conexão para a variável global da classe 
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				UsuarioDB,SenhaDB);
		
	}

	public static void inserirAluno (String nomeAluno, Double Nota1, Double Nota2 ) throws ClassNotFoundException, SQLException {
		
				PreparedStatement stmt =
                conn.prepareStatement("insert into aluno (codigo, nome, nota1, nota2) values ("
                                + "(select coalesce(max(codigo)+1,1) from aluno), ?, ?, ?)");
		stmt.setString(1,nomeAluno);
		stmt.setDouble(2,Nota1);
		stmt.setDouble(3,Nota2);
		try {
			/* inicia a transação */
			conn.setAutoCommit(false);
			/* executa a query */
			ResultSet rs = stmt.executeQuery();
		    System.out.println("Registro gravado com sucesso.");
		    /* COmmita a transação */
		    conn.commit();
		
		} catch(Exception e) {
		    System.out.println("Erro ao gravar registro.");
		    // volta a transação para estado normal
		    conn.rollback();
		}
		
	}
	
	public static void excluirAluno (int Codigo) {
		
	}
	
	public static void listarAlunos() throws SQLException {
		PreparedStatement stmt =
                conn.prepareStatement("select * from aluno");
		try {
			/* executa a query */
			ResultSet rs = stmt.executeQuery();
			// imprime um "cabeçalho" do resultado
			System.out.print( preenche_espacos("Aluno", 30) );
			System.out.print( preenche_espacos("Nota 1", 8) );
			System.out.print( preenche_espacos("Nota 2", 8) );
			System.out.println("");
			System.out.print( preenche_espacos("-----", 30) );
			System.out.print( preenche_espacos("------", 8) );
			System.out.print( preenche_espacos("------", 8) );
			System.out.println("");
			
			// percorre o resultset
			while(rs.next()){
				System.out.print( preenche_espacos(rs.getString("nome"), 30));
				System.out.print( preenche_espacos(rs.getString("nota1"),8));
				System.out.println(preenche_espacos(rs.getString("nota2"));
			}
			
		} catch(Exception e) {
		    System.out.println("Erro ao selecionar registros.");
		}
		
	}
	
	public static  String preenche_espacos(String str, int tamanho) {
		/* Função somente para preencher com espaços, para ficar bonitinho no console
		 * Esta função não deveria estar dentro da classe aluno, má "vâmo que vâmo" 
		 *  */
		String temp = str;
		if (temp.length() < tamanho) {
			/* preenche com espaços até o tamanho desejado*/
			while (temp.length() < tamanho) {
				temp = temp + " ";	
			}
		}
		/* retorna a string com os espaços */
		return temp;
		
	}
}
