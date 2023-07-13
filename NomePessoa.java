
/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */

public class NomePessoa{
    
    Texto t;
    
    /**
     * NomePessoa Construtor para instanciar o objeto do tipo Texto
     *
     * @param txt - String, nome da pessoa
     */
    public NomePessoa(String txt) {
        t = new Texto(txt);
    }
    
    /**
     * nomeAutor - formata o nome da pessoa para o estilo bibliografico
     *
     * @return nome autor, String nome bibliografico
     */
    public String nomeAutor() {
        String NomeAutor ="";
        int tamanhoString = t.getQtdePalavras();
        String[] arr = t.getTxt().split(" ", tamanhoString);
        NomeAutor += arr [tamanhoString - 1] + ", ";
        for (int i  = tamanhoString - 1; i != 0; i--) {
            if (arr [i-1].length() > 2) {
                NomeAutor += arr [i-1].charAt(0) + ". ";
            }
        }
        return NomeAutor;
    }
    
}
