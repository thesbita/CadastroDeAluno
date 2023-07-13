
import java.util.Scanner;
/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */
public class EntraDadosT implements Interface {
    Scanner scanner = new Scanner(System.in);
    static String aux=null; //definir string auxiliar para testes do try/catch e consequentes conversões de tipo
    int end =0;
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
                System.out.println("Digite a quantidade total de alunos desejada");
                aux = scanner.nextLine();

                quantidade = Integer.parseInt(aux);

                if(quantidade<=0){
                    System.out.println("Valor inválido");
                }else
                    verify = true;

            }catch (NumberFormatException e) {                
                if (aux == null) { // Se o usuário clicou no botão cancelar
                    System.out.println("Operação Cancelada!");
                } else if (aux.trim().isEmpty()) { // Se o valor digitado está vazio ou contém apenas espaços em branco
                    System.out.println("A quantidade de alunos não pode estar vazia!");
                }else{
                    System.out.println("Erro de formato do numero!");
                }
            }catch(Exception e){
                System.out.println("Outra excecao!");
            }
        } while (!verify);

        return quantidade;
    }

    public int lerOpcao() {
        int opcao=0;
        boolean verify = false;
        do{
            try {
                System.out.println("Digite uma opção: \n1 – Inserir \n2 – Remover \n3 – Listar\n4 – Salvar\n5 – Load\n6 – SAIR");
                aux = scanner.nextLine();

                opcao = Integer.parseInt(aux);

                verify = true;
            } catch (NumberFormatException e ) {
                if (aux == null) { // Se o usuário clicou no botão cancelar
                    System.out.println("Operação Cancelada!");
                } else if (aux.trim().isEmpty()) { // Se o valor digitado está vazio ou contém apenas espaços em branco
                    System.out.println("ERRO: Valor vazio");
                }else{
                    System.out.println("Erro de formato do numero!");
                }
            }
        } while (!verify);

        return(opcao);
    }

    public String lerNome() {
        String nome = null;
        boolean verify = false;

        do {
            try {
                System.out.println("Forneca o nome do aluno");
                nome = scanner.nextLine();
                if (nome == null) { // Se o usuário clicou no botão cancelar
                    System.out.println("Operação Cancelada!");
                } else if (nome.trim().isEmpty()) { // Se o valor digitado está vazio ou contém apenas espaços em branco
                    System.out.println("O nome do aluno não pode estar vazio!");
                } else {
                    verify = true;
                }
            } catch (Exception e) {
                System.out.println( "Erro: " + e.getMessage());
            }
        } while (!verify);

        return nome;
    }

    public String lerRA() {
        String ra = null;
        boolean verify = false;

        do {
            try {
                System.out.println("Forneca o ra do aluno");
                ra = scanner.nextLine();
                if (ra == null) { // Se o usuário clicou no botão cancelar
                    System.out.println("Operação Cancelada!");
                } else if (ra.trim().isEmpty()) { // Se o valor digitado está vazio ou contém apenas espaços em branco
                    System.out.println("O ra do aluno não pode estar vazio!");
                } else {
                    verify = true;
                }
            } catch (Exception e) {
                System.out.println( "Erro: " + e.getMessage());
            }
        } while (!verify);

        return ra;
    }

    public String lerRemover(){
        String raremov= null;
        boolean verify = false;
        do{
            try{
                System.out.println("Insira o ra do aluno");
                raremov = scanner.nextLine();
                if(raremov==null){ // Se o usuário clicou no botão cancelar
                    System.out.println("Operação Cancelada!");
                }else if(raremov.trim().isEmpty()){ // Se o valor digitado está vazio ou contém apenas espaços em branco
                    System.out.println("ERRO: Valor vazio");
                } else {
                    verify = true;
                }
            }catch(NumberFormatException e){
                if(raremov.trim().isEmpty())
                    System.out.println("Erro: " + e.getMessage());
            }
        }while(!verify);
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
        do{
            try{
                System.out.println("Forneca a idade do aluno: ");
                aux = scanner.nextLine();

                idade = Integer.parseInt(aux);
                if(idade<=0){
                    System.out.println("Valor inválido");
                }else
                    verify = true;
            }catch(NumberFormatException e){
                if(aux==null){ // Se o usuário clicou no botão cancelar
                    System.out.println("Operação Cancelada!");
                }else if(aux.trim().isEmpty()){ // Se o valor digitado está vazio ou contém apenas espaços em branco
                    System.out.println("ERRO: Valor vazio");
                }else{
                    System.out.println("Erro de formato do numero!");
                }
            }
        }while(!verify);

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
        do{
            try{
                System.out.println("Forneca a quantidade de disciplinas cursadas pelo aluno: ");
                aux = scanner.nextLine();

                qtde = Integer.parseInt(aux);
                if(qtde<=0){
                    System.out.println("Valor inválido");
                }else
                    verify = true;
            }catch(NumberFormatException e){
                if(aux==null){ // Se o usuário clicou no botão cancelar
                    System.out.println("Operação Cancelada!");
                }else if(aux.trim().isEmpty()){ // Se o valor digitado está vazio ou contém apenas espaços em branco
                    System.out.println("ERRO: Valor vazio");
                }else{
                    System.out.println("Erro de formato do numero!");
                }
            }
        }while(!verify);

        return qtde;
    }

    public String lerDisciplina(){
        String disciplina = null;

        boolean verify = false;
        do{
            try{
                System.out.println("Informe o nome do componente curricular");
                disciplina = scanner.nextLine();
                if(disciplina==null){ // Se o usuário clicou no botão cancelar
                    System.out.println( "Operação Cancelada!");
                }else if(disciplina.trim().isEmpty()){ // Se o valor digitado está vazio ou contém apenas espaços em branco
                    System.out.println("ERRO: Valor vazio");
                } else {
                    verify = true;
                }
            }catch(NumberFormatException e){
                if(disciplina.trim().isEmpty())
                    System.out.println( "Erro: " + e.getMessage());
            }
        }while(!verify);

        return disciplina;
    } 

    public float lerNota(){ 
        float nota = -1;
        boolean verify = false;
        do{
            try{
                System.out.println("Forneca a nota do aluno: ");
                aux = scanner.nextLine();

                nota = Float.parseFloat(aux);
                if((nota <  0) || (nota>10)){
                    System.out.println("ERRO: Nota fora da condição 0<=nota<=10");                   
                }else{
                    verify = true;
                }
            }catch(Exception e){
                if (aux == null){ // Se o usuário clicou no botão cancelar
                    System.out.println("Foi cancelado!");
                }else if(aux.trim().isEmpty()){ // Se o valor digitado está vazio ou contém apenas espaços em branco
                    System.out.println("ERRO: Valor vazio");
                }else{
                    System.out.println("Erro de formato do numero!");
                }
            }
        }while(!verify);

        return nota;
    }

    public int lerAno(){ 
        int ano = 0;
        boolean verify=false;
        do{
            try{
                System.out.println("Forneca a serie do aluno: ");
                aux = scanner.nextLine();
                ano=Integer.parseInt(aux);
                
                if(ano<=0){
                System.out.println("Valor inválido");
                }else
                verify = true;

            }catch(NumberFormatException e){
                if(aux==null){ // Se o usuário clicou no botão cancelar
                    System.out.println("Operação Cancelada!");
                }else if(aux.trim().isEmpty()){ // Se o valor digitado está vazio ou contém apenas espaços em branco
                    System.out.println("ERRO: Valor vazio");
                }
                else{
                    System.out.println("Erro de formato do numero!");
                }
            }
        }while(!verify);

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

