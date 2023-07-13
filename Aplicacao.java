import javax.swing.JOptionPane;
import java.util.Scanner;
/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */

public class Aplicacao {
    public static void main(String[] args) {

        boolean cadastroScanner;
        int i = 0, numeroCadastro = 0;
        boolean sinal = false;
        while (!sinal) {

            //entrada em modo grafico - recebe o numero do cadastro
            String numeroString = JOptionPane.showInputDialog("Escolha um numero de cadastros: \n(Necessariamente multiplo de 5)");
            try {
                numeroCadastro = Integer.parseInt(numeroString); //passa o input (String) para int 

                //confere se o numero de cadastro recebido é valido
                if (numeroCadastro % 5 == 0) {
                    sinal = true;
                }
            } catch (Exception e) {
                //output em modo grafico - mensagem de erro
                JOptionPane.showMessageDialog(null, "Escolha um numero valido!");
            }
        }
        //instancia objeto do tipo CadastroAlunos
        CadastroAlunos cadastro = new CadastroAlunos(numeroCadastro); cadastroScanner = false;
        //        CadastroAlunosScanner cadastroS = new CadastroAlunosScanner(); cadastroScanner = true; Scanner input = new Scanner(System.in);
        sinal = false;

        if(cadastroScanner) {
            while(i == 0) {
                System.out.println("\n(I)nserir"
                    + "\n(R)emover"
                    + "\n(L)istar"
                    + "\n(S)air"
                    + "\nInsira a opcao desejada: ");
                //            sinal = cadastroS.setIndice(input.next());
                if (!sinal) {
                    //output em modo grafico - mensagem de erro
                    JOptionPane.showMessageDialog(null, "opção inválida, tente novamente!");
                }
            }
        }else {
            while(i == 0) {

                //entrada em modo grafico - recebe a opcao do menu selecionada
                sinal = cadastro.setIndice(JOptionPane.showInputDialog("\n(I)nserir"
                        + "\n(R)emover"
                        + "\n(L)istar"
                        + "\n(S)air"
                        + "\nInsira a opcao desejada: "));
                if(!sinal) {
                    //output em modo grafico - mensagem de erro
                    JOptionPane.showMessageDialog(null, "opção inválida, tente novamente!");
                }
            }
        }
        //        input.close();
    }
}

