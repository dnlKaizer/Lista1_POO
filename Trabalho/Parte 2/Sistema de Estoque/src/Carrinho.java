public class Carrinho {
    private Item[] itens;
    private int nItens;

    private Carrinho(Item[] itens, int nItens) {
        this.itens = itens;
        this.nItens = nItens;
    }
    public Carrinho() {
        itens = new Item[10];
    }

    public int getNItens() {
        return nItens;
    }
    public float getPrecoTotal(int pos) {
        return itens[pos].getPrecoTotal();
    }

    public boolean addItem(Item item) {
        if (item == null) return false;
        // Verifica se o carrinho está cheio
        if (itens.length == nItens) ampliarCarrinho();
        int index = verificarItemCarrinho(item);
        if (index == -1) {
            itens[nItens] = item;
            nItens++;
        } else {
            itens[index].merge(item);
        }
        return true;
    }

    public Item buscarItem(int pos) {
        if (pos >= 0 && pos < this.nItens) return itens[pos].copy();
        else return null;
    }

    // Retorna a posição do item caso ele já exista, caso contrário retorna -1
    private int verificarItemCarrinho(Item item) {
        for (int i = 0; i < this.nItens; i++) {
            if (this.itens[i].getCdProduto() == item.getCdProduto()) {
                return i;
            }
        }
        return -1;
    }

    public Carrinho copy() {
        return new Carrinho(copyItens(), this.nItens);
    }
    private Item[] copyItens() {
        Item[] itens = new Item[this.nItens];
        for (int i = 0; i < this.nItens; i++) {
            itens[i] = this.itens[i].copy();
        }
        return itens;
    }

    @Override
    public String toString() {
        return String.format(
        "{ Itens: [%s] }", 
        this.arrayItensToString());
    }

    private void ampliarCarrinho() {
        Item[] itensAux = new Item[this.itens.length + 10];
        for (int i = 0; i < this.nItens; i++) {
            itensAux[i] = this.itens[i]; 
        }
        this.itens = itensAux;
    }

    private String arrayItensToString() {
        String str = "";
        for (int i = 0; i < nItens; i++) {
            str += "(" + itens[i] + ")";
            if (i != nItens - 1) str += ", ";
        }
        return str;
    }
}