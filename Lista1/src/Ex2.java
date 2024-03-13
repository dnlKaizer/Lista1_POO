public class Ex2 {
    
    public void EncontrarFuncionarios() throws Exception {

        TFuncionario[] funcionarios = new TFuncionario[50];
        TEmpresa[] empresas = new TEmpresa[15];

        for (int i = 0; i < funcionarios.length; i++) {
            if(funcionarios[i] != null) {
                if(funcionarios[i].nDependentes >= 5) {
                    imprimirDadosFuncionario(funcionarios[i]);
                }
            } else {
                break;
            }
        }

    }

    private void imprimirDadosFuncionario(TFuncionario funcionario) {
        
        System.out.println();
        System.out.println("Número da Empresa: " + funcionario.numeroEmpresa);
        System.out.println("Número do Funcionário: " + funcionario.numeroFuncionario);
        System.out.println("Nome: " + funcionario.nome);
        System.out.println("Data de Nascimento: " + funcionario.dataNascimento);
        System.out.println("Estado Civil: " + funcionario.estadoCivil);
        System.out.println("Endereço: " + funcionario.endereco);
        System.out.println("RG: " + funcionario.rg);
        System.out.println("CIC: " + funcionario.cic);
        System.out.println("Telefone: " + funcionario.telefone);
        System.out.println("Número de Dependentes: " + funcionario.nDependentes);
        for (int i = 0; i < funcionario.nDependentes; i++) {
            System.out.println(". Dependente " + (i+1) + ": " + funcionario.nomeDependentes[i]);
            System.out.println(". Parentesco: " + funcionario.parentesco[i]);
        }
        System.out.println("Nome do Pai: " + funcionario.nomePai);
        System.out.println("Nome da Mãe: " + funcionario.nomeMae);
        System.out.println();
    }

    private class TFuncionario {
        String numeroEmpresa;
        String numeroFuncionario;
        String nome;
        String dataNascimento;
        String estadoCivil;
        String endereco;
        String rg;
        String cic;
        String telefone;
        int nDependentes;
        String[] nomeDependentes = new String[6];
        String[] parentesco = new String[6];
        String nomePai;
        String nomeMae;
    }

    private class TEmpresa {
        String numero;
        String nome;
        String endereco;
        String pessoaContato;
        String email;
        String[] telefones = new String[4];
    }
}
