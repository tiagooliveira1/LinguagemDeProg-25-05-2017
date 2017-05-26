package com.teste;

import com.opet.util.Reader;

public class Aula25_05_17_Exercicio1 {

	public static void main(String[] args) throws Exception {
		int opcao = 0;
		while (opcao != 9) {
			System.out.println("Informe a sua opção:");
			System.out.println("1) Inserir aluno.");
			System.out.println("2) Alterar informações.");
			System.out.println("3) Excluir aluno.");
			System.out.println("4) Listar alunos.");
			System.out.println("9) Sair.");
			opcao = Reader.readInt();
			
			switch (opcao) {
			case 1:
				System.out.println("Informe o nome do aluno:");
				String nomeAluno;
				Double nota1, nota2;
				nomeAluno = Reader.readString();
				System.out.println("Informe a primeira nota:");
				nota1 = Reader.readDouble();
				System.out.println("Informe a segunda nota:");
				nota2 = Reader.readDouble();
				
				aluno objAluno = new aluno();
				aluno.inserirAluno(nomeAluno, nota1, nota2);
				break;

			default:
				break;
			} 
		}
		System.out.println("Bye bye!");
	}

}
