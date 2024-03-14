import java.util.Random;

public class Ex3 {
    
    public void imprimirInformacoes() {
        TPessoa[] pessoas = gerarPessoas();
        int nMasc = 0;
        int tamanho = pessoas.length;

        System.out.println("\nNomes:\n");
        for (int i = 0; i < tamanho; i++) {
            System.out.println(pessoas[i].nome + " " + pessoas[i].sobrenome);
            if(pessoas[i].sexo == 'M') nMasc += 1;
        }

        System.out.println();
        System.out.println("Número de Homens: " + nMasc);
        System.out.println("Número de Mulheres: " + (tamanho - nMasc));
        System.out.println();

        String[] parentesco = new String[tamanho];
        int n = 0;
        for (int i = 0; i < tamanho; i++) {
            for (int j = i + 1; j < tamanho; j++) {
                if (pessoas[i].sobrenome.equals(pessoas[j].sobrenome)) {
                    parentesco[n] = pessoas[i].sobrenome;
                    n++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < pessoas.length; j++) {
                if(pessoas[j].sobrenome.equals(parentesco[i])) {
                    System.out.println(pessoas[j].nome + " " + pessoas[j].sobrenome);
                }
            }
        }
    }
    
    private TPessoa[] gerarPessoas() {
        int nPessoas = 5;
        TPessoa[] pessoas = new TPessoa[nPessoas];
        Random random = new Random();
        char[] sexo = {'M', 'F'};
        for (int i = 0; i < pessoas.length; i++) {
            pessoas[i] = new TPessoa();
            pessoas[i].nome = "" + (char)(i + 65);
            pessoas[i].sobrenome = "" + (char)(random.nextInt(26) + 65);
            pessoas[i].sexo = (sexo[random.nextInt(2)]);
        }

        return pessoas;
    }

    private class TPessoa {
        String nome;
        String sobrenome;
        char sexo;
    }
}
