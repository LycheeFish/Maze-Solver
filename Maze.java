import java.util.*;

public class Maze {
    public static void main(String[] args){
        
        //0 corridor 1 wall 2 beginning 3 end
        int[][] layout = { 
            {1,1,1,1,1,1,1,1},
            {1,0,1,0,1,1,1,1},
            {1,2,0,0,0,0,1,1},
            {1,0,0,1,1,0,1,1},
            {1,1,0,1,0,0,0,1},
            {1,1,1,1,0,1,3,1},
            {1,1,1,1,0,1,1,1},
            {1,1,1,1,1,1,1,1},
        };

        int rowLength = layout.length;
        int colLength = layout[0].length;

        MNode[][] map = new MNode[rowLength][colLength];
        
        //setting the maze by copying over types
        int countIndex = 0;
        for (int i = 0; i<rowLength; i++) {
            for (int j = 0; j<colLength; j++){
                //copying types
                map[i][j] = new MNode();
                map[i][j].setType(layout[i][j]);
                //initializing indexes
                map[i][j].setIndex(countIndex);
                countIndex++;
            }
        }

        
        //reward
        double reward = 1;
        //small value
        double limit = .01;
        //tracker
        int x = 1;

        while (x == 1) {
            x=0;
            for (int i = 0; i<rowLength; i++) {
                for (int j = 0; j<colLength; j++) {
                    if (map[i][j].getType() == 0 || map[i][j].getType() == 3) {
                        //adding values of sides to an arraylist and finding the maximum
                        ArrayList<Double> sides = new ArrayList<>();
                        if (map[i-1][j].getType() == 0  || map[i-1][j].getType() == 2 || map[i-1][j].getType() == 3) {
                            sides.add(map[i-1][j].getPastValue()-reward);
                        }
                        if (map[i+1][j].getType() == 0  || map[i+1][j].getType() == 2 || map[i+1][j].getType() == 3) {
                            sides.add(map[i+1][j].getPastValue()-reward);
                        }
                        if (map[i][j-1].getType() == 0  || map[i][j-1].getType() == 2 || map[i][j-1].getType() == 3) {
                            sides.add(map[i][j-1].getPastValue()-reward);
                        }
                        if (map[i][j+1].getType() == 0  || map[i][j+1].getType() == 2 || map[i][j+1].getType() == 3) {
                            sides.add(map[i][j+1].getPastValue()-reward);
                        }
                        //finding maximum
                        map[i][j].setNewValue(Collections.max(sides));
                        //calculating difference
                        //System.out.println(map[i][j].getNewValue()-map[i][j].getPastValue());
                        if (Math.abs(map[i][j].getNewValue()-map[i][j].getPastValue()) > limit){
                            x = 1;
                        };
                        map[i][j].setPastValue(map[i][j].getNewValue());

                    }
                }
            }
            
            if (x == 0) {
                break;
            }
        }

        double maxDirection;
        for (int i = 0; i<rowLength; i++) {
            for (int j = 0; j<colLength; j++) {
                if (map[i][j].getType() == 0 || map[i][j].getType() == 3){
                    //comparing maximums
                    ArrayList<Double> temp = new ArrayList<>();
                    if (map[i-1][j].getType() == 0 || map[i-1][j].getType() == 2){
                        temp.add(map[i-1][j].getPastValue());
                    }
                    if (map[i+1][j].getType() == 0 || map[i+1][j].getType() == 2){
                        temp.add(map[i+1][j].getPastValue());
                    }
                    if (map[i][j-1].getType() == 0 || map[i][j-1].getType() == 2){
                        temp.add(map[i][j-1].getPastValue());
                    }
                    if (map[i][j+1].getType() == 0 || map[i][j+1].getType() == 2){
                        temp.add(map[i][j+1].getPastValue());
                    }
                    maxDirection = Collections.max(temp);

                    //checking for maximum direction side
                    if (map[i-1][j].getType() == 2) {
                        System.out.println(map[i][j].getIndex());
                        map[i][j].getPolicy().clear();
                        map[i][j].getPolicy().add(1);
                    } else if (map[i-1][j].getPastValue() == maxDirection){
                        map[i][j].getPolicy().clear();
                        map[i][j].getPolicy().add(1);
                    }
                    if (map[i+1][j].getType() == 2) {
                        map[i][j].getPolicy().clear();
                        map[i][j].getPolicy().add(4);
                    } else if (map[i+1][j].getPastValue() == maxDirection){
                        map[i][j].getPolicy().clear();
                        map[i][j].getPolicy().add(4);
                    }
                    if (map[i][j-1].getType() == 2) {
                        map[i][j].getPolicy().clear();
                        map[i][j].getPolicy().add(3);
                    }else if (map[i][j-1].getPastValue() == maxDirection){
                        map[i][j].getPolicy().clear();
                        map[i][j].getPolicy().add(3);
                    }
                    if (map[i][j+1].getType() == 2) {
                        map[i][j].getPolicy().clear();
                        map[i][j].getPolicy().add(2);
                    }else if (map[i][j+1].getPastValue() == maxDirection){
                        map[i][j].getPolicy().clear();
                        map[i][j].getPolicy().add(2);
                    }

                }
            }
        }

        for(int i = 0; i<rowLength; i++) {
            for (int j = 0; j<colLength; j++) {
                if (j==7){
                    System.out.println(map[i][j].getPolicy().get(0));
                }
                else {
                    System.out.print(map[i][j].getPolicy().get(0));
                }
                
            }
        }

        for (int i = 0; i<rowLength; i++) {
            for (int j = 0; j<colLength; j++) {
                if (map[i][j].getIndex() == 25){
                    System.out.println(map[i][j].getPolicy().get(0));
                }
            }
        }
    
    }

}
