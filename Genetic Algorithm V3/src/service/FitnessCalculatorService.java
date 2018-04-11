package service;

import domain.Member;
import domain.Status;

import java.util.List;

public class FitnessCalculatorService {

    public FitnessCalculatorService() {
    }

    /**
     * Calculates the fitness of a member by counting how many characters are the same as the result and are also on the same position
     */
    public void calculateBasicFitness(Member member, String result){
        String sentence = member.getSentence();
        for(int i = 0; i < sentence.length(); i++){
            if(result.charAt(i) == sentence.charAt(i)){
                member.addPointToFitness();
                member.getGeneAtIndex(i).setGeneStatus(Status.Good);
            }
            else{
                member.getGeneAtIndex(i).setGeneStatus(Status.Bad);
            }
        }
    }

    public void calculateBasicFitness(List<Member> members, String result){
        for(Member member : members){
            calculateBasicFitness(member, result);
        }
    }

    public Member getBestMember(List<Member> members){
        Member bestMember = null;
        for(Member member : members){
            if(bestMember == null){
                bestMember = member;
            }
            else if(bestMember.getFitness() < member.getFitness()){
                bestMember = member;
            }
        }
        return bestMember;
    }
}
