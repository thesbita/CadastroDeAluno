/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */

public class Disciplina {

    //Atributos da classe Disciplina 
    private String curso;
    private String disciplinas [] = new String [8];
    private double notas [] = new double [8];
    private int numDisciplinas = 0;

    Texto t;
    
    /**
     * getDisciplinas - retorna o nome da disciplina 
     * 
     * @param i - int, valor da posicao no array
     *
     * @return disciplinas[i], String da disciplina na posicao i
     */
    public String getDisciplinas(int i) {
        return disciplinas[i];
    }
    
    /**
     * setDisciplina - altera o nome da Disciplina
     * 
     * @return boolean - true: nome alterado
     *                      false: nome nao alterado
     */
    public boolean setDisciplinas(int i, String disciplina) {
        
        //checagem da string disciplina (null ou vazia)
        if (Utils.checaStringNull(disciplina)) {
            return false;
        }else if(Utils.checaStringVazia(disciplina)) {
            return false;
        }
        
        //formatacao da String disciplina
        t = new Texto(disciplina);
        
        //atribuicao da String disciplina para a posicao correta no array
        disciplinas[i] = t.getTxt();
        
        //adicao do numero de disciplinas
        numDisciplinas++;
        
        return true;
    }
    
    /**
     * setNota - altera o valor da Nota
     * 
     * @return boolean - true: valor alterado
     *                      false: valor nao alterado
     */
    public boolean setNota(int i, double nota) {
            notas[i] = nota;
            return true;
    }
    
    /**
     * getNota - retorna o valor da Nota
     *
     *@param i - int, valor da posicao no array
     *
     *@return notas[i], double valor da nota na posicao i do array
     */
    public double getNota(int i) {
        return notas[i];
    }
    
    
    /**
     * getCurso - retorna a String curso
     *
     *@return curso, String que contem o nome do curso do aluno
     */
    public String getCurso() {
        return curso;
    }
    
    /**
     * setCurso - altera o valor do curso
     *
     *@return boolean - true: valor alterado
     *                    false: valor nao alterado
     */
    public boolean setCurso(String curso) {
        //checks para ver se esta dentro dos parâmetros
        if (Utils.checaStringNull(curso)) {
            return false;
        }else if(Utils.checaStringVazia(curso)) {
            return false;
        }
        
        //formatacao da String disciplina
        t = new Texto(curso);
        
        //atribuiçao da String curso para a variavel private curso
        this.curso = t.getTxt();
        return true;
    }

    /**
     * limparDisciplina - Apaga os dados dos atributos da classe Disciplina
     * 
     */
    public void limparDisciplina() {
        
        //apaga o valor das 8 disciplinas e das 8 notas
        for (int i = 0; i < 8; i++) {
            disciplinas[i] = null;
            notas[i] = 0.0;
            
        }
    }
}
