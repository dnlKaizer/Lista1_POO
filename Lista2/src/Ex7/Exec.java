package Ex7;
import java.util.Scanner;

public class Exec {
    public void registrarFuncionario() throws Exception {
        Scanner sc = new Scanner(System.in);
    
        Funcionario func = new Funcionario();
        System.out.print("\nDigite o nome do funcionário: ");
        func.nome = sc.next(); 
        while (true) {
            System.out.print("\nDigite o cpf do funcionário: ");
            func.cpf = sc.next(); 
            boolean verify = func.validaCpf();
            
            if (verify) {
                System.out.println("\nFuncionário cadastrado.");
                break;
            } else {
                System.out.println("\nERRO. CPF inválido. Tente novamente.");
                Thread.sleep(2500);
            }
        }

        sc.close();
    }
}
