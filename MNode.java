import java.util.*;

public class MNode {
    
    private int index;
    private int type;
    private double pastValue;
    private double newValue;
    private ArrayList<Integer> policy;
    
    public MNode() {
        index = 0;
        type = 0;
        pastValue = 0;
        newValue = 0;
        policy = new ArrayList<>(Arrays.asList(0));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex (int index) {
        this.index = index;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getPastValue() {
        return pastValue;
    }

    public void setPastValue(double pastValue) {
        this.pastValue = pastValue;
    }

    public double getNewValue() {
        return newValue;
    }

    public void setNewValue(double newValue) {
        this.newValue = newValue;
    }

    public ArrayList<Integer> getPolicy() {
        return policy;
    }

}
