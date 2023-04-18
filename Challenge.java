import java.util.*;

public class Challenge {

    public static int calculateTurns(int seats, int customer, String dishes){
        String dishesOrder = "EPD";
        String assignDishes = "";
        String [] dishes2 = dishes.split("");

        System.err.println("Customer index equals to E: " + dishes2[customer].equals("E"));

        int count = 0;
        if(customer == 0){
            return turnsForwards(dishes2, dishesOrder, assignDishes);
        }

        if(customer > 0 && !dishes2[customer].equals("E")){
            String [] checkBackwards = Arrays.copyOfRange(dishes2, 0, customer+1);
            Collections.reverse(Arrays.asList(checkBackwards));
            int countBackwards = countToEBackwards(checkBackwards, dishesOrder, assignDishes);
            String [] checkFordward = Arrays.copyOfRange(dishes2, customer-countBackwards, dishes2.length);
            return turnsForwards(checkFordward, dishesOrder, assignDishes) + countBackwards;
        } else if (dishes2[customer].equals("E")) {
            String [] checkFordwar = Arrays.copyOfRange(dishes2, customer, dishes2.length);
            count += turnsForwards(checkFordwar, dishesOrder, assignDishes);
        }
        System.err.println(count);
        return count;
    }

    public static String overwriteCreate(String dishesIteration,int eCount,int pCount,int dCount){
        if(!dishesIteration.contains("N")){
            if(dishesIteration.contains("E") && eCount == 1){
                return "E";
            } else if (dishesIteration.contains("P") && pCount == 1) {
                return "P";
            }else if(dishesIteration.contains("D") && dCount == 1){
                return "D";
            }
        }
        return "";
    }

    public static int turnsForwards(String [] dishes2, String dishesOrder, String assignDishes){
        int count = 0;
        int eCount = 0;
        int pCount = 0;
        int dCount = 0;
        for(String x : dishes2){
            if(x.equals("E")){
                eCount++;
            } else if (x.equals("P")) {
                pCount++;
            } else if (x.equals("D")) {
                dCount++;
            }
            assignDishes+=overwriteCreate(x,eCount,pCount,dCount);
            if(dishesOrder.equals(assignDishes)){
                break;
            }
            count++;
        }
        System.err.println("Fordward Count : " + count);
        return count;
    }

    public static int countToEBackwards(String [] checkBackwards, String dishesOrder, String assignDishes){
        int count = 0;
        int eCount = 0;
        int pCount = 0;
        int dCount = 0;
        for(String x : checkBackwards){
            if(x.equals("E")){
                eCount++;
            } else if (x.equals("P")) {
                pCount++;
            } else if (x.equals("D")) {
                dCount++;
            }
            assignDishes+=overwriteCreate(x,eCount,pCount,dCount);
            if(x.equals("E")){
                System.err.println("Backwards Count : " + count);
                return count;
            }
            count++;
        }
        System.err.println("Backwards Count : " + count);
        return count;
    }

    public static void main(String[] args) {
        System.err.println("Count from main Page: " + calculateTurns(14,3,"NNNEPNNPPPDE"));
    }
}