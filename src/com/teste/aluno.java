package com.teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class aluno {

	public static final String SenhaDB = "oracle";
	public static final String UsuarioDB = "system";

	public static void inserirAluno (String nomeAluno, Double Nota1, Double Nota2 ) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				UsuarioDB,SenhaDB);
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
}
