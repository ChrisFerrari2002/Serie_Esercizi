package ferrari_chris.serie04.prova;

public class Cat {
    private int eta;
    public String nome;

    public Cat(int eta, String nome) {
        this.eta = eta;
        this.nome = nome;
    }
    public Cat(String nome) {
        this.nome = nome;
    }

    private void meow(){
        System.out.println("Meow");
    }
    public int getEta() {
        return eta;
    }

    public String getNome() {
        return nome;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    private void prova(int a, String e){

    }
}
