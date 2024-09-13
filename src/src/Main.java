import java.nio.channels.Selector;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Map<Integer, Integer[]> snakeCordinatesMap = extractSnakeCordinatesFromArgs(args);
        Map<Integer, Integer[]> ladderCordinatesMap = extractLadderCordinatedFromArgs(args);
        Map<Integer, String> playersNameMap = extractPlayersNameMapFromArgs(args);

        if (snakeCordinatesMap == null || ladderCordinatesMap == null || playersNameMap == null) {
            System.out.println("Invalid parameters");
            return;
        }

        OrganizeSnakesAndLadders organizeSnakesAndLadders = new OrganizeSnakesAndLadders();

        organizeSnakesAndLadders.organiseEvent(snakeCordinatesMap, ladderCordinatesMap, playersNameMap);


    }


    private static Map<Integer, Integer[]> extractSnakeCordinatesFromArgs(String[] args) {
        if(args.length <= 1 || args.length < Integer.parseInt(args[0])*2+1) {
            System.out.println("Incorrect arguments given");
            return null;
        }
        System.out.println(args[0]);
        int numberOfSnakes = Integer.parseInt(args[0]);
        Map<Integer, Integer[]> map = new HashMap<>();
        int count = 1;
        for(int i = 1; i < numberOfSnakes; i++) {

            Integer[] snakeCordinates = new Integer[2];
            snakeCordinates[0] = Integer.parseInt(args[i*2-1]);
            snakeCordinates[1] = Integer.parseInt(args[i*2]);
            count ++;
            map.put(count, snakeCordinates);
        }
        return map;
    }

    private static Map<Integer, Integer[]> extractLadderCordinatedFromArgs(String[] args) {
        if (args.length<=1 || args.length < (Integer.parseInt(args[0])*2+1) || (args.length < (2*(Integer.parseInt(args[2*Integer.parseInt(args[0]) +1])) +1) + Integer.parseInt(args[0])*2+1)) {
            System.out.println("Incorrect arguments given");
            return null;
        }
        int ladderCountIndex = Integer.parseInt(args[0]) * 2 + 1;
        int ladderStartIndex = ladderCountIndex + 1;
        int ladderEndIndex =  ladderCountIndex + Integer.parseInt(args[ladderCountIndex])*2;

        System.out.println(args[ladderCountIndex]);
//        int numberOfLadders = Integer.parseInt(args[0]);
        Map<Integer, Integer[]> map = new HashMap<>();
        int count = 1;
        for(int i = ladderStartIndex; i <= ladderEndIndex; i++) {
            Integer[] ladderCordinates = new Integer[2];
            ladderCordinates[0] = Integer.parseInt(args[i]);
            ladderCordinates[1] = Integer.parseInt(args[i+1]);
            i = i +2;
            map.put(count, ladderCordinates);
            count++;
        }
        return map;
    }

    private static Map<Integer, String> extractPlayersNameMapFromArgs(String[] args) {
        if (args.length<=1 || args.length < (Integer.parseInt(args[0])*2+1) || (args.length < (2*(Integer.parseInt(args[2*Integer.parseInt(args[0]) +1])) +1) + Integer.parseInt(args[0])*2+1)
                || args.length < ((2*(Integer.parseInt(args[2*Integer.parseInt(args[0]) +1])) +1) + Integer.parseInt(args[0])*2+1) + Integer.parseInt(args[Integer.parseInt(args[(2*(Integer.parseInt(args[2*Integer.parseInt(args[0]) +1])) +1) + Integer.parseInt(args[0])*2+1])])*2+1) {
            System.out.println("Incorrect arguments given");
            return null;
        }
        int personCountIndex = 2*(Integer.parseInt(args[2*Integer.parseInt(args[0]) +1])) +1 + Integer.parseInt(args[0])*2+1;
        int personStartIndex = personCountIndex + 1;
        int personEndIndex =  personCountIndex + Integer.parseInt(args[personCountIndex])*2;

        System.out.println(args[personCountIndex]);
//        int numberOfSnakes = Integer.parseInt(args[0]);
        Map<Integer, String> map = new HashMap<>();
        int count = 1;
        for(int i = personStartIndex; i <= personEndIndex; i++) {
            map.put(count, args[i]);
            count++;
        }
        return map;
    }

}
