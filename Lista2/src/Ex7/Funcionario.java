package Ex7;

public class Funcionario {
    String nome;
    String cpf;

    String formataCpf() {
        String var = "";
        for (int i = 0; i < cpf.length(); i++) {
            if(cpf.charAt(i) != '.' && cpf.charAt(i) != '-') {
                var += cpf.charAt(i);
            }
        }
        return var;
    }

    boolean validaCpf() {
        cpf = formataCpf();

        int num;
        int resto;

        for (int j = 1; j >= 0; j--) {
            num = 0;
            resto = 0;

            for (int i = 0; i < cpf.length() - 1 - j; i++) {
                num += ((int)(cpf.charAt(i)) - 48) * (i + j);
            }
            resto = num % 11;
            if (resto != ((int)cpf.charAt(10 - j) - 48)) {
                return false;
            }
        }
        
        return true;
    }

}
