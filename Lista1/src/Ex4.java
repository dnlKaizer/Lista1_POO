// import java.util.Random;

import java.util.Random;

public class Ex4 {

    public void AlunosMaiorMedia() {
        TAluno[] alunos = new TAluno[100];
        gerarAlunos(alunos);
        for (int i = 0; i < alunos.length; i++) {
            for (int j = i + 1; j < alunos.length; j++) {
                if(alunos[i].media < alunos[j].media) {
                    TAluno var = alunos[i];
                    alunos[i] = alunos[j];
                    alunos[j] = var;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            imprimirAluno(alunos[i]);
        }

    }

    private void imprimirAluno(TAluno aluno) {
        System.out.println();
        System.out.println("Aluno: " + aluno.nome);
        System.out.println("Número: " + aluno.numero);
        for (int i = 0; i < 10; i++) {
            System.out.println("Nota " + (i + 1) + ": " + aluno.notas[i]);
        }
        System.out.println("Média: " + aluno.media);
        System.out.println();
    }

    private TAluno[] gerarAlunos(TAluno[] alunos) {
        Random random = new Random();
        int lastName = 65;
        int name = 65;

        for (int i = 0; i < alunos.length; i++) {
            alunos[i] = new TAluno();
            float media = 0;
            if(lastName + i > 90) {
                lastName -= 26;
                name += 1;
            }
            alunos[i].nome = (char)(name) + "" + (char)(lastName+i);
            if(i >= 10) {
                alunos[i].numero = "00" + i; 
            } else {
                alunos[i].numero = "000" + i; 
            }
            for (int j = 0; j < 10; j++) {
                alunos[i].notas[j] = random.nextInt(11);
                media += alunos[i].notas[j];
            }
            media /= 10f;
            alunos[i].media = media;
        }

        return alunos;
    }
    
    private class TAluno {
        String nome;
        String numero;
        int[] notas = new int[10];
        float media;
    }
}

