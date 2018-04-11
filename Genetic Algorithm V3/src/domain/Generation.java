package domain;

import java.util.ArrayList;
import java.util.List;

public class Generation {
    private List<Member> population;
    private int amountOfMembers;

    public Generation(int amountOfMembers){
        this.amountOfMembers = amountOfMembers;
        population = new ArrayList<>();
    }

    public void generateInitialPopulation(int dnaHelixLength){
        for(int i = 0; i < amountOfMembers; i++){
            population.add(new Member(dnaHelixLength));
        }
    }

    public List<Member> getPopulation() {
        return population;
    }

    public void setPopulation(List<Member> population) {
        this.population = population;
    }

    public int amountOfMembersInPopulation(){
        return population.size();
    }

    public Member getMemberAtIndex(int index){
        return population.get(index);
    }

    public void addMember(Member member){
        population.add(member);
    }

    public int getAmountOfMembers() {
        return amountOfMembers;
    }

    public void setAmountOfMembers(int amountOfMembers) {
        this.amountOfMembers = amountOfMembers;
    }

    public void clearFitnessAndStatus(){
        for(Member member : population){
            member.setFitness(0);
            member.clearGeneStatus();
        }
    }
}
