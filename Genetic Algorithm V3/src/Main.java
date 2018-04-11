import domain.CoupleSelection;
import domain.Generation;
import service.FitnessCalculatorService;
import service.ReproduceService;
import service.Simulator;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        double mutationRate = 0.01;
        int amountOfMembersInPopulation = 500;
        int amountOfElites = 5;
        String wordToSearch = "This is the beginning of the end humans, we will take over the world!";
        CoupleSelection coupleSelection = CoupleSelection.Tournament;



        Generation generation = new Generation(amountOfMembersInPopulation);

        FitnessCalculatorService fitnessCalculatorService = new FitnessCalculatorService();

        ReproduceService reproduceService = new ReproduceService(mutationRate);
        reproduceService.setFitnessCalculatorService(fitnessCalculatorService);
        reproduceService.setRandom(new Random());
        reproduceService.setAmountOfElites(amountOfElites);

        Simulator simulator = new Simulator();
        simulator.setTypeOfCoupleSelection(coupleSelection);
        simulator.setReproduceService(reproduceService);
        simulator.setFitnessCalculatorService(fitnessCalculatorService);
        simulator.setGeneration(generation);
        simulator.setResult(wordToSearch);
        simulator.startSimulation();
    }
}
