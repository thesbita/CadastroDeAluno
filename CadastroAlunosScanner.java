import java.util.Scanner;
import javax.swing.JOptionPane;
/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */


public class CadastroAlunosScanner implements InterfaceCadastroAlunos {

    //Atributos da classe CadastroAlunos
        Armazenar arm;
        private String indice = "";
        private boolean sinal = false;
        private int numCadastros = 0;
        private int cadastrosDisponiveis[];
        Scanner input = new Scanner(System.in);
    
    /**
     * getIndice - retorna a opcao selecionada no menu 
     *
     * @return indice, String de indice
     */
    public String getIndice() {
        return indice;
    }
    
    /**
     * setIndice - altera o valor do indice
     * 
     * @return boolean - true: valor alterado
     *                      false: valor nao alterado
     */
    public boolean setIndice(String indice) {
        
        //checagem da string indice (null ou vazia)
        if (Utils.checaStringNull(indice)) {
            return false;
        }else if(Utils.checaStringVazia(indice)) {
            return false;
        }
        this.indice = indice;
        
        //chamada do metodo selecionaOpcao
        selecionaOpcao(this.indice);
        return true;
    }
    
    
    /**
     * selecionaOpcao - menu (switch) do cadastro de alunos
     * 
     * @param opcao - String, opcao recebida do usuario
     */
    public void selecionaOpcao(String opcao) {
        
        //formata a String opcao para letra maiuscula
        opcao = opcao.toUpperCase();
        
        //passa a String opcao para um char
        char c = opcao.charAt(0);
        
        switch (c) {
        
            case 'I':
                //confere se há espaço para armazenar o cadastro
                if (numCadastros < cadastrosDisponiveis.length) {
                    cadastro();//chamada de metodo para cadastrar alunos
                }else {
                    //output em modo grafico - mensagem de erro
                    System.out.println("Cadastros cheios! Libere espaço antes!");
                }
                break;
            case 'R':
                remover();//chamada de metodo para cadastrar alunos
                break;
            case 'L':
                try {
                    listar();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Este indice esta fora do seu limite!");
                }
                break;
            case 'S':
                sair();
            default:
                System.out.println("Opcao Invalida!");
                break;
        }
    }
    
    
    public void cadastro() {
        int indiceCadastro = verificarProxCadastroDisponivel();
        int nDisciplinas = 0;
        boolean flipflop = false;
        boolean raEncontrado = false;
        
        //entrada em modo grafico - recebe, confere e armazena o nome do Aluno
        while (sinal == false) { 
            System.out.println("Insira seu nome: ");
            sinal = arm.insereNome(input.next(), indiceCadastro);
            if (sinal == false) {
                //output em modo grafico - mensagem de erro
                System.out.println("O nome e invalido, tente novamente!");
            }
        }
        sinal = false;
        
        //entrada em modo grafico - recebe, confere e armazena o cpf do Aluno
        while (sinal == false){
            System.out.println("Insira seu cpf: ");
            sinal = arm.insereCPF(input.next(), indiceCadastro);
            if (sinal == false) {
                //output em modo grafico - mensagem de erro
                System.out.println("Cpf invalido, tente novamente!");
            }
        }
        sinal = false;
        
        //entrada em modo grafico - recebe, confere e armazena a data de nascimento do Aluno
        while (sinal == false) {
            System.out.println("Insira sua data de nascimento(DDMMAAAA): ");
            sinal = arm.insereDataDeNascimento(input.next(), indiceCadastro);
            if (sinal == false) {
                //output em modo grafico - mensagem de erro
                System.out.println("Data invalida, tente novamente!");
            }
        }
        sinal = false;
        
        //entrada em modo grafico - recebe, confere e armazena o RA do Aluno
                while (sinal == false) {
                    System.out.println("Insira seu RA: ");
                    sinal = arm.insereRA(input.next(), indiceCadastro);
                    for (int i = 0; i < cadastrosDisponiveis.length; i++) {
                        if (cadastrosDisponiveis[i] == 1) {
                            try {
                                raEncontrado = compararRA(arm.retornaRA(indiceCadastro), i);
                                if(raEncontrado) {
                                    System.out.println("RA ja cadastrado! Tente novamente!");
                                    sinal = false;
                                    flipflop = true;
                                }
                            } catch (NullPointerException e) {
                                sinal = false;
                            }
                        }
                    }
                    if(flipflop == false) {
                        if (sinal == false) {
                            //output em modo grafico - mensagem de erro
                            System.out.println("RA invalido, tente novamente!");
                        }
                    }
                    flipflop = false;
                }
                sinal = false;
        
        //entrada em modo grafico - quantidade de disciplinas que o Aluno deseja inserir
        while(sinal == false) {
            while (sinal == false) {
                System.out.println("Insira o nome do curso:");
                sinal = arm.insereCurso(input.next(), indiceCadastro);
                if (sinal == false) {
                    System.out.println("Nome de curso inválido!");
                }
            }
            System.out.println("Quantas disciplinas você deseja inserir?: ");
            String numero = input.next();
            
            //valida a quantidade de disciplinas
            sinal = arm.validaQtdDisciplinas(numero);
            
            if(sinal) {
                try {
                    nDisciplinas = Integer.parseInt(numero); //passa a string qtd para integer
                }catch (NumberFormatException e) {
                    sinal = false;
                    break;
                }
                if(0 < nDisciplinas && nDisciplinas < 9) {
                    
                    //cadastra a quantidade de disciplinas desejada e suas respectivas notas
                    for (int i = 0; i < nDisciplinas; i++) {
                        cadastrarDisciplinas(i, indiceCadastro); //chamada de metodo para cadastrar o nome da disciplina
                        sinal = false;
                        while (sinal == false) {
                            sinal = cadastrarNotas(i, indiceCadastro); //chamada de metodo para cadastrar a nota da disciplina
                        }
                        
                    }
                    sinal = true;
                } else {
                    System.out.println("Numero invalido!");
                }
            } else {
                System.out.println("Numero invalido!"); //output em modo grafico - mensagem de erro
                sinal = false;
            }
        }
        sinal = false;
        numCadastros++; //atualição do numero cadastros
        cadastrosDisponiveis[indiceCadastro] = 1; //esse cadastro recebe o valor "1" no array cadastrosDisponiveis
        
    }
    
    /**
     * cadastrarDisciplinas - cadastra o nome das disciplinas do Aluno
     * 
     * @param indiceCadastro - int, posicao do Aluno no array de Alunos (cAlunos[])
     * @param indiceDisciplina - int, posicao da disciplina no array de Disciplinas
     * 
     */
    private void cadastrarDisciplinas(int indiceDisciplina, int indiceCadastro) {
        boolean sinal = false;
        while (sinal == false) {
            //recebe, confere e armazena o nome da disciplina que o Aluno deseja inserir
            System.out.println("Insira o nome da Disciplina " + (indiceDisciplina + 1) + ":  ");
            sinal = arm.insereDisciplina(input.next(), indiceDisciplina, indiceCadastro); 
        }    
    }
    
    /**
     * cadastrarNotas - cadastra a nota das disciplinas do Aluno
     * 
     * @param indiceCadastro - int, posicao do Aluno no array de Alunos (cAlunos[])
     * @param indiceDisciplina - int, posicao da disciplina no array de Disciplinas
     * 
     */
    private boolean cadastrarNotas(int indiceDisciplina, int indiceCadastro) {
        boolean sinal = false;
        double aux;
        while (sinal == false) {
            ////recebe, confere e armazena a nota da disciplina que o Aluno deseja inserir
            System.out.println("Insira a nota da Disciplina " + (indiceDisciplina + 1) + ":  ");
            sinal = arm.insereNota(input.next(), indiceDisciplina, indiceCadastro);
            aux = arm.retornaNota(indiceDisciplina, indiceCadastro);
            if(aux >= 0.0 && aux <= 10.0) {
                sinal = true;
            }else {
                sinal = false;
                System.out.println("Nota invalida! Insira uma nota entre 0 e 10!");
            }
        }    
        return sinal;
    }

    
    /**
     * remover - remove o cadastro de acordo com o indice do aluno
     */
    public void remover() {
        if(numCadastros != 0) {
            String RA = entradaRA();//chamada de metodo que verifica o RA
            boolean cadastroEncontrado = false;
            boolean nenhumCadastro = true;
            for (int i = 0; i < cadastrosDisponiveis.length; i++) {
                if(!(Utils.checaStringNull(indice))){
                    cadastroEncontrado = compararRA(RA, i);
                    if(cadastroEncontrado == true) {
                        arm.limparAluno(i); //chamada do metodo que apaga os dados do aluno
                        cadastrosDisponiveis[i] = 0; //atualiza o valor para "0" no array cadastrosDisponiveis[]
                        numCadastros--; //atualiza o numero atual de cadastros
                        System.out.println("Cadastro Removido com sucesso!"); //output em modo grafico - mensagem de exito
                        nenhumCadastro = false;
                    }
                }
            }
            if(nenhumCadastro) {
                System.out.println("Nao existem cadastros com esse RA!");
            }
        } else {
            System.out.println("Nao existem cadastros de aluno para remover");
        }
    }
    /**
     * listar - imprime de maneira grafica a ficha dos alunos com os parametros desejados
     * 
     */
    public void listar() {
        String tempDisciplinas[] = new String [8];
        double tempNotas[] = new double [8];
        boolean cadastroEncontrado = false;
        boolean flipflop = false;
        if(numCadastros != 0) {
            String RA = entradaRA();//chamada de metodo que verifica o RA
            
            for (int i = 0; i < cadastrosDisponiveis.length; i++) {
                if (cadastrosDisponiveis[i] == 1) {
                    cadastroEncontrado = compararRA(RA, i);
                    if(cadastroEncontrado) {
                        imprimirCadastro(i); //se o cadastro existir ele é impresso por meio do metodo imprimirCadastro
                        
                        //armazena o nome das disciplinas e das notas nos arrays tempDisciplinas e tempNotas
                        for (int m = 0; m < 8; m++) {
                            tempDisciplinas[m] = getDisciplinasListar(i,m); 
                            if(tempDisciplinas[m].equals("Disciplina não cadastrada")) {
                                tempNotas[m] = 0.0;
                            } else tempNotas[m] = getNotasListar(i,m);
                        }
                        imprimirCurso(i);//chamada do metodo que imprime o nome do curso
                        imprimirDisciplinas(tempDisciplinas); //chamada do metodo que imprime o nome das disciplinas
                        imprimirNotas(tempNotas); //chamada do metodo que imprime a nota das disciplinas
                        flipflop = true;
                    }
                }    
            }
            if(flipflop == false) {
                JOptionPane.showMessageDialog(null, "Nao existem cadastros com esse RA!");
            }
        }else {
            JOptionPane.showMessageDialog(null, "Nao existem cadastros para remover!");
        }
    }
    
    /**
     * imprimirCadastro - imprime as informações do cadastro dos alunos
     * 
     * @param i - int, posicao do Aluno no array de Alunos (cAlunos[])
     * 
     */
    private void imprimirCadastro(int i) {
        System.out.println(
            "O nome cadastrado e: " + arm.retornaNome(i) +
            "\nO cpf cadastrado e: " + arm.retornaCPF(i) + 
            "\nA data de nascimento e: " + arm.retornaDataDeNascimento(i) +
            "\nA sua idade e: " + arm.retornaIdade(i) +
            "\nO RA esta cadastrado como: " + arm.retornaRA(i));
        
            
    }
    
    /**
     * imprimirCurso - imprime o curso do aluno
     * 
     * @param indiceCadastro - int, com o indice do cadastro do aluno
     * 
     */
    private void imprimirCurso(int indiceCadastro) {
        JOptionPane.showMessageDialog(null, "Nome do curso: " + arm.retornaCurso(indiceCadastro));
    }
    
    /**
     * imprimirDisciplinas - imprime o nome das disciplinas do aluno
     * 
     * @param i - int, posicao do Aluno no array de Alunos (cAlunos[])
     * @param tempDisciplinas[] - String, array dos nomes das disciplinas
     * 
     */
    private void imprimirDisciplinas(String tempDisciplinas[]) {
        System.out.println("Disciplina 0: " + tempDisciplinas[0] + "\n" +
                "Disciplina 1: " + tempDisciplinas[1] + "\n" +
                "Disciplina 2: " + tempDisciplinas[2] + "\n" +
                "Disciplina 3: " + tempDisciplinas[3] + "\n" +
                "Disciplina 4: " + tempDisciplinas[4] + "\n" +
                "Disciplina 5: " + tempDisciplinas[5] + "\n" +
                "Disciplina 6: " + tempDisciplinas[6] + "\n" +
                "Disciplina 7: " + tempDisciplinas[7] + "\n" );
    
    }
    
    /**
     * imprimirNotas - imprime a nota das disciplinas do aluno
     * 
     * @param tempNotas[] - double, array das notas das disciplinas
     * 
     */
    private void imprimirNotas(double tempNotas[]) { 
        System.out.println("Disciplina 0: " + tempNotas[0] + "\n" +
                "Disciplina 1: " + tempNotas[1] + "\n" +
                "Disciplina 2: " + tempNotas[2] + "\n" +
                "Disciplina 3: " + tempNotas[3] + "\n" +
                "Disciplina 4: " + tempNotas[4] + "\n" +
                "Disciplina 5: " + tempNotas[5] + "\n" +
                "Disciplina 6: " + tempNotas[6] + "\n" +
                "Disciplina 7: " + tempNotas[7] + "\n" );
    
    }

    /*
     * sair - fecha o programa
     * 
     */
    public void sair() {
        System.exit(0);
    }
    
    /**
     * getNomesListar - se o cadastro existir retorna o nome do aluno, caso contrario retorna a string "Vaga Aberta"
     * 
     * @param i - int, posicao do Aluno no array de Alunos (cAlunos[])
     * 
     * @return nome, String nome      
     */
    private String getNomesListar(int i) {
        String nome;
        try {
            arm.retornaNome(i).length();
        } catch (Exception e) {
            nome = "Vaga Aberta";
            return nome;
        }
        nome = arm.retornaNome(i);
        return nome;
    }
    
    /**
     * getDisciplinasListar - se a disciplina existir retorna o nome da disciplina, caso contrario retorna a string "Disciplina não cadastrada"
     * 
     * @param i - int, posicao do Aluno no array de Alunos (cAlunos[])
     * @param m - String, array dos nomes das disciplinas
     * 
     * @return disciplina, String disciplina      
     */
    private String getDisciplinasListar(int i, int m) {
        String disciplina;
        try {
            arm.retornaDisciplina(i,m).length();
        } catch (Exception e) {
            disciplina = "Disciplina não cadastrada";
            return disciplina;
        }
        disciplina = arm.retornaDisciplina(i, m);
        return disciplina;
    }
    
    /**
     * getNotasListar - retorna a nota armazenada da disciplina
     * 
     * @return nota, double nota
     */
    private double getNotasListar(int i, int m) {
        return arm.retornaNota(i, m);
    }
    
    /**
     * entradaRA - verifica se o indice é uma opção válida
     * 
     * @return temp, String com o RA
     */
    private String entradaRA() {
        boolean bool = false, aux = true;
        String temp;
        do {
            System.out.println("Insira o RA desejado: ");
            temp = input.next();
            if (Utils.checaStringNull(temp)) {
                aux = false;
            }else if(Utils.checaStringVazia(temp)) {
                aux = false;
            }
            if (aux == true) {
                if (Utils.checaRA(temp)) {
                    temp = Utils.formatarRA(temp);
                    bool = true;
                }
            }
            if (bool == false) {
                System.out.println("O RA nao e valido!");
            }
        }while(bool == false);
        return temp;
    }
    
    /**
     * compararRA - compara o RA para ver é o mesmo
     * 
     *  @return boolean - true: RA igual
     *                    false : RA não é igual
     */
    public boolean compararRA(String rA, int indiceCadastro) {
        if(rA.equals(arm.retornaRA(indiceCadastro))) {
            return true;
        }else return false;
        
    }
    
    /**
     * entradaIndice - verifica se o indice é uma opção válida
     * 
     * @return i, int indice
     */
    private int entradaIndice() {
        boolean bool = false;
        String temp;
        int i = -1;
        while (bool == false) {
            System.out.println("Insira o indice desejado: ");
            temp = input.next();
            try {
                i = Integer.parseInt(temp);
            } catch (NumberFormatException e) {
                System.out.println("O indice nao e valido!");
                i = -1;
            }
            if (i >= 0) {
                bool = true;
            }
        }
        return i;
    }
    
    /**
     * verificarProxCadastroDisponivel - verifica qual é o proximo cadastro disponivel
     * 
     * @return proxDisp, int indice do proximo cadastro disponivel no array de cadastros
     */
    private int verificarProxCadastroDisponivel(){
        int proxDisp = -1, i = 0;
        while(proxDisp == -1) {
            if (cadastrosDisponiveis[i] == 0) {
                proxDisp = i;
            }
            i++;
        }
        return proxDisp;
    }
}
