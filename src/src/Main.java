import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Integer[] intArgs = SnakeLadderAndPlayerMapExtractor.convertStringArgsToIntegerArgs(args);
        Map<Integer, Map<Integer, Integer>> snakeCordinatesMap = SnakeLadderAndPlayerMapExtractor.extractSnakeCordinatesMap(intArgs);
        Map<Integer, Map<Integer, Integer>> ladderCordinatesMap = SnakeLadderAndPlayerMapExtractor.extractLadderCordinatesMap(intArgs);
        Map<Integer, String> playersNameMap = SnakeLadderAndPlayerMapExtractor.extractPlayersNameMap(args);

        MoveTheBoard.moveBoardAccordingToMoves(snakeCordinatesMap, ladderCordinatesMap, playersNameMap);
    }
}
