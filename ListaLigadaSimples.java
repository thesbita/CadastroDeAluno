
/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */

import java.io.Serializable;
/*
 * Autor: Emanuelle Ribeiro Thesbita Silva
 * Versão: 1.1
 * Data 2023/05/23
 */
public class ListaLigadaSimples implements IArmazenador, Serializable {
    No inicio, fim;
    int qtdNos;

    /**
     * ListaLigadaSimples Construtor
     *
     */
    public ListaLigadaSimples(){
        setInicio(null);
        setFim(null);
        setQtdNos(0);
    }

    /**
     * setInicio
     *
     * @param inicio No inicio da lista ligada
     */
    private void setInicio(No inicio){
        this.inicio = inicio;
    }

    /**
     * getInicio
     *
     * @return No de inicio da lista ligada
     */
    public No getInicio(){
        return(this.inicio);
    }

    /**
     * getFim
     *
     * @return No do final da lista ligada
     */
    public No getFim(){
        return(this.fim);
    }

    /**
     * setFim
     *
     * @param fim No fim da lista ligada
     */
    private void setFim(No fim){
        this.fim = fim;
    }

    /**
     * getQtd
     *
     * @return int, qtde de nos na lista ligada
     */
    public int getQtd(){
        return this.qtdNos;
    }

    /**
     * setQtdNos
     *
     * @param qtdNos Um parâmetro
     */
    private void setQtdNos(int qtdNos){
        this.qtdNos = qtdNos;
    }

    /**
     * estaVazia
     *
     * @return boolean, true se estiver vazia e false caso contrario
     */
    public boolean estaVazia(){
        boolean vazia = false; 
        if (getQtd() == 0 && getInicio() == null && getFim() == null){
            vazia = true;
        }
        return vazia;
    }

    /**
     * inserirInicio
     *
     * @param elem Objeto a ser inserido no inicio da lista ligada
     */
    public void inserirInicio(Object elem) {
        No novo = new No(elem); //1

        if (estaVazia()){
            setInicio(novo);
            setFim(novo);
        }
        else{
            novo.setProximo(inicio);
            setInicio(novo);
        }
        setQtdNos(getQtd() + 1);
    }

    public Object remover(int i){
        int k=0;
        No prox = getInicio();
        No ant=null;
        No aux = null;
        Object obj = null; 
        if (!estaVazia()){
            if(i==0){
                removerInicio();
            }else {            
                // percorre ate achar o no antes do fim
                while(prox!= fim && i!=k){
                    ant=prox; 
                    prox = prox.getProximo(); //percorremos a lista sempre com uma váriavel recebendo o termo anterior do atual, para que ao encontrarmos o item desejado, o penúltimo item aponte para o próximo do atual
                    k++;
                }
                if (prox == fim && i==k){ //se o item a ser removido for o último, é intercalada a função de remover o último da lista
                    removerFim();
                }

                else if(i==k){ //fazemos o penúltimo item apontar para o próximo do atual
                    ant.setProximo(prox.getProximo());
                    aux = getInicio();
                    setQtdNos(getQtd() - 1);
                    obj = aux.getConteudo();
                }    
            }
        }
        return obj;

    }

    /**
     * buscar - retorna o objeto da posicao i
     *
     * @param i posicao onde esta o objeto
     * @return null se nao achar e endereco do objeto se achar
     */
    public Object buscar (int i){
        Object ret = null;
        if(!estaVazia()){
            int k =0;
            No current = getInicio();
            No aux = getInicio();
            while(current!=null && k < i){ //buscar enquanto a lista não for vazia e enquanto não for encontrado o índice desejado
                current = current.getProximo();
                k++;
            }
            if(k==i){
                ret=current.getConteudo();
            }
        }
        return ret;
    }

    /**
     * adicionar
     *
     * @param elem Objeto a ser inserido no fim da lista ligada
     */
    public void adicionar(Object elem){
        No novo = new No(elem);
        if (estaVazia()){
            setInicio(novo);
            setFim(novo);
        }
        else{
            getFim().setProximo(novo);
            setFim(novo);
        }
        setQtdNos(getQtd() + 1);
    }

    /**
     * removerInicio
     *
     * @return Objeto removido
     */
    public Object removerInicio(){
        No aux = null;
        Object obj = null; 
        if(!estaVazia()){
            if ((getInicio() == getFim())){
                aux = getInicio();
                setInicio(null);
                setFim(null);
            } else {
                aux = getInicio();
                setInicio(aux.getProximo());
                aux.setProximo(null);
            }
            setQtdNos(getQtd() - 1);
            obj = aux.getConteudo();
        }

        return(obj);
    }

    /**
     * removerFim
     *
     * @return Objeto removido
     */
    public Object removerFim(){
        No ant = getInicio();
        No aux = null;
        Object obj = null; 
        if (!estaVazia()){
            if ((getInicio() == getFim())){
                aux = getInicio();
                setInicio(null);
                setFim(null);
            } else {            
                // percorre ate achar o no antes do fim
                while(ant.getProximo() != fim){
                    ant = ant.getProximo();
                }
                ant.setProximo(null);
                aux = fim;
                setFim(ant);
            }
            setQtdNos(getQtd() - 1);
            obj = aux.getConteudo();           
        }
        return obj;
    }
    
    
    /**
     * toString
     *
     * @return lista de objetos armazenados
     */
    public String toString(){
        String s = "\n";
        
        No current = getInicio();
        while(current!=null){ //buscar enquanto a lista não for vazia e enquanto não for encontrado o índice desejado
            s += current.getConteudo().toString() + "\n";   
            current = current.getProximo();
            
            }
      
        s = s + "\n";
        return s;
    }
    
}
