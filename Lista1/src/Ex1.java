import java.util.Scanner;

public class Ex1 {
    private class TAluno {
        String nome;
        float idade;
        String endereco;
        String dataInscricao;
        String dataObtencao;
    }

    public void CadastroAlunosAutoescola() throws Exception {
        Scanner sc = new Scanner(System.in);
        TAluno[] alunos = new TAluno[50];
        um: {
            for (int i = 0; i < alunos.length; i++) {
                
                alunos[i] = new TAluno();
                System.out.println("\nAdicionar aluno " + (i+1) + ":");

                System.out.print("Nome: ");
                alunos[i].nome = sc.next();
                if(alunos[i].nome.equals(".")) break um;

                do {
                    System.out.print("Idade: ");
                    try {
                        alunos[i].idade = sc.nextFloat();
                    } catch(Exception exception) {
                        break um;
                    }
                        
                } while(alunos[i].idade < 18);

                System.out.print("Endereço: ");
                alunos[i].endereco = sc.next();
                if(alunos[i].endereco.equals(".")) break um;

                System.out.print("Data de Inscrição: ");
                alunos[i].dataInscricao = sc.next();
                if(alunos[i].dataInscricao.equals(".")) break um;

                System.out.print("Data de obtenção: ");
                alunos[i].dataObtencao = sc.next();
                if(alunos[i].dataObtencao.equals(".")) break um;

                
            }
        }
        acharAlunoMaisNovo(alunos);

        sc.close();
    }

    private void acharAlunoMaisNovo(TAluno[] alunos) {
        TAluno alunoMaisNovo = new TAluno();
        alunoMaisNovo.idade = 999;
        int tamanhoListaAluno = alunos.length;
        for(int i = 0; i < alunos.length; i++) {
            if(alunos[i].dataObtencao == null || alunos[i].dataObtencao.equals(".")) {
                tamanhoListaAluno = i-1;
                break;
            }
        }
        
        for(int i = 0; i < tamanhoListaAluno; i++) {
            if(alunos[i].idade < alunoMaisNovo.idade) {
                alunoMaisNovo = alunos[i];
            } 
        }
        
        if(alunoMaisNovo.dataObtencao != null) {
            System.out.println("\nDados do Aluno Mais Novo:\n");
            System.out.println("Nome: " + alunoMaisNovo.nome);
            System.out.println("Idade: " + alunoMaisNovo.idade);
            System.out.println("Endereço: " + alunoMaisNovo.endereco);
            System.out.println("Data de Inscrição: " + alunoMaisNovo.dataInscricao);
            System.out.println("Data de Obtenção: " + alunoMaisNovo.dataObtencao);
        }
    }
}
