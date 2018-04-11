package service;

import domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ReproduceService {
    private FitnessCalculatorService fitnessCalculatorService;
    private Generation darwinGeneration;
    private double mutationRate;
    private int amountOfElites;
    private Random random;

    public ReproduceService(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public void darwinSelection(CoupleSelection typeOfSelection, List<Member> population, String result){
        Member mother = null;
        Member father = null;
        Member child = null;

        for(int i = 0; i < population.size(); i++){
            if(typeOfSelection == CoupleSelection.Tournament){
                mother = tournamentSelection(population, result);
                father = tournamentSelection(population, result);
            }
            else if(typeOfSelection == CoupleSelection.StochasticUniversalSampling){
                stochasticUniversalSampling(population);
                mother = tournamentSelectionSUS();
                father = tournamentSelectionSUS();
            }
            else{
                throw new RuntimeException("Type of coupleselection not implemented!");
            }
            child = pair(mother, father);
            simulateMutation(child);
            population.set(i, child);
        }
    }

    private void simulateMutation(Member member) {
        for(int i = 0; i < member.sizeOfDnaHelix(); i++){
            if(Math.random() <= mutationRate){
                member.replaceGene(new Gene(), i);
            }
        }
    }

    public Member pair(Member mother, Member father){
        int size = mother.sizeOfDnaHelix();
        Member child = new Member();
        int randomMiddle = random.nextInt(size);

        for(int i = 0; i < size; i++){
            if (i > randomMiddle) {
                child.addGene(mother.getGeneAtIndex(i));
            } else {
                child.addGene(father.getGeneAtIndex(i));
            }
        }
        simulateMutation(child);
        return child;
    }

    private void stochasticUniversalSampling(List<Member> population){
        darwinGeneration = new Generation(population.size());
        int amountOfMembers = population.size();
        double totalFitnessOfPopultion = 0;

        for(Member member : population){
            totalFitnessOfPopultion+= member.getFitness();
        }

        double pointerDistance = totalFitnessOfPopultion / amountOfMembers;

        double start = Math.random() * pointerDistance;

        int index = 0;
        double sum = population.get(index).getFitness();

        for(int i = 0; i < amountOfMembers; i++){
            double pointer = start + i * pointerDistance;

            if(sum >= pointer){
                darwinGeneration.addMember(population.get(index));
            }
            else{
                for(++index; index < population.size(); index++){
                    sum += population.get(index).getFitness();
                    if(sum >= pointer){
                        darwinGeneration.addMember(population.get(index));
                        break;
                    }
                }
            }
        }
    }

    private Member tournamentSelection(List<Member> population, String result){
        darwinGeneration = new Generation(population.size());

        for(int i = 0; i < population.size(); i++){
            int random = (int) (Math.random() * population.size());
            darwinGeneration.addMember(population.get(random));
        }

        Member fittestMember = fitnessCalculatorService.getBestMember(darwinGeneration.getPopulation());
        return fittestMember;
    }

    private Member tournamentSelectionSUS(){
        return fitnessCalculatorService.getBestMember(darwinGeneration.getPopulation());
    }

    public void addElites(List<Member> population){
        List<Member> elites = findElites(population);
        for(int i = 0; i < amountOfElites; i++){
            int random = (int) (Math.random() * population.size());
            population.set(random, elites.get(i));
        }
    }

    private List<Member> findElites(List<Member> population){
        int count = 0;
        List<Member> elites = new ArrayList<>();
        int bestFitness = fitnessCalculatorService.getBestMember(population).getFitness();

        while(elites.size() < amountOfElites){
            int value = bestFitness + count;
            int limitValue = amountOfElites - elites.size();
            elites.addAll(population.stream().filter(m -> m.getFitness() == value).limit(limitValue).collect(Collectors.toList()));
            count--;
        }
        return elites;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public FitnessCalculatorService getFitnessCalculatorService() {
        return fitnessCalculatorService;
    }

    public void setFitnessCalculatorService(FitnessCalculatorService fitnessCalculatorService) {
        this.fitnessCalculatorService = fitnessCalculatorService;
    }

    public void setAmountOfElites(int amountOfElites) {
        this.amountOfElites = amountOfElites;
    }
}
