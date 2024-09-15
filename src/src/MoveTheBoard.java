import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class MoveTheBoard {

    public static void moveBoardAccordingToMoves(Map<Integer, Map<Integer, Integer>> snakeCordinatesMap, Map<Integer, Map<Integer, Integer>> ladderCordinatesMap, Map<Integer, String> playersNameMap) {
        SimplySnakeAndLadderPositions snakeAndLadderPositions = new SimplySnakeAndLadderPositions();
        Map<Integer, Integer> simplifiedPositionMap = snakeAndLadderPositions.simplyfySnakeAndLadderPositions(snakeCordinatesMap, ladderCordinatesMap);

        Integer currentDiceThrowerPlayerIndex = 1;

        Map<Integer, Integer> playerCordinatesOnBoardBeforeThrow = new HashMap<>();
        Map<Integer, Integer> updatedPlayerCordinatesOnBoardAfterThrow = new HashMap<>();

        for (int x = 1; x <= playersNameMap.size(); x++){
            playerCordinatesOnBoardBeforeThrow.put(x, 0);
            updatedPlayerCordinatesOnBoardAfterThrow.put(x, 0);
        }

        while (!isWinnerDecide(updatedPlayerCordinatesOnBoardAfterThrow, currentDiceThrowerPlayerIndex, playersNameMap)){
            Integer currentDiceThrowByPlayer = generateRandomDiceThrow();


            keepPlaying(simplifiedPositionMap, currentDiceThrowerPlayerIndex, currentDiceThrowByPlayer, updatedPlayerCordinatesOnBoardAfterThrow, playerCordinatesOnBoardBeforeThrow);
            System.out.println(playersNameMap.get(currentDiceThrowerPlayerIndex) + " rolled a " + currentDiceThrowByPlayer + " and moved from " + playerCordinatesOnBoardBeforeThrow.get(currentDiceThrowerPlayerIndex) + " to " + updatedPlayerCordinatesOnBoardAfterThrow.get(currentDiceThrowerPlayerIndex));
            playerCordinatesOnBoardBeforeThrow.replace(currentDiceThrowerPlayerIndex, updatedPlayerCordinatesOnBoardAfterThrow.get(currentDiceThrowerPlayerIndex));
            currentDiceThrowerPlayerIndex = identifyNextThrowerIndex(currentDiceThrowerPlayerIndex, playersNameMap);
        }

    }

    private static void keepPlaying(Map<Integer, Integer> simplifiedPositionMap, Integer currentDiceThrowerPlayerIndex, Integer currentDiceThrowByPlayer, Map<Integer, Integer> updatedPlayerCordinatesOnBoardAfterThrow, Map<Integer, Integer> playerCordinatesOnBoardBeforeThrow) {

        Integer expectedPositionForCurrentPlayerOnBoard = playerCordinatesOnBoardBeforeThrow.get(currentDiceThrowerPlayerIndex) + currentDiceThrowByPlayer;
        if (expectedPositionForCurrentPlayerOnBoard > 100){
            return;
        }
        Integer finalPositionForCurrentPlayerOnBoard = simplifiedPositionMap.get(expectedPositionForCurrentPlayerOnBoard);
        if (!Objects.equals(finalPositionForCurrentPlayerOnBoard, expectedPositionForCurrentPlayerOnBoard)){
            updatedPlayerCordinatesOnBoardAfterThrow.replace(currentDiceThrowerPlayerIndex, finalPositionForCurrentPlayerOnBoard);
        } else{
            updatedPlayerCordinatesOnBoardAfterThrow.replace(currentDiceThrowerPlayerIndex, expectedPositionForCurrentPlayerOnBoard);
        }
    }

    private static boolean isWinnerDecide(Map<Integer, Integer> updatedPlayerCordinatesOnBoardAfterThrow, int lastDiceThrowerPlayerIndex, Map<Integer, String> playersNameMap) {
        if (lastDiceThrowerPlayerIndex - 1 < 1){
            lastDiceThrowerPlayerIndex = playersNameMap.size();
        }else {
            lastDiceThrowerPlayerIndex--;
        }
        if (Objects.equals(updatedPlayerCordinatesOnBoardAfterThrow.get(lastDiceThrowerPlayerIndex), 100)){
            System.out.println(playersNameMap.get(lastDiceThrowerPlayerIndex) + " wins the game");
            return true;
        }
        return false;
    }

    private static Integer identifyNextThrowerIndex(Integer currentDiceThrowerPlayerIndex, Map<Integer, String> playersNameMap) {
        if (currentDiceThrowerPlayerIndex >= playersNameMap.size()){
            return 1;
        }
        return currentDiceThrowerPlayerIndex + 1;
    }

    private static Integer generateRandomDiceThrow() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

}
