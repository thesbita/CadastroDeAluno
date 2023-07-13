
/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */
import java.util.ArrayList;
import java.io.Serializable;

public class ListaArray implements IArmazenador, Serializable {

    // Atributo para armazenagem: arrayList
    private ArrayList <Object> lista;

    
    /**
     * ListaArray Construtor
     *
     */
    public ListaArray(){
        setLista(new <Object>ArrayList());
    }

    /**
     * getLista
     * 
     * @return the vet, arrayList
     */
    private ArrayList <Object> getLista() {
        return lista;
    }

    /**
     * getQtd
     * 
     * @return the qtd, int
     */
    public int getQtd() {
        return lista.size();
    }

    /**
     * setLista
     * 
     * @param vet the vet to set
     */
    private void setLista(ArrayList <Object>lista) {
        this.lista = lista;
    }

    /**
     * adicionar
     *
     * @param obj, objeto a ser adicionado
     */
    public void adicionar(Object obj){
        lista.add(obj);
    }

    /**
     * remover
     *
     * @param i, int - indice do objeto a ser adicionado
     */
    public Object remover(int i) {
        Object ret = null;

        if (buscar(i) != null){
            ret = lista.get(i);
            lista.remove(i);
        }
        return ret;
    }

    /**
     * buscar
     *
     * @param i Um parÃ¢metro
     * @return objeto encontrado ou null (nao encontrado)
     */
    public Object buscar (int i){
        Object ret = null;
        if(!lista.isEmpty()&& (i >= 0 && i < getQtd())){
            ret = lista.get(i);
        }
        return ret;
    }

    /**
     * estaVazia
     *
     * @return tru lista vazia e false lista nao vazia
     */
    public boolean estaVazia(){
        return (lista.isEmpty());
    }

    /**
     * toString
     *
     * @return lista de objetos armazenados
     */
    public String toString(){
        String s = "\n";
        for (int i = 0; i < lista.size(); i++){
            s += lista.get(i).toString() + "\n";
        }
        s = s + "\n";
        return s;
    }
}