/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */

import java.util.InputMismatchException;

public class Armazenar {
    
    //Atributos da classe Armazena
    Aluno cAluno[];
    
    /**
     * Armazena Construtor para instanciar os objetos do tipo Aluno
     *
     * @param qtd - int, qtd de alunos que podem ser cadastrados
     * 
     */
    public Armazenar(int qtd) {
        cAluno = new Aluno[qtd];
        for (int i = 0; i < qtd; i++) {
            cAluno[i] = new Aluno();
        }
    }
    
    /**
     * insereNome - armazena o nome do Aluno no array de Alunos
     * 
     * @return boolean - true: valor alterado
     *                      false: valor nao alterado
     */
    public boolean insereNome(String nome, int indice) {
        if(cAluno[indice].setNome(nome)){
            return true;
        }else return false;
    }
    
    /**
     * insereCPF - armazena o CPF no array de Alunos
     * 
     * @return boolean - true: valor alterado
     *                      false: valor nao alterado
     */
    public boolean insereCPF(String cpf, int indice) {
        if(cAluno[indice].setCpf(cpf)){
            return true;
        }else return false;
    }
    
    /**
     * insereDataDeNascimento - armazena a data de nascimento no array de Alunos
     * 
     * @return boolean - true: valor alterado
     *                      false: valor nao alterado
     */
    public boolean insereDataDeNascimento(String dataDeNascimento, int indice) {
        if(cAluno[indice].setDataDeNascimento(dataDeNascimento)){
            return true;
        }else return false;
    }
    
    /**
     * insereRA - armazena o RA no array de Alunos
     * 
     * @return boolean - true: valor alterado
     *                      false: valor nao alterado
     */
    public boolean insereRA(String RA, int indice) {
        if(cAluno[indice].setRA(RA)){
            return true;
        }else return false;
    }
    
    /**
     * insereCurso - armazena o Curso na classe Disciplina
     * 
     * @return boolean - true: valor alterado
     *                      false: valor nao alterado
     */
    public boolean insereCurso(String curso, int indice) {
        if(cAluno[indice].disciplina.setCurso(curso)) {
            return true;
        }else return false;
    }
    
    /**
     * insereDisciplina - armazena o nome da Disciplina no array de Disciplinas de cada Aluno
     * 
     * @return boolean - true: valor alterado
     *                      false: valor nao alterado
     */
    public boolean insereDisciplina(String Disciplina, int indiceDisciplina, int indice) {
        if(cAluno[indice].disciplina.setDisciplinas(indiceDisciplina, Disciplina)) {
            return true;
        }else return false;
    }
    
    /**
     * insereNota - armazena a nota das disciplinas no array de notas de cada Aluno
     * 
     * @return boolean - true: valor alterado
     *                      false: valor nao alterado
     */
    public boolean insereNota(String notaString, int indiceDisciplina, int indice) {
        double notaDouble;
        
        //checagem da string RA (null ou vazia)
        if (Utils.checaStringNull(notaString)) {
            return false;
        }else if(Utils.checaStringVazia(notaString)) {
            return false;
        }
        try {
            
            //passando a String nota para double
            notaDouble = Double.parseDouble(notaString);
        }catch (InputMismatchException e) {
            return false;
        }
        
        if(cAluno[indice].disciplina.setNota(indiceDisciplina, notaDouble)) {
            return true;
        }else return false;
    }
    
    /**
     * limparAluno - chamada do metodo limparAluno da classe Aluno
     * 
     * @param indice - int, posicao do Aluno no array de Alunos (cAlunos[])
     */
    public void limparAluno(int indice) {
        cAluno[indice].limparAluno();
    }
    
    /**
     * retornaNome - retorna o nome do Aluno armazenado no array de alunos
     * 
     * @param indice - int, posicao do Aluno no array de Alunos (cAlunos[])
     * 
     * @return nome do aluno, String nome
     */
    public String retornaNome(int indice) {
        return cAluno[indice].getNome();
    }
    
    /**
     * retornaCPF - retorna o CPF do Aluno armazenado no array de alunos
     * 
     * @param indice - int, posicao do Aluno no array de Alunos (cAlunos[])
     * 
     * @return CPF do aluno, String CPF
     */
    public String retornaCPF(int indice) {
        return cAluno[indice].getCpf();
    }
    
    /**
     * retornaDataDeNascimento - retorna a data de nascimento do Aluno armazenado no array de alunos
     * 
     * @param indice - int, posicao do Aluno no array de Alunos (cAlunos[])
     * 
     * @return data de nascimento do aluno, String dataDeNascimento
     */
    public String retornaDataDeNascimento(int indice) {
        return cAluno[indice].getDataDeNascimento();
    }
    
    /**
     * retornaRA - retorna o RA do Aluno armazenado no array de alunos
     * 
     * @param indice - int, posicao do Aluno no array de Alunos (cAlunos[])
     * 
     * @return RA do aluno, String RA
     */
    public String retornaRA(int indice) {
        return cAluno[indice].getRA();
    }
    
    /**
     * retornaIdade - retorna a idade do Aluno armazenado no array de alunos
     * 
     * @param indice - int, posicao do Aluno no array de Alunos (cAlunos[])
     * 
     * @return idade do aluno, int idade
     */
    public int retornaIdade(int indice) {
        return cAluno[indice].getIdade();
    }
    /**
     * retornaCurso - retorna o nome do curso do Aluno armazenado no array de alunos
     * 
     * @param indice - int, posicao do Aluno no array de Alunos (cAlunos[])
     * 
     * @return nome da disciplina do aluno, String disciplina
     */
    public String retornaCurso(int indice) {
        return cAluno[indice].disciplina.getCurso();
    }
    
    /**
     * retornaDisciplina - retorna o nome da disciplina do Aluno armazenado no array de alunos
     * 
     * @param indice - int, posicao do Aluno no array de Alunos (cAlunos[])
     * @param indiceDisciplina - int, posicao da disciplina no array de Disciplinas
     * 
     * @return nome da disciplina do aluno, String disciplina
     */
    public String retornaDisciplina(int indice, int indiceDisciplina) {
        return cAluno[indice].disciplina.getDisciplinas(indiceDisciplina);
    }
    
    /**
     * retornaNota - retorna a nota da disciplina do Aluno armazenado no array de alunos
     * 
     * @param indice - int, posicao do Aluno no array de Alunos (cAlunos[])
     * @param indiceDisciplina - int, posicao da disciplina no array de Disciplinas
     * 
     * @return nota da disciplina do aluno, String notas
     */
    public Double retornaNota(int indice, int indiceDisciplina) {
        return cAluno[indice].disciplina.getNota(indiceDisciplina);
    }
    
    /**
     * validaQtdDisciplinas - confere se a String qtd é valida
     * 
     * @return boolean - true: String valida
     *                      false: String invalida
     */
    public boolean validaQtdDisciplinas(String qtd) {
        
        //checagem da string qtd (null ou vazia)
        if (Utils.checaStringNull(qtd)) {
            return false;
        }else if(Utils.checaStringVazia(qtd)) {
            return false;
        }
        
        try {
            
            //passa a String qtd para integer
            int nDisciplinas = Integer.parseInt(qtd); 
        }catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    /**
     * confereQtdDisciplinas - confere se a quantidade de disciplinas é valida
     * 
     * @return boolean - true: quantidade de disciplinas valida
     *                      false: quantidade de disciplinas invalida
     */
    public boolean confereQtdDisciplinas(int qtd) {
        
        if(0 < qtd && qtd < 9) {
            return true;
        }else {
            return false;
        }
    }
}
