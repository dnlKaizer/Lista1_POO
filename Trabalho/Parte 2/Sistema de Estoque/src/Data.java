public class Data {
    int dia;
    int mes;
    int ano;
    String string;

    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    @Override
    public String toString() {
        return this.string;
    }
    public void setString(String string) {
        this.string = string;
    }

    private Data(int dia, int mes, int ano, String string) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.string = string;
    }
    public static Data getInstance(int dia, int mes, int ano) {
        if (ano < 1500 || mes < 1 || dia < 1) return null;
        if (mes > 12 || dia > 31) return null;
        if (mes == 2 && (dia > 29 || ((ano % 4) != 0 && dia > 28))) return null;
        if (dia > 30 && (mes == 2 || mes == 4 || mes == 7 || mes == 9 || mes == 11)) return null;

        String str = "";
        if (dia < 10) str += "0" + dia;
        else str += dia;
        str += "/";

        if (mes < 10) str += "0" + mes;
        else str += mes;
        str += "/";

        str += ano;

        return new Data(dia, mes, ano, str);
    }
    public static Data getInstance(String str) {
        String[] nums = str.split("/");
        if (nums.length != 3) return null;
        int dia = Integer.parseInt(nums[0]);
        int mes = Integer.parseInt(nums[1]);
        int ano = Integer.parseInt(nums[2]);
        return getInstance(dia, mes, ano);
    }

    private Data(Data data) {
        this.dia = data.getDia();
        this.mes = data.getMes();
        this.ano = data.getAno();
        this.string = data.toString();
    }

    public Data copy() {
        return new Data(this);
    }
}
