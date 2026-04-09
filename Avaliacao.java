// Cauã Santos Straub RA: 10734873
// Henrique Horodenko Braga RA: 10734878

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