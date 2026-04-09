// Cauã Santos Straub
// Henrique Horodenko Braga

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class TrackList {
    public static void main(String[] args) {
        ArrayList<Musica> listaMusicas = new ArrayList<>(); 
        boolean listaOrdenada = false; 
        Scanner ler = new Scanner(System.in);


        File arqMusicas = new File("musicas.txt");
        try (BufferedReader brMusicas = new BufferedReader(new FileReader(arqMusicas))) {
            String linha;
            while ((linha = brMusicas.readLine()) != null) {
                if (linha.trim().isEmpty()) continue; 
                
                String[] partes = linha.split(";");
                if (partes.length >= 4) { 
                    Musica m = new Musica(partes[0].trim(), partes[1].trim(), partes[2].trim(), Integer.parseInt(partes[3].trim()));
                    listaMusicas.add(m);
                }
            }
            System.out.println("Sucesso: " + listaMusicas.size() + " musicas carregadas!");
        } catch (Exception e) {
            System.out.println("ERRO: Não foi possível ler o arquivo.");
        }

        File arqAvaliacoes = new File("avaliacoes.txt");
        try (BufferedReader brAvaliacoes = new BufferedReader(new FileReader(arqAvaliacoes))) {
            String linha;
            int contAvaliacoes = 0;
            while ((linha = brAvaliacoes.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                
                String[] partes = linha.split(";");
                if (partes.length >= 4) {
                    String tituloAux = partes[0].trim();
                    boolean achou = false;
                    
                    for (Musica m : listaMusicas) {
                        if (m.getTitulo().equalsIgnoreCase(tituloAux)) {
                            m.addAvaliacao(new Avaliacao(partes[1].trim(), Double.parseDouble(partes[2].trim()), partes[3].trim()));
                            achou = true;
                            contAvaliacoes++;
                            break;
                        }
                    }
                    if (!achou) {
                        System.out.println("Aviso: Musica '" + tituloAux + "' não encontrada no catalogo para receber a avaliação.");
                    }
                }
            }
            System.out.println("Sucesso: " + contAvaliacoes + " avaliacoes carregadas!");
        } catch (Exception e) {
            System.out.println("ERRO: Não foi possível ler o arquivo.");
        }

        int opcao = 0;
        while (opcao != 9) { 
            System.out.println("=== MENU TRACKLIST ===");
            System.out.println("1. Cadastrar nova musica");
            System.out.println("2. Avaliar musica");
            System.out.println("3. Listar todas as musicas");
            System.out.println("4. Busca sequencial por titulo");
            System.out.println("5. Busca binaria por titulo");
            System.out.println("6. Filtrar por genero");
            System.out.println("7. Ranking das musicas mais bem avaliadas");
            System.out.println("8. Exibir media e avaliacoes de uma musica");
            System.out.println("9. Encerrar");
            System.out.println("10. Busca sequencial por artista ");
            System.out.print("Escolha: ");
            
            try {
                opcao = ler.nextInt();
                ler.nextLine(); 
            } catch (Exception e) {
                System.out.println("Por favor, digite um numero valido.");
                ler.nextLine(); 
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Titulo: ");
                    String tit = ler.nextLine();
                    System.out.print("Artista: ");
                    String art = ler.nextLine();
                    System.out.print("Genero: ");
                    String gen = ler.nextLine();
                    System.out.print("Ano: ");
                    int ano = ler.nextInt();
                    ler.nextLine(); 
                    listaMusicas.add(new Musica(tit, art, gen, ano));
                    listaOrdenada = false; 
                    System.out.println("Música cadastrada!");
                    break;

                case 2:
                    System.out.print("Qual a música que você quer avaliar? ");
                    String buscaTit = ler.nextLine();
                    Musica musicaParaAvaliar = null;
                    
                    for (Musica m : listaMusicas) {
                        if (m.getTitulo().equalsIgnoreCase(buscaTit)) {
                            musicaParaAvaliar = m;
                            break;
                        }
                    }

                    if (musicaParaAvaliar != null) {
                        System.out.print("Seu nome: ");
                        String nome = ler.nextLine();
                        System.out.print("Nota (0 a 10): ");
                        double nota = ler.nextDouble();
                        ler.nextLine(); 
                        while (nota < 0 || nota > 10) {
                            System.out.print("Nota inválida. Digite novamente: ");
                            nota = ler.nextDouble();
                            ler.nextLine();
                        }
                        System.out.print("Comentário: ");
                        String coment = ler.nextLine();

                        musicaParaAvaliar.addAvaliacao(new Avaliacao(nome, nota, coment));
                        System.out.println("Avaliação registrada com sucesso!");
                    } else {
                        System.out.println("Música não encontrada.");
                    }
                    break;

                case 3:
                    if (listaMusicas.isEmpty()) {
                        System.out.println("O catálogo está vazio! Nenhuma música para listar.");
                    } else {
                        for (Musica m : listaMusicas) {
                            System.out.println(m.toString()); 
                        }
                    }
                    break;

                case 4:
                    System.out.print("Digite o titulo para buscar (Sequencial): ");
                    String tituloSeq = ler.nextLine();
                    int comparacoesSeq = 0;
                    boolean achouSeq = false;

                    for (int i = 0; i < listaMusicas.size(); i++) {
                        comparacoesSeq++;
                        if (listaMusicas.get(i).getTitulo().equalsIgnoreCase(tituloSeq)) {
                            System.out.println("Música encontrada: " + listaMusicas.get(i).toString());
                            achouSeq = true;
                            break;
                        }
                    }
                    if (!achouSeq) System.out.println("Música não encontrada.");
                    System.out.println("Comparações realizadas: " + comparacoesSeq);
                    break;

                case 5:
                    if (!listaOrdenada) {
                        System.out.println("Aviso: A lista não está ordenada. Ordenando agora com Bubble Sort...");
                        for (int i = 0; i < listaMusicas.size() - 1; i++) {
                            for (int j = 0; j < listaMusicas.size() - i - 1; j++) {
                                if (listaMusicas.get(j).getTitulo().compareToIgnoreCase(listaMusicas.get(j + 1).getTitulo()) > 0) {
                                    Musica aux = listaMusicas.get(j);
                                    listaMusicas.set(j, listaMusicas.get(j + 1));
                                    listaMusicas.set(j + 1, aux);
                                }
                            }
                        }
                        listaOrdenada = true;
                    }

                    System.out.print("Digite o titulo para busca binária: ");
                    String tituloBin = ler.nextLine();
                    int comparacoesBin = 0;
                    int inicio = 0;
                    int fim = listaMusicas.size() - 1;
                    boolean achouBin = false;

                    while (inicio <= fim) {
                        comparacoesBin++;
                        int meio = (inicio + fim) / 2;
                        String titMeio = listaMusicas.get(meio).getTitulo();
                        
                        int comparacao = titMeio.compareToIgnoreCase(tituloBin);

                        if (comparacao == 0) {
                            System.out.println("Música encontrada: " + listaMusicas.get(meio).toString());
                            achouBin = true;
                            break;
                        } else if (comparacao < 0) {
                            inicio = meio + 1;
                        } else {
                            fim = meio - 1;
                        }
                    }
                    if (!achouBin) System.out.println("Música não encontrada.");
                    System.out.println("Comparações realizadas: " + comparacoesBin);
                    break;

                case 6:
                    System.out.print("Digite o genero musical para filtrar: ");
                    String genBusca = ler.nextLine();
                    boolean temGenero = false;
                    for (Musica m : listaMusicas) {
                        if (m.getGenero().equalsIgnoreCase(genBusca)) {
                            System.out.println(m.toString());
                            temGenero = true;
                        }
                    }
                    if(!temGenero) System.out.println("Nenhuma música desse gênero foi encontrada.");
                    break;

                case 7:
                    ArrayList<Musica> ranking = new ArrayList<>(listaMusicas);
                    Collections.sort(ranking, new Comparator<Musica>() {
                        public int compare(Musica m1, Musica m2) {
                            return Double.compare(m2.getMedia(), m1.getMedia()); 
                        }
                    });

                    int max = Math.min(10, ranking.size());
                    if (max < 10) {
                        System.out.println("Aviso: Há menos de 10 músicas no catálogo (total: " + max + ").");
                    }
                    System.out.println("--- RANKING: TOP " + max + " ---");
                    for (int i = 0; i < max; i++) {
                        System.out.println((i + 1) + "º Lugar: " + ranking.get(i).toString());
                    }
                    break;

                case 8:
                    System.out.print("Digite o titulo da música para ver as avaliações: ");
                    String titAval = ler.nextLine();
                    boolean achouParaAvaliacao = false;
                    for (Musica m : listaMusicas) {
                        if (m.getTitulo().equalsIgnoreCase(titAval)) {
                            System.out.println("Música: " + m.getTitulo() + " | Média: " + String.format("%.2f", m.getMedia()));
                            for (Avaliacao a : m.getAvaliacoes()) {
                                System.out.println(" -> " + a.getNomeUsuario() + " (Nota: " + a.getNota() + "): " + a.getComentario());
                            }
                            achouParaAvaliacao = true;
                            break;
                        }
                    }
                    if (!achouParaAvaliacao) System.out.println("Música não encontrada.");
                    break;

                case 9:
                    int totalAvaliacoes = 0;
                    double somaGeral = 0;
                    for(Musica m : listaMusicas) {
                        totalAvaliacoes += m.getAvaliacoes().size();
                        somaGeral += m.getMedia();
                    }
                    double mediaGeral = listaMusicas.isEmpty() ? 0 : somaGeral / listaMusicas.size();
                    
                    System.out.println("\n--- Estatísticas Finais ---");
                    System.out.println("Total de Músicas: " + listaMusicas.size());
                    System.out.println("Total de Avaliações: " + totalAvaliacoes);
                    System.out.println("Média Geral do Catálogo: " + String.format("%.2f", mediaGeral));
                    System.out.println("Encerrando o TrackList...");
                    break;

                case 10:
                    System.out.print("Digite o nome do artista para busca sequencial: ");
                    String artBusca = ler.nextLine();
                    boolean achouArtista = false;
                    for (Musica m : listaMusicas) {
                        if (m.getArtista().equalsIgnoreCase(artBusca)) { 
                            System.out.println(m.toString());
                            achouArtista = true;
                        }
                    }
                    if (!achouArtista) System.out.println("Nenhuma música desse artista encontrada.");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        ler.close();
    }
}
