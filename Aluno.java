/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */

public class Aluno extends Pessoa {
    
    //Atributos da classe Aluno 
    private String RA;
    private String NomeAutor;
    
    //instancia objeto do tipo Disciplina 
    Disciplina disciplina = new Disciplina();
    
    /**
     * getNomeAutor - retorna o valor de NomeAutor
     *
     * @return NomeAutor, String de NomeAutor
     */
    public String getNomeAutor() { 
        return NomeAutor;
    }
    
    /**
     * getRA - retorna o valor de RA
     *
     * @return RA, String de RA
     */
    public String getRA() {
        return RA;
    }
    
    /**
     * setRA - altera o valor de RA
     * 
     * @return boolean - true: valor alterado
     *                      false: valor nao alterado
     */
    public boolean setRA(String rA) {
        
        //checagem da string RA (null ou vazia)
        if (Utils.checaStringNull(rA)) {
            return false;
        }else if(Utils.checaStringVazia(rA)) {
            return false;
        }
        
        //checagem dos numeros do RA (correto ou nao)
        boolean bool = Utils.checaRA(rA);
        
        //formatacao do RA
        if(bool) {
            rA = Utils.formatarRA(rA); 
            RA = rA;
            return true;
        }else return false;
    }
    
    
    
    /**
     * limparAluno - Apaga os dados do Aluno
     * 
     */
    public void limparAluno() {
        super.limparPessoa(); //chamada do metodo limparPessoa na classe mae
        this.RA = null;
        this.NomeAutor = null;
        disciplina.limparDisciplina(); //chamada do metodo limparDisciplina na classe disciplina
    }
}
