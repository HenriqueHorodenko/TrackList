// Cauã Santos Straub
// Henrique Horodenko Braga

import java.util.ArrayList;

public class Musica {
    private String titulo;
    private String artista;
    private String genero;
    private int anoLancamento;
    private ArrayList<Avaliacao> avaliacoes; 

    public Musica(String titulo, String artista, String genero, int anoLancamento) {
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.avaliacoes = new ArrayList<>();
    }

    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public String getGenero() { 
        return genero; 
    }
    public int getAnoLancamento() { 
        return anoLancamento; 
    }
    public ArrayList<Avaliacao> getAvaliacoes() { 
        return avaliacoes; 
    }

    public void addAvaliacao(Avaliacao a) {
        this.avaliacoes.add(a);
    }

    public double getMedia() {
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }
        double soma = 0;
        for (Avaliacao a : avaliacoes) {
            soma += a.getNota();
        }
        return soma / avaliacoes.size();
    }

    @Override
    public String toString() {
        return titulo + " (" + artista + ") - " + genero + " [" + anoLancamento + "] | Média: " + String.format("%.2f", getMedia());
    }
}
