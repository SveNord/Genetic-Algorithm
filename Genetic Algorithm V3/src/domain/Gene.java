package domain;

import java.util.Random;

public class Gene {
    private char value;
    private Status geneStatus;
    private static final String characterSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ  ,!.";

    public Gene() {
        value = randomAzValue();
        geneStatus = Status.Default;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public char randomAzValue(){
        Random random = new Random();
        return characterSet.charAt(random.nextInt(characterSet.length()));
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public Status getGeneStatus() {
        return geneStatus;
    }

    public void setGeneStatus(Status geneStatus) {
        this.geneStatus = geneStatus;
    }
}
