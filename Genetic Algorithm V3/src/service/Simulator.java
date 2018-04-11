package service;

import domain.CoupleSelection;
import domain.Generation;

public class Simulator {
    private Boolean running;
    private ReproduceService reproduceService;
    private FitnessCalculatorService fitnessCalculatorService;
    private Generation generation;
    private String result;
    private int generationCounter;
    private CoupleSelection typeOfcoupleSelection;

    public Simulator() {
        running = false;
        generationCounter = 0;
    }

    public void startSimulation(){
        running = true;
        generation.generateInitialPopulation(result.length());
        fitnessCalculatorService.calculateBasicFitness(generation.getPopulation(), result);

        do{
            reproduceService.darwinSelection(typeOfcoupleSelection, generation.getPopulation(), result);
            generationCounter++;
            fitnessCalculatorService.calculateBasicFitness(generation.getPopulation(), result);
            reproduceService.addElites(generation.getPopulation());

            String best = fitnessCalculatorService.getBestMember(generation.getPopulation()).getSentence();

            if(best.equals(result)){
                running  = false;
            }

            System.out.printf("Generation %d: Best answer: %s\n", generationCounter, best);
        }while (running);
    }

    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public ReproduceService getReproduceService() {
        return reproduceService;
    }

    public void setReproduceService(ReproduceService reproduceService) {
        this.reproduceService = reproduceService;
    }

    public FitnessCalculatorService getFitnessCalculatorService() {
        return fitnessCalculatorService;
    }

    public void setFitnessCalculatorService(FitnessCalculatorService fitnessCalculatorService) {
        this.fitnessCalculatorService = fitnessCalculatorService;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setTypeOfCoupleSelection(CoupleSelection typeOfCoupleSelection) {
        this.typeOfcoupleSelection = typeOfCoupleSelection;
    }
}
