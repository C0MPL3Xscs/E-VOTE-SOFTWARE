package E_Vote.Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import E_Vote.SecurityUtils.SecurityUtils;
import java.io.Serializable;

public class Eleicao implements Serializable {

    private ArrayList<Eleitores> listaEleitores;
    private ArrayList<Candidatos> listaCandidatos;
    private ArrayList<String> Admins = new ArrayList<>();
    boolean isOver;

    //Contrutor por defeito
    public Eleicao() {
        this.listaEleitores = new ArrayList<>();
        this.listaCandidatos = new ArrayList<>();
        Admins.add("271830409");
        Admins.add("1");
        Admins.add("Admin");
        this.isOver = false;
    }

    //Construtor para inicializar candidatos e eleitores
    public Eleicao(boolean load) {
        this();
        if (load) {
            loadData();
        }
        this.isOver = false;
        Admins.add("271830409");
    }

    public boolean isAdmin(String eleitor) {
        if (Admins.contains(eleitor)) {
            return true;
        }
        return false;
    }

    public Eleicao(ArrayList<Eleitores> listaEleitores, ArrayList<Candidatos> listaCandidatos) {
        this.listaEleitores = listaEleitores;
        this.listaCandidatos = listaCandidatos;
    }

    //Adiciona um eleitor
    public void addEleitor(Eleitores eleitor) {
        this.listaEleitores.add(eleitor);
    }

    //Adiciona um candidato
    public void addCandidato(Candidatos candidato) {
        this.listaCandidatos.add(candidato);
    }

    //Retorna a lista de eleitores
    public ArrayList<Eleitores> getListaEleitores() {
        return listaEleitores;
    }

    //Retorna a lista de eleitores
    public ArrayList<String> getListaNifs() {
        ArrayList<String> nifList = new ArrayList<>();

        for (Eleitores eleitor : listaEleitores) {
            nifList.add(eleitor.getNIF());
        }

        return nifList;
    }

    //Retorna a lista de eleitores
    public ArrayList<String> getListaNames() {
        ArrayList<String> nameList = new ArrayList<>();

        for (Candidatos candidato : listaCandidatos) {
            nameList.add(candidato.getName());
        }

        return nameList;
    }

    //Retorna a lista de candidatos
    public ArrayList<Candidatos> getListaCandidatos() {
        return listaCandidatos;
    }

    public boolean login(String NIF, String password) {
        try {
            //Validar password atravez da chave privada
            byte[] privData = Files.readAllBytes(Paths.get(NIF + ".priv"));
            privData = SecurityUtils.decrypt(privData, password);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void setOver() {
        this.isOver = true;
    }

    public boolean getIsOver() {
        return this.isOver;
    }

    //Regista um voto na blockchain
    public String RegisterVote(Eleitores eleitor, int candidateIndex) {
        try {
            Candidatos candidato = this.listaCandidatos.get(candidateIndex);
            if (!eleitor.getVoted()) {
                candidato.addVoto(eleitor);
                eleitor.setVoted();
                return "O voto foi registado.";
            } else {
                return "Eleitor não pode votar mais do que uma vez.";
            }
        } catch (Exception ex) {

        }
        return "Não está logado, faça login para poder votar";
    }

    //Retorna um eleitor tendo em conta o seu NIF
    public Eleitores getEleitor(String NIF) {
        for (int i = 0; i < this.listaEleitores.size(); i++) {
            if (this.listaEleitores.get(i).getNIF().equals(NIF)) {
                return this.listaEleitores.get(i);
            }
        }
        return null;
    }

    //Retorna um eleitor tendo em conta o seu NIF
    public Candidatos getCandidato(int Index) {
        try {
            return listaCandidatos.get(Index);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + Index);
        }
    }

    //Cria os candidatos e Eleitores tendo em conta os dados que estao nos ficheiros
    private void loadData() {
        String CfilePath = "Candidates.txt";
        /*
        String EfilePath = "Electors.txt";
        //Carrega os eleitores
        try (BufferedReader reader = new BufferedReader(new FileReader(EfilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ", 2);
                String ElectorPassword = parts[0];
                String ElectorName = parts[1];
                Eleitores Eleitor = new Eleitores(ElectorPassword, ElectorName);
                this.listaEleitores.add(Eleitor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //Carrega os candidatos
        try (BufferedReader reader = new BufferedReader(new FileReader(CfilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Candidatos candidato = new Candidatos(line);
                this.listaCandidatos.add(candidato);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
