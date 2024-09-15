import java.util.HashMap;
import java.util.Map;

public class SimplySnakeAndLadderPositions {

    public static final Integer totalPositionsOnBoard = 100;

    public Map<Integer, Integer> simplyfySnakeAndLadderPositions(Map<Integer, Map<Integer, Integer>> snakeCordinatesMap, Map<Integer, Map<Integer, Integer>> ladderCordinatesMap) {
        Map<Integer, Integer> simplifiedPositionMap = new HashMap<>();

        for (int i=0; i<=totalPositionsOnBoard; i++) {
            Integer finalPositionToMoveTo = checkIfThereIsASnakeOrLadderAtThisPostionOnBoard(i, snakeCordinatesMap, ladderCordinatesMap);
            simplifiedPositionMap.put(i, finalPositionToMoveTo);
        }
        return simplifiedPositionMap;
    }

    private Integer checkIfThereIsASnakeOrLadderAtThisPostionOnBoard(int currentPostion, Map<Integer, Map<Integer, Integer>> snakeCordinatesMap, Map<Integer, Map<Integer, Integer>> ladderCordinatesMap) {
        Integer finalPosition = currentPostion;
        for (int x=1; x<=snakeCordinatesMap.size(); x++) {
            Map<Integer, Integer> snakeLengthMap = snakeCordinatesMap.get(x);
            if (snakeLengthMap.containsKey(finalPosition)) {
                finalPosition = snakeLengthMap.get(currentPostion);
                break;
            }
        }
        for (int y=1; y<=ladderCordinatesMap.size(); y++) {
            Map<Integer, Integer> ladderLengthMap = ladderCordinatesMap.get(y);
            if (ladderLengthMap.containsKey(finalPosition)) {
                finalPosition = ladderLengthMap.get(currentPostion);
                break;
            }
        }
        if (finalPosition.equals(currentPostion)) {
            return finalPosition;
        }
        checkIfThereIsASnakeOrLadderAtThisPostionOnBoard(finalPosition, snakeCordinatesMap, ladderCordinatesMap);
        return finalPosition;
    }


}
