# 🎵 TrackList

Sistema em Java para gerenciamento de músicas e avaliações, com funcionalidades de cadastro, busca, filtragem e ranking.

## 📌 Objetivo

O projeto simula um catálogo de músicas onde usuários podem:

- Cadastrar músicas
- Avaliar músicas com nota e comentário
- Buscar músicas (sequencial e binária)
- Filtrar por gênero ou artista
- Visualizar ranking das mais bem avaliadas

---

## 🧠 Estrutura do Projeto

- `TrackList.java` → Classe principal (menu e controle do sistema)
- `Musica.java` → Representa uma música
- `Avaliacao.java` → Representa uma avaliação de usuário
- `musicas.txt` → Base inicial de músicas
- `avaliacoes.txt` → Base inicial de avaliações

---

## ⚙️ Funcionalidades

### 📥 Carregamento de dados
- Lê arquivos `.txt`
- Associa avaliações às músicas pelo título

### 🔎 Buscas
- **Busca sequencial por título**
- **Busca binária por título**
  - (Ordena automaticamente usando Bubble Sort antes)

### 🎯 Filtros
- Por gênero
- Por artista

### ⭐ Avaliações
- Nota de 0 a 10
- Comentário textual
- Cálculo de média por música

### 🏆 Ranking
- Top 10 músicas com melhor média

---

## 📊 Estruturas utilizadas

- `ArrayList` → armazenamento dinâmico
- `BufferedReader` → leitura de arquivos
- `Comparator` + `Collections.sort()` → ordenação
- Algoritmos:
  - Busca sequencial
  - Busca binária
  - Bubble Sort

---

## 📚 Aviso

Este projeto foi desenvolvido como parte de um trabalho acadêmico para a faculdade, com fins educacionais.

---

## ▶️ Como executar

### 1. Compilar
```bash
javac *.java
```

### 2. Executar
```bash
java Main
```
