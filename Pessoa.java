import java.util.InputMismatchException;
import java.util.Date;
/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */


public class Pessoa {
    
    //Atributos da classe Pessoa
    private String nome;
    private String cpf;
    private String dataDeNascimento;
    private int idade;
    
    NomePessoa np;
    
    //instancia objeto do tipo Date
    Date d = new Date();
    
    /**
     * getNome - retorna o nome da Pessoa
     *
     * @return nome, String do nome
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * setNome - altera o nome da Pessoa
     * 
     * @return boolean - true: valor alterado
     *                      false: valor nao alterado
     */
    public boolean setNome(String nome) {
        
        //checagem da string nome (null ou vazia)
        if (Utils.checaStringNull(nome)) {
            return false;
        }else if(Utils.checaStringVazia(nome)) {
            return false;
        }
        
        this.nome = nome;
        
        //instancia objeto do tipo NomePessoa, limpando os espaços excedentes do nome
        np = new NomePessoa(getNome());
        this.nome = np.t.getTxt();//formata o nome 
        return true;
    }

    /**
     * getCpf - retorna o cpf da Pessoa
     *
     * @return cpf, String do cpf
     */
    public String getCpf() {
        return cpf;
    }
    
    /**
     * setCpf - altera o cpf da Pessoa
     * 
     * @return boolean - true: valor alterado
     *                      false: valor nao alterado
     */
    public boolean setCpf(String cpf) {
        boolean sinal = false;
        
        //checagem da string cpf (null ou vazia)
        if (Utils.checaStringNull(cpf)) {
            return false;
        }else if(Utils.checaStringVazia(cpf)) {
            return false;
        }
        
        if (checaCpf(cpf)) {//checa se o cpf é valido
            cpf = formatarCpf(cpf); //chamada do metodo que formata o cpf
            this.cpf = cpf;
            sinal = true;
            return sinal;
        }else {
            return sinal;
        }
    }
    
    /**
     * getDataDeNascimento - retorna a data de nascimento da Pessoa
     *
     * @return data de nascimento, String da data de nascimento
     */
    public String getDataDeNascimento() {
        return dataDeNascimento;
    }
    
    /**
     * setDataDeNascimento - altera a setDataDeNascimento da Pessoa
     * 
     * @return boolean - true: valor alterado
     *                      false: valor nao alterado
     */
    public boolean setDataDeNascimento(String dataDeNascimento) {
        
        //checagem da string dataDeNascimento (null ou vazia)
        if (Utils.checaStringNull(dataDeNascimento)) {
            return false;
        }else if(Utils.checaStringVazia(dataDeNascimento)) {
            return false;
        }
        
        //confere se o tamanho da string é valido
        if (dataDeNascimento.length() != 8) {
            return false;
        }
        dataDeNascimento = formatarDataDeNascimento(dataDeNascimento); //chamada do metodo que formata a data de nascimento
        this.dataDeNascimento = dataDeNascimento;
        setIdade(); //chamada do metodo que calcula a idade da pessoa
        return true;
    }
    
    /**
     * setIdade - altera a idade da Pessoa
     */
    public void setIdade() {
        int id;
        id = calcularIdade(getDataDeNascimento()); //chamada do metodo que calcula a idade da pessoa
        this.idade = id;
    }
    
    /**
     * getIdade - retorna a idade da Pessoa
     *
     * @return idade, int valor da idade da pessoa
     */
    public int getIdade() {
        return idade;
    }
    
    /**
     * calcularIdade - calcula a idade baseado no tempo do sistema
     * 
     * @return idade, int da idade da pessoa
     */
    private int calcularIdade(String data) {
        
        String ano = data.substring(6, getDataDeNascimento().length()); // pega o ano da data de nascimento
        String mes = data.substring(3,5); // pega o mes da data de nascimento
        String dia = data.substring(0,2); // pega o dia da data de nascimento
        
        int temp = d.getYear();
        int aux2 = 1 + d.getMonth(); //comandos da biblioteca Date para pegar o tempo atual do sistema
        int aux3 = d.getDate();
        temp += 1900; // necessario pois na biblioteca o ano necessariamente recebe + 1900 para obter o ano atual
        
        int anos = Integer.parseInt(ano);
        int meses = Integer.parseInt(mes);
        int dias = Integer.parseInt(dia);
        
        if (aux2 < meses) {
            anos++;
        }
        if (aux2 == meses) {
            if (dias > aux3) {
                anos++;
            }
        }
        anos = temp - anos;
        return anos;
    }
    
    /**
     * checaCpf - checa se o cpf é válido
     * 
     * @return boolean - true: cpf valido
     *                      false: cpf invalido
     */
    private boolean checaCpf(String cpf) {
        
        // verifica se os numeros nao se repetem e se a string tem o tamanho correto
        if (cpf.equals("11111111111") || cpf.equals("22222222222") ||
            cpf.equals("33333333333") || cpf.equals("44444444444") ||
            cpf.equals("55555555555") || cpf.equals("66666666666") ||
            cpf.equals("77777777777") || cpf.equals("88888888888") ||
            cpf.equals("99999999999") || cpf.length() != 11) {
            
            return false;
        }
        
        char digito10, digito11;
        int soma, i, j, numero, peso;
        
        try{
            // transforma e calcula o primeiro digito do cpf
            soma = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                numero = (int) (cpf.charAt(i) - 48);
                soma = soma + (numero * peso);
                peso--;
            }
            
            j = 11 - (soma % 11);
            if ((j == 10) || (j == 11)) {
                digito10 = '0';
            }else {
                digito10 = (char)(j + 48);
            }
            
            //transforma e calcula o segundo digito do cpf
            soma = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                numero = (int) (cpf.charAt(i) - 48);
                soma = soma + (numero * peso);
                peso--;
            }
            
            j = 11 - (soma % 11);
            if ((j == 10) || (j == 11)) {
                digito11 = '0';
            }else {
                digito11 = (char)(j + 48);
            }
            
            if ((digito10 == cpf.charAt(9)) && (digito11 == cpf.charAt(10))) {
                return true;
            }else {
                return false;
            }
            
        }catch (InputMismatchException e) {
            return false;
        }
    }
    
    /**
     * formatarCpf - formata o cpf: xxx.xxx.xxx-xx
     * 
     * @return cpf formatado, String do cpf
     */
    private String formatarCpf(String cpf) {
        String subBloco1 = cpf.substring(0, 3);
        String subBloco2 = cpf.substring(3, 6);
        String subBloco3 = cpf.substring(6, 9);
        String subBloco4 = cpf.substring(9, 11);
        cpf = subBloco1 + "." + subBloco2 + "." + subBloco3 + "-" + subBloco4;
        return cpf; 
    }
    
    /**
     * formatarDataDeNascimento - formata a data de nascimento no formato padrao xx/xx/xxxx
     * 
     * @return data de nascimento formatada, String da data de nascimento
     */
    private String formatarDataDeNascimento(String dtn) {
        String subBloco1 = dtn.substring(0,2);
        String subBloco2 = dtn.substring(2,4);
        String subBloco3 = dtn.substring(4,dtn.length());
        dtn = subBloco1 + "/" + subBloco2 + "/" + subBloco3;
        return dtn;
    }
    
    /**
     * limparPessoa - limpa os campos para eliminar um cadastro
     */
    protected void limparPessoa() {
        this.nome = null;
        this.cpf  = null;
        this.dataDeNascimento = null;
        this.idade = 0;
    }
    
}
