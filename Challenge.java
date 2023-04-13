import java.util.*;

public class Challenge {

    public static int calculateTurns(int seats, int customer, String dishes){
        String dishesOrder = "EPD";
        String assignDishes = "";
        String [] dishes2 = dishes.split("");


        int count = 0;
        if(customer == 0){
            System.err.println("Customer is equals to 0");
            count+=turnsForwards(dishes2, count, dishesOrder, assignDishes);
            System.err.println("assignDishes: " + assignDishes + " vs dishesOrder: " + dishesOrder);
            System.err.println(count);
            return count;
        }

        if(customer > 0 && !dishes2[customer].equals("E")){
            String [] checkBackwards = Arrays.copyOfRange(dishes2, 0, customer+1);
            String [] checkFordwar = Arrays.copyOfRange(dishes2, customer, dishes2.length);
            Collections.reverse(Arrays.asList(checkBackwards));
            System.err.println("Customer is grater than 0");
            System.err.println("FIRST IF checkBackwards array: " + Arrays.toString(checkBackwards) + "checkForward array: " + Arrays.toString(checkFordwar) + "Count backwards: " + count);
            count += turnsForwards(checkFordwar, 0, dishesOrder, assignDishes) + countToEBackwards(checkBackwards, 0);;
            System.err.println("count" + count);
            return count;
        } else if (dishes2[customer].equals("E")) {
            String [] checkFordwar = Arrays.copyOfRange(dishes2, customer, dishes2.length);
            System.err.println("Customer is grater than 0");
            System.err.println("checkBackwards array: " + 0 + "checkForward array: " + Arrays.toString(checkFordwar) + "Count backwards: " + count);
            count += turnsForwards(checkFordwar, 0, dishesOrder, assignDishes);
        }
        System.err.println("assignDishes: " + assignDishes + " vs dishesOrder: " + dishesOrder);
        System.err.println(count);
        return count;
    }

    public static String overwriteCreate(String create, String dishesIteration){
        if(!dishesIteration.equals("N")){
            if(!create.contains("E") && dishesIteration.equals("E")){
                return "E";
            } else if (!create.contains("P") && dishesIteration.equals("P")) {
                return "P";
            }else if(!create.contains("D") && dishesIteration.equals("D")){
                return "D";
            }
        }
        return "";
    }

    public static int turnsForwards(String [] dishes2, int count, String dishesOrder, String assignDishes){
        for(String x : dishes2){
            assignDishes+=overwriteCreate(assignDishes,x);
            System.out.println("Dish: " + x + " Iteration: " + count);
            System.out.println("assignDishes inside of turnsForwards before: " + assignDishes);
            if(dishesOrder.equals(assignDishes)){
                break;
            }
            System.out.println("Adding: " + overwriteCreate(assignDishes,x));
            System.out.println("assignDishes inside of turnsForwards after: " + assignDishes);

            if(x.equals("E") && assignDishes.contains("E")){
                count++;
            } else if (x.equals("P") && assignDishes.contains("P")) {
                count++;
            }else if (x.equals("D") && assignDishes.contains("D")) {
                count++;
            }else if (x.equals("N")) {
                count++;
            }
            System.out.println("_________________");
        }
        System.err.println("Count :" + count);
        return count;
    }

    public static int countToEBackwards(String [] checkBackwards, int count){
        String create = "";
        for(String x : checkBackwards){
            create+=overwriteCreate(create,x);
            if(create.contains("E")){
                break;
            }
            System.out.println("Dish: " + x + " Iteration: " + count);
            if(!x.equals("E") && !create.equals("E")){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        calculateTurns(14,2,"ENNPPPNNDN");
    }
}
