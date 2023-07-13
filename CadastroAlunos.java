import javax.swing.JOptionPane;
/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */


public class CadastroAlunos implements InterfaceCadastroAlunos{
    
    //Atributos da classe CadastroAlunos
    Armazenar arm;
    private String indice = "";
    private boolean sinal = false;
    private int numCadastros = 0;
    private int cadastrosDisponiveis[];
    
    /**
     * CadastroAlunos Construtor para instanciar o objeto do tipo Armazena, e determinar o tamanho do array cadastrosDisponiveis[]
     *
     * @param qtd - int, qtd de alunos que podem ser cadastrados
     * 
     */
    public CadastroAlunos(int qtd) {
            arm = new Armazenar(qtd);
            cadastrosDisponiveis = new int [qtd];
            
            //preenche o array cadastros disponiveis com "0" para indicar que ainda não há aluno cadastrado nas posicoes indicadas
            for (int i = 0; i < cadastrosDisponiveis.length; i++) {
                cadastrosDisponiveis[i] = 0;
            }
    }
    
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
                    JOptionPane.showMessageDialog(null, "Cadastros cheios! Libere espaço antes!");
                }
                break;
            case 'R':
                remover();//chamada de metodo para cadastrar alunos
                //removerAntigo();//chamada de metodo para cadastrar alunos
                break;
            case 'L':
                try {
                    listar();//chamada de metodo para listar o cadastro de alunos
                    //listarAntigo();//chamada de metodo para listar o cadastro de alunos
                } catch (ArrayIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(null, "Este indice esta fora do seu limite!");
                }
                break;
            case 'S':
                sair();
            default:
                JOptionPane.showMessageDialog(null, "Opcao Invalida!");
                break;
        }
    }
    
    /**
     * cadastro - adiciona um novo cadastro de aluno
     * 
     */
    public void cadastro() {
        int indiceCadastro = verificarProxCadastroDisponivel();
        int nDisciplinas = 0;
        boolean flipflop = false;
        boolean raEncontrado = false;
        
        //entrada em modo grafico - recebe, confere e armazena o nome do Aluno
        while (sinal == false) { 
            sinal = arm.insereNome(JOptionPane.showInputDialog("Insira seu nome: "), indiceCadastro);
            if (sinal == false) {
                //output em modo grafico - mensagem de erro
                JOptionPane.showMessageDialog(null, "O nome e invalido, tente novamente!");
            }
        }
        sinal = false;
        
        //entrada em modo grafico - recebe, confere e armazena o cpf do Aluno
        while (sinal == false){
            sinal = arm.insereCPF(JOptionPane.showInputDialog("Insira seu cpf: "), indiceCadastro);
            if (sinal == false) {
                //output em modo grafico - mensagem de erro
                JOptionPane.showMessageDialog(null, "Cpf invalido, tente novamente!");
            }
        }
        sinal = false;
        
        //entrada em modo grafico - recebe, confere e armazena a data de nascimento do Aluno
        while (sinal == false) {
            sinal = arm.insereDataDeNascimento(JOptionPane.showInputDialog(
                    "Insira sua data de nascimento(DDMMAAAA): "), indiceCadastro);
            if (sinal == false) {
                //output em modo grafico - mensagem de erro
                JOptionPane.showMessageDialog(null, "Data invalida, tente novamente!");
            }
        }
        sinal = false;
        
        //entrada em modo grafico - recebe, confere e armazena o RA do Aluno
        while (sinal == false) {
            sinal = arm.insereRA(JOptionPane.showInputDialog("Insira seu RA: "), indiceCadastro);
            for (int i = 0; i < cadastrosDisponiveis.length; i++) {
                if (cadastrosDisponiveis[i] == 1) {
                    try {
                        raEncontrado = compararRA(arm.retornaRA(indiceCadastro), i);
                        if(raEncontrado) {
                            JOptionPane.showMessageDialog(null, "RA ja cadastrado! Tente novamente!");
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
                    JOptionPane.showMessageDialog(null, "RA invalido, tente novamente!");
                }
            }
            flipflop = false;
        }
        sinal = false;
    
        //entrada em modo grafico - nome do curso, disciplinas e notas
        while(sinal == false) {
            while (sinal == false) {
                //entrada em modo grafico - nome do curso para as disciplinas
                sinal = arm.insereCurso(JOptionPane.showInputDialog("Insira o nome do curso:"), indiceCadastro);
                
                if (sinal == false) {
                    //output em modo grafico - mensagem de erro
                    JOptionPane.showMessageDialog(null, "Nome de curso inválido!"); 
                }
            }
            String numero;
            sinal = false;
            while (sinal == false) {
                //entrada em modo grafico - quantidade de disciplinas que o Aluno deseja inserir
                numero = JOptionPane.showInputDialog("Quantas disciplinas você deseja inserir? ");
                
                //valida a quantidade de disciplinas
                sinal = arm.validaQtdDisciplinas(numero);
                if (sinal == true) {
                    
                    //passa a string qtd para integer
                    nDisciplinas = Integer.parseInt(numero);
                    
                    if (0 < nDisciplinas && nDisciplinas < 9) {
                        sinal = true;
                    }else {
                        sinal = false;
                        JOptionPane.showMessageDialog(null, "Numero invalido!"); //output em modo grafico - mensagem de erro
                    }
                }
            }
                    
        //cadastra a quantidade de disciplinas desejada e suas respectivas notas
        for (int i = 0; i < nDisciplinas; i++) {
            cadastrarDisciplinas(i, indiceCadastro); //chamada de metodo para cadastrar o nome da disciplina
            do {
                sinal = cadastrarNotas(i, indiceCadastro); //chamada de metodo para cadastrar a nota da disciplina
            }while (sinal == false);
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
            sinal = arm.insereDisciplina(JOptionPane.showInputDialog("Insira o nome da Disciplina " + (indiceDisciplina + 1) + ":  "), indiceDisciplina, indiceCadastro); 
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
            sinal = arm.insereNota(JOptionPane.showInputDialog("Insira a nota da Disciplina " + (indiceDisciplina + 1) + ":  "), indiceDisciplina, indiceCadastro);
            aux = arm.retornaNota(indiceDisciplina, indiceCadastro);
            if(aux >= 0.0 && aux <= 10.0) {
                sinal = true;
            }else {
                sinal = false;
                JOptionPane.showMessageDialog(null, "Nota invalida! Insira uma nota entre 0 e 10!");
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
                        JOptionPane.showMessageDialog(null, "Cadastro Removido com sucesso!"); //output em modo grafico - mensagem de exito
                        nenhumCadastro = false;
                    }
                }
            }
            if(nenhumCadastro) {
                JOptionPane.showMessageDialog(null, "Nao existem cadastros com esse RA!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nao existem cadastros de aluno para remover");
        }
    }
    
    /**
     * remover - remove o cadastro de acordo com o indice do aluno
     */
    public void removerAntigo() {
        int i = entradaIndice(); //chamada de metodo que verifica o indice do aluno
        if (cadastrosDisponiveis[i] == 0) {
            //output em modo grafico - mensagem de erro
            JOptionPane.showMessageDialog(null, "Nao existem cadastros nesse indice!");
        }else {
            if (numCadastros == 0) {
                //output em modo grafico - mensagem de erro
                JOptionPane.showMessageDialog(null, "Nao existem cadastros para remover!");
            }else {
                arm.limparAluno(i); //chamada do metodo que apaga os dados do aluno
                cadastrosDisponiveis[i] = 0; //atualiza o valor para "0" no array cadastrosDisponiveis[]
                numCadastros--; //atualiza o numero atual de cadastros
                JOptionPane.showMessageDialog(null, "Cadastro Removido com sucesso!"); //output em modo grafico - mensagem de exito
            }
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
     * listar - imprime de maneira grafica a ficha dos alunos com os parametros desejados
     * 
     */
    public void listarAntigo() {
        String tempNomes [] = new String [5];
        String tempDisciplinas[] = new String [8];
        double tempNotas[] = new double [8];
        int aux = 0;
        for(int x = 0; x < cadastrosDisponiveis.length; x += 5) {
            for (int k = 0; k < 5; k++) {
                tempNomes[k] = getNomesListar(aux); //chamada do metodo que imprime os nomes dos alunos
                aux++;
            }
            
            //output em modo grafico - indice de cadastro e nome do aluno 
            JOptionPane.showMessageDialog(null, "Cadastro " + (x + 0) + ": " + tempNomes[0] + "\n" +
                    "Cadastro " + (x + 1) + ": " + tempNomes[1] + "\n" +
                    "Cadastro " + (x + 2) + ": " + tempNomes[2] + "\n" +
                    "Cadastro " + (x + 3) + ": " + tempNomes[3] + "\n" +
                    "Cadastro " + (x + 4) + ": " + tempNomes[4] + "\nUse 999 para listar todos!");
        }
        
        int i = entradaIndice(); //chamada de metodo que verifica o indice do aluno
        
        //armazena o nome das disciplinas e das notas nos arrays tempDisciplinas e tempNotas
        for (int m = 0; m < 8; m++) {
            tempDisciplinas[m] = getDisciplinasListar(i,m); 
            if(tempDisciplinas[m].equals("Disciplina não cadastrada")) {
                tempNotas[m] = 0.0;
            } else tempNotas[m] = getNotasListar(i,m);
        }
        
        if (i == 999) {
            for (int j = 0; j < cadastrosDisponiveis.length; j++) {
                if (cadastrosDisponiveis[j] == 1) {
                    imprimirCadastro(j); //se o cadastro existir ele é impresso por meio do metodo imprimirCadastro
                    
                    //armazena o nome das disciplinas e das notas nos arrays tempDisciplinas e tempNotas
                    for (int m = 0; m < 8; m++) {
                        tempDisciplinas[m] = getDisciplinasListar(j,m);
                        if(tempDisciplinas[m].equals("Disciplina não cadastrada")) {
                            tempNotas[m] = 0.0;
                        } else tempNotas[m] = getNotasListar(j,m);
                        
                    }
                    imprimirDisciplinas(tempDisciplinas); //chamada do metodo que imprime o nome das disciplinas
                    imprimirNotas(tempNotas); //chamada do metodo que imprime a nota das disciplinas
                }
            }
        }else if (cadastrosDisponiveis[i] == 0) {
            //output em modo grafico - mensagem de erro
            JOptionPane.showMessageDialog(null, "Nao existem cadastros nesse indice!");
        }else {
            imprimirCadastro(i); //chamada do metodo que imprime o cadastro 
            imprimirDisciplinas(tempDisciplinas); //chamada do metodo que imprime o nome das disciplinas
            imprimirNotas(tempNotas); //chamada do metodo que imprime a nota das disciplinas
        }
    }
    
    /**
     * imprimirCadastro - imprime as informações do cadastro dos alunos
     * 
     * @param i - int, posicao do Aluno no array de Alunos (cAlunos[])
     * 
     */
    private void imprimirCadastro(int i) {
        JOptionPane.showMessageDialog(null,
            "O nome cadastrado e: " + arm.retornaNome(i) +
            "\nO cpf cadastrado e: " + arm.retornaCPF(i) + 
            "\nA data de nascimento e: " + arm.retornaDataDeNascimento(i) +
            "\nA sua idade e: " + arm.retornaIdade(i) +
            "\nO RA esta cadastrado como: " + arm.retornaRA(i));
        
            
    }
    
    /**
     * imprimirDisciplinas - imprime o nome das disciplinas do aluno
     * 
     * @param i - int, posicao do Aluno no array de Alunos (cAlunos[])
     * @param tempDisciplinas[] - String, array dos nomes das disciplinas
     * 
     */
    private void imprimirDisciplinas(String tempDisciplinas[]) {
        JOptionPane.showMessageDialog(null, "Disciplina 0: " + tempDisciplinas[0] + "\n" +
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
        JOptionPane.showMessageDialog(null, "Disciplina 0: " + tempNotas[0] + "\n" +
                "Disciplina 1: " + tempNotas[1] + "\n" +
                "Disciplina 2: " + tempNotas[2] + "\n" +
                "Disciplina 3: " + tempNotas[3] + "\n" +
                "Disciplina 4: " + tempNotas[4] + "\n" +
                "Disciplina 5: " + tempNotas[5] + "\n" +
                "Disciplina 6: " + tempNotas[6] + "\n" +
                "Disciplina 7: " + tempNotas[7] + "\n" );
    
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
     * entradaIndice - verifica se o indice é uma opção válida
     * 
     * @return i, int indice
     */
    private int entradaIndice() {
        boolean bool = false;
        String temp;
        int i = -1;
        while (bool == false) {
            temp = JOptionPane.showInputDialog("Insira o indice desejado: ");
            try {
                i = Integer.parseInt(temp);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "O indice nao e valido!");
                i = -1;
            }
            if (i >= 0) {
                bool = true;
            }
        }
        return i;
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
            temp = JOptionPane.showInputDialog("Insira o RA desejado: ");
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
                JOptionPane.showMessageDialog(null, "O RA nao e valido!");
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
