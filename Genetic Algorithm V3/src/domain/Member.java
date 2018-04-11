package domain;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private List<Gene> dna;
    private int fitness;

    public Member(){
        dna = new ArrayList<>();
    }

    public Member(int amountOfGenes){
        dna = new ArrayList<>();
        generateDna(amountOfGenes);
    }

    public void generateDna(int amountOfGenes){
        for(int i = 0; i < amountOfGenes; i++){
            dna.add(new Gene());
        }
    }

    public String getSentence(){
        StringBuilder stringBuilder = new StringBuilder();
        dna.forEach(stringBuilder::append);
        String t = stringBuilder.toString();
        return t;
    }

    public void addPointToFitness(){
        fitness++;
    }

    public Gene getGeneAtIndex(int index){
        return dna.get(index);
    }

    public int getFitness() {
        return fitness;
    }

    public int sizeOfDnaHelix(){
        return dna.size();
    }

    public void addGene(Gene gene){
        dna.add(gene);
    }

    public List<Gene> getDna() {
        return dna;
    }

    public void setDna(List<Gene> dna) {
        this.dna = dna;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void clearGeneStatus(){
        for(Gene gene : dna){
            gene.setGeneStatus(Status.Default);
        }
    }

    public void replaceGene(Gene gene, int index){
        dna.set(index, gene);
    }
}
