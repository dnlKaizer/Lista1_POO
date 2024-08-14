public class Carrinho {
    private Item[] itens;
    private int nItens;

    public Carrinho() {
        itens = new Item[10];
    }
    private Carrinho(Carrinho car) {
        this.itens = car.copyItens();
        this.nItens = car.getNItens();
    }
    private Item[] copyItens() {
        Item[] itensAux = new Item[this.itens.length];
        for (int i = 0; i < this.nItens; i++) {
            itensAux[i] = this.itens[i].copy();
        }
        return itensAux;
    }

    public int getNItens() {
        return nItens;
    }
    public Item[] getItens() {
        return copyItens();
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

    public Item buscar(int pos) {
        if (pos >= 0 && pos < this.nItens) return itens[pos].copy();
        else return null;
    }

    // Retorna a posição do item caso ele já exista, caso contrário retorna -1
    private int verificarItemCarrinho(Item item) {
        for (int i = 0; i < this.nItens; i++) {
            if (this.itens[i].getProduto().getCodigo() == item.getProduto().getCodigo()) {
                return i;
            }
        }
        return -1;
    }

    private void ampliarCarrinho() {
        Item[] itensAux = new Item[this.itens.length + 10];
        for (int i = 0; i < this.nItens; i++) {
            itensAux[i] = this.itens[i]; 
        }
        this.itens = itensAux;
    }

    public Carrinho copy() {
        return new Carrinho(this);
    }

    @Override
    public String toString() {
        return String.format(
        "{ Itens: [%s] }", 
        this.arrayItensToString());
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