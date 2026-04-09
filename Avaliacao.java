// Cauã Santos Straub
// Henrique Horodenko Braga

public class Avaliacao {
    private String nomeUsuario;
    private double nota; 
    private String comentario;

    public Avaliacao(String nomeUsuario, double nota, String comentario) {
        this.nomeUsuario = nomeUsuario;
        this.nota = nota;
        this.comentario = comentario;
    }

    public String getNomeUsuario() { 
        return nomeUsuario; 
    }
    public double getNota() { 
        return nota; 
    }
    public String getComentario() { 
        return comentario; 
    }

    @Override
    public String toString() {
        return nomeUsuario + " deu nota " + nota + " - " + comentario;
    }
}
