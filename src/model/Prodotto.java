package application;

public class Prodotto {
    private String nome;
    private String imageURL;

    public Prodotto(String nome, String imageURL) {
        this.nome = nome;
        this.imageURL = imageURL;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void getImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
    public String getImageURL() {
        return imageURL;
    }
}