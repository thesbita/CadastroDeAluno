
/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */


public interface IArmazenador { 

    public void adicionar(Object a);
    public Object remover(int i);
    public boolean estaVazia();
    public Object buscar (int i);
    public int getQtd();

}
