import javax.swing.JOptionPane;
/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */
public class EntraDadosG implements Interface {

    String aux=null; //definir string auxiliar para testes do try/catch e consequentes conversões de tipo
    public static int end = 0 ;
    /**
     * lerNome - entrada de nome (grafica)
     *
     * @return String, nome fornecido
     */

    public int lerQuantidadeAlunos() {
        int quantidade = 0;
        boolean verify = false;
        do{
            try {
                aux = JOptionPane.showInputDialog("Digite a quantidade total de alunos desejada");
                quantidade = Integer.parseInt(aux);
                if(quantidade<=0){
                JOptionPane.showMessageDialog(null,"Valor inválido");
                }else
                verify = true;

            } catch (NumberFormatException e) {                
                if (aux == null) { // Se o usuário clicou no botão cancelar
                    JOptionPane.showMessageDialog(null,"Operação Cancelada!");
                    System.exit(0);
                } else if (aux.trim().isEmpty()) { // Se o valor digitado está vazio ou contém apenas espaços em branco
                    JOptionPane.showMessageDialog(null,"A quantidade de alunos não pode estar vazia!");
                }else{
                    JOptionPane.showMessageDialog(null,"Opção invalida!");
                }
            }
        }while(!verify);

        return quantidade;
    }

    public int lerOpcao() {
        int opcao=0;
        boolean verify = false;
        while (!verify && end!=1){
            try {
                aux = JOptionPane.showInputDialog("Digite uma opção: \n1 – Inserir \n2 – Remover \n3 – Listar\n4 – Salvar\n5 – Load\n6 – SAIR");
                opcao = Integer.parseInt(aux);
                
                if(opcao<=0){
                JOptionPane.showMessageDialog(null,"Valor inválido");
                }else
                verify = true;
                
            } catch (NumberFormatException e ) {
                if (aux == null) { // Se o usuário clicou no botão cancelar
                    JOptionPane.showMessageDialog(null,"Operação Cancelada!");
                    System.exit(0);
                } else if (aux.trim().isEmpty()) { // Se o nome digitado está vazio ou contém apenas espaços em branco
                    JOptionPane.showMessageDialog(null,"ERRO: Valor vazio");
                }else{
                    JOptionPane.showMessageDialog(null,"Opção invalida!");
                }
            }
        }

        return(opcao);
    }

    public String lerNome() {
        String nome = null;
        boolean verify = false;

        while (!verify && end!=1){
            try {
                nome = JOptionPane.showInputDialog("Forneca o nome do aluno");
                if (nome == null) { // Se o usuário clicou no botão cancelar
                    JOptionPane.showMessageDialog(null,"Operação Cancelada!");
                    end = 1;
                } else if (nome.trim().isEmpty()) { // Se o nome digitado está vazio ou contém apenas espaços em branco
                    JOptionPane.showMessageDialog(null,"O nome do aluno não pode estar vazio!");
                } else {
                    verify = true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }

        return nome;
    }

    public String lerRA() {
        String ra = null;
        boolean verify = false;

        while (!verify && end!=1){
            try {
                ra = JOptionPane.showInputDialog("Forneca o ra do aluno");
                if (ra == null) { // Se o usuário clicou no botão cancelar
                    JOptionPane.showMessageDialog(null,"Operação Cancelada!");
                    end = 1;
                } else if (ra.trim().isEmpty()) { // Se o nome digitado está vazio ou contém apenas espaços em branco
                    JOptionPane.showMessageDialog(null,"O ra do aluno não pode estar vazio!");
                } else {
                    verify = true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }

        return ra;
    }

    public String lerRemover(){
        String raremov= null;
        boolean verify = false;
        while (!verify && end!=1){
            try{
                raremov = JOptionPane.showInputDialog("Insira o ra do aluno");
                if(raremov==null){ // Se o usuário clicou no botão cancelar
                    JOptionPane.showMessageDialog(null,"Operação Cancelada!");
                    end = 1;
                }else if(raremov.trim().isEmpty()){  // Se o valor digitado está vazio ou contém apenas espaços em branco
                    JOptionPane.showMessageDialog(null,"ERRO: Valor vazio");
                } else {
                    verify = true;
                }
            }catch(NumberFormatException e){
                if(raremov.trim().isEmpty())
                    JOptionPane.showMessageDialog(null,"Erro: " + e.getMessage());
            }
        }
        return raremov;
    } 

    /**
     * lerIdade - entrada de idade (grafica)
     *
     * @return int, idade fornecida
     */
    public int lerIdade(){ 
        int idade =0 ; 
        boolean verify=false;
        while (!verify && end!=1){
            try{
                aux = JOptionPane.showInputDialog("Forneca a idade do aluno: ");

                idade = Integer.parseInt(aux);
                if(idade<=0){
                JOptionPane.showMessageDialog(null,"Valor inválido");
                }else
                verify = true;
            }catch(NumberFormatException e){
                if(aux==null){ // Se o usuário clicou no botão cancelar
                    JOptionPane.showMessageDialog(null,"Operação Cancelada!");
                    end = 1;
                }else if(aux.trim().isEmpty()){  // Se o valor digitado está vazio ou contém apenas espaços em branco
                    JOptionPane.showMessageDialog(null,"ERRO: Valor vazio");
                }else{
                    JOptionPane.showMessageDialog(null,"Opção invalida!");
                }
            }
        }

        return idade;
    }

    /**
     * lerNota - entrada de nota (grafica)
     *
     * @return float, nota fornecida
     * 
     */

    public int lerQtdeDisciplinas(){ 
        int qtde = 0;
        boolean verify=false;
        while (!verify && end!=1){
            try{
                aux = JOptionPane.showInputDialog("Forneca a quantidade de disciplinas cursadas pelo aluno: ");

                qtde = Integer.parseInt(aux);
                if(qtde<=0){
                JOptionPane.showMessageDialog(null,"Valor inválido");
                }else
                verify = true;
            }catch(NumberFormatException e){
                if(aux==null){ // Se o usuário clicou no botão cancelar
                    JOptionPane.showMessageDialog(null,"Operação Cancelada!");
                    end = 1;
                }else if(aux.trim().isEmpty()){  // Se o valor digitado está vazio ou contém apenas espaços em branco
                    JOptionPane.showMessageDialog(null,"ERRO: Valor vazio");
                }else{
                    JOptionPane.showMessageDialog(null,"Opção invalida!");
                }
            }
        }

        return qtde;
    }

    public String lerDisciplina(){
        String disciplina = null;

        boolean verify = false;
        while (!verify && end!=1){
            try{
                disciplina= JOptionPane.showInputDialog("Insira o nome da disciplina");

                if(disciplina==null){ // Se o usuário clicou no botão cancelar
                    JOptionPane.showMessageDialog(null, "Operação Cancelada!");
                    end = 1;
                }else if(disciplina.trim().isEmpty()){  // Se o valor digitado está vazio ou contém apenas espaços em branco
                    JOptionPane.showMessageDialog(null,"ERRO: Valor vazio");
                } else {
                    verify = true;
                }
            }catch(NumberFormatException e){
                if(disciplina.trim().isEmpty())
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }

        return disciplina;
    } 

    public float lerNota(){ 
        float nota = -1;
        boolean verify = false;
        while (!verify && end!=1){
            try{
                aux = JOptionPane.showInputDialog("Forneca a nota do aluno: ");

                nota = Float.parseFloat(aux);
                if((nota <  0) || (nota>10)){
                    JOptionPane.showMessageDialog(null,"ERRO: Nota fora da condição 0<=nota<=10");                   
                }else{
                    verify = true;
                }
            }catch(Exception e){
                if (aux == null){ // Se o usuário clicou no botão cancelar
                    JOptionPane.showMessageDialog(null,"Foi cancelado!");
                    end = 1;
                }else if(aux.trim().isEmpty()){  // Se o valor digitado está vazio ou contém apenas espaços em branco
                    JOptionPane.showMessageDialog(null,"ERRO: Valor vazio");
                }else{
                    JOptionPane.showMessageDialog(null,"Opção invalida!");
                }
            }
        }

        return nota;
    }

    public int lerAno(){ 
        int ano = 0;
        boolean verify=false;
        while (!verify && end!=1){
            try{
                aux = JOptionPane.showInputDialog("Forneca a serie do aluno: ");
                ano=Integer.parseInt(aux);
                
                if(ano<=0){
                JOptionPane.showMessageDialog(null,"Valor inválido");
                }else
                verify = true;
                
            }catch(NumberFormatException e){
                if(aux==null){ // Se o usuário clicou no botão cancelar
                    JOptionPane.showMessageDialog(null,"Operação Cancelada!");
                    end = 1;
                }else if(aux.trim().isEmpty()){  // Se o valor digitado está vazio ou contém apenas espaços em branco
                    JOptionPane.showMessageDialog(null,"ERRO: Valor vazio");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Opção invalida!");
                }
            }
        }

        return ano;
    }
    
    public boolean inserirOK(){
        boolean ok = true;
        
        if(end==1)
        ok=false;

        end = 0;
        return ok;
    }
}


