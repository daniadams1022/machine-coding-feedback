import java.util.HashMap;
import java.util.Map;

public class SnakeLadderAndPlayerMapExtractor {


    public static Integer[] convertStringArgsToIntegerArgs(String[] args) {
        int totalSnakeCount = Integer.parseInt(args[0]);
        int totalLadderCount = Integer.parseInt(args[2*totalSnakeCount+1]);
        int totalIntegersInArgs = 2 + 2*totalSnakeCount +2*totalLadderCount;
        Integer[] result = new Integer[totalIntegersInArgs];
        for (int i = 0; i <totalIntegersInArgs ; i++) {
            result[i] = Integer.parseInt(args[i]);
        }
        return result;
    }

    public static Map<Integer, Map<Integer, Integer>> extractSnakeCordinatesMap(Integer[] intArgs) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();

        Integer totalSnakeCount = intArgs[0];
        Integer count = 1;
        for (int i = 1; i <= 2*totalSnakeCount; i=i+2) {
            Map<Integer, Integer> snakeLengthMap = new HashMap<>();
            snakeLengthMap.put(intArgs[i], intArgs[i+1]);
            map.put(count, snakeLengthMap);
            count++;
        }
        return map;
    }

    public static Map<Integer, Map<Integer, Integer>> extractLadderCordinatesMap(Integer[] intArgs) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();

        Integer totalSnakeCount = intArgs[0];
        Integer totalLadderCount = intArgs[1+ 2*totalSnakeCount];
        Integer count = 1;
        for (int i = 2 + 2*totalSnakeCount; i <= 2*totalLadderCount + 2*totalLadderCount; i=i+2) {
            Map<Integer, Integer> ladderLengthMap = new HashMap<>();
            ladderLengthMap.put(intArgs[i], intArgs[i+1]);
            map.put(count, ladderLengthMap);
            count++;
        }
        return map;
    }

    public static Map<Integer, String> extractPlayersNameMap(String[] args) {
        Map<Integer, String> map = new HashMap<Integer, String>();

        int totalSnakeCount = Integer.parseInt(args[0]);
        int totalLadderCount = Integer.parseInt(args[1+ 2*totalSnakeCount]);
        int totalIntegersInArgs = 2 + 2*totalSnakeCount +2*totalLadderCount + 1;
        int totalPlayerCount = Integer.parseInt(args[2+ 2*totalSnakeCount + 2*totalLadderCount]);
        Integer count = 1;
        for (int i = 0; i < totalPlayerCount; i++) {
            map.put(count, args[i + totalIntegersInArgs]);
            count++;
        }
        return map;
    }

}
