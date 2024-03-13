// import java.util.Random;

import java.util.Random;

public class Ex4 {

    public void AlunosMaiorMedia() {
        TAluno[] alunos = new TAluno[100];
        gerarAlunos(alunos);
        for (int i = 0; i < alunos.length; i++) {
            System.out.println(alunos[i].nome);
            System.out.println(alunos[i].numero);
            for (int j = 0; j < 10; j++) {
                System.out.println(alunos[i].notas[j]);
            }
            System.out.println();
        }
    }

    private TAluno[] gerarAlunos(TAluno[] alunos) {
        Random random = new Random();
        int lastName = 65;
        int name = 65;

        for (int i = 0; i < alunos.length; i++) {
            alunos[i] = new TAluno();
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
            }
        }

        return alunos;
    }
    
    private class TAluno {
        String nome;
        String numero;
        int[] notas = new int[10];
    }

    private String limitaString(String texto, int maximo){
       if (texto.length() <= maximo){
            return texto;
       } else {
            return texto.substring(0, maximo);
       }
    }
}

