
/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */


public class Utils {
    
    /**
     * checaStringNull - checa se a string é null
     * 
     * @return boolean - true: String null
     *                      false: String não null
     */
    public static boolean checaStringNull(String teste) {
        if (teste == null) {
            return true;
        }else return false;
    }
    
    /**
     * checaStringVazia - checa se a string é vazia
     * 
     * @return boolean - true: String vazia
     *                      false: String não vazia
     */
    public static boolean checaStringVazia(String teste) {
        if (teste.length() == 0) {
            return true;
        }else return false;
    }
    
    /**
     * checaRA - confere se o input do RA esta correto.
     * 
     *  @return boolean - true: RA correto
     *                    false : RA incorreto
     */
    public static boolean checaRA(String RA) {
        //checagem do tamanho do RA
                if (RA.length() != 10) {
                    return false;
                }
                
                //checa se o RA comeca com as letras "r" e "a"
                String subString1 = RA.substring(0,2);
                if (subString1.equals("ra") || subString1.equals("Ra") ||
                    subString1.equals("RA") || subString1.equals("rA")) {

                    int temp = 0;
                    String subString2 = RA.substring(2,RA.length());
                    
                    try {
                        //passagem dos numeros presentes na String RA para um int 
                        temp = Integer.parseInt(subString2);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                    
                    if (temp != 0) {
                        return true;
                    }else return false;
                    
                }else return false;
    }
    
    /**
     * formatarRA - formata o ra (colocando "ra" em maiusculo)
     * 
     * @return RA formatado, String de ra
     */
    public static String formatarRA(String ra) {
        
        //divisao do RA em parcelas menores(substrings) para formata-lo corretamente
        String subString1 = ra.substring(0,2);
        String subString2 = ra.substring(2,ra.length());
        subString1 = subString1.toUpperCase();
        ra = subString1 + subString2;
        return ra;
    }
}
