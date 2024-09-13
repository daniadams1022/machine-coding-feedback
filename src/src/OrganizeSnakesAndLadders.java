import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class OrganizeSnakesAndLadders {

    public void organiseEvent(Map<Integer, Integer[]> snakeCordinatesMap, Map<Integer, Integer[]> ladderCordinatesMap, Map<Integer, String> playersNameMap){

        Integer currentDiceThrowByPlayer = generateRandomDiceThrow();

//        boolean isGameOver = false;
        Map<Integer, Boolean> resultMap = new HashMap<>();
        resultMap.put(1,false);

        Integer currentDiceThrowerPlayerIndex = 1;

        Map<Integer, Integer> playerCordinatesOnBoardBeforeThrow = new HashMap<>();
        Map<Integer, Integer> updatedPlayerCordinatesOnBoardAfterThrow = new HashMap<>();

        for (int x = 1; x <= playersNameMap.size(); x++){
            playerCordinatesOnBoardBeforeThrow.put(x, 0);
            updatedPlayerCordinatesOnBoardAfterThrow.put(x, 0);
        }

        while(!resultMap.get(1)){

            System.out.println(playersNameMap.get(currentDiceThrowerPlayerIndex) + " rolled a " + currentDiceThrowByPlayer + "and moved from " + playerCordinatesOnBoardBeforeThrow.get(currentDiceThrowerPlayerIndex) + "to" + updatedPlayerCordinatesOnBoardAfterThrow.get(currentDiceThrowerPlayerIndex));

//            if(computeIsTheGameOver(updatedPlayerCordinatesOnBoardAfterThrow, currentDiceThrowerPlayerIndex)){
//                isGameOver = true;
//                System.out.println(playersNameMap.get(currentDiceThrowerPlayerIndex) + "wins the game");
//            }

            computeCurrentPostionOnBoardAndCheckIfGameOver(playerCordinatesOnBoardBeforeThrow, updatedPlayerCordinatesOnBoardAfterThrow, currentDiceThrowerPlayerIndex, currentDiceThrowByPlayer, resultMap, snakeCordinatesMap, ladderCordinatesMap, playersNameMap);

            if (currentDiceThrowerPlayerIndex > playersNameMap.size()){
                currentDiceThrowerPlayerIndex = 1;
            }else {
                currentDiceThrowerPlayerIndex++;
            }
        }

    }

    private void computeCurrentPostionOnBoardAndCheckIfGameOver(Map<Integer, Integer> playerCordinatesOnBoardBeforeThrow, Map<Integer, Integer> updatedPlayerCordinatesOnBoardAfterThrow, Integer currentDiceThrowerPlayerIndex, Integer currentDiceThrowByPlayer, Map<Integer, Boolean> resultMap, Map<Integer, Integer[]> snakeCordinatesMap, Map<Integer, Integer[]> ladderCordinatesMap, Map<Integer, String> playersNameMap) {

//        if (isStablePositionForCurrentThorwerReached){
//            return;
//        }

        if (computeIsTheGameOver(updatedPlayerCordinatesOnBoardAfterThrow, currentDiceThrowerPlayerIndex)){
//            isStablePositionForCurrentThorwerReached = true;
//            isGameOver = true;
            resultMap.put(1,true);
            System.out.println(playersNameMap.get(currentDiceThrowerPlayerIndex) + "wins the game");
            return;
        }

        if(playerHasReachedOutsideBoard(playerCordinatesOnBoardBeforeThrow, currentDiceThrowerPlayerIndex, currentDiceThrowByPlayer)){
            // then don't update player position
            return;
        }

        if (isStablePositionForCurrentThorwerReached(playerCordinatesOnBoardBeforeThrow, currentDiceThrowerPlayerIndex, currentDiceThrowByPlayer, snakeCordinatesMap, ladderCordinatesMap)){
            return;
        }

//        else if (hasSnakeBittenThePlayer(playerCordinatesOnBoardBeforeThrow, currentDiceThrowerPlayerIndex, currentDiceThrowByPlayer, snakeCordinatesMap)){
//            // move the player to snakes Tail
//        }
//        else if (hasPlayerMovedToLadder()){
//            // move the player to Ladders Top
//
//        }
//        else if (!hasSnakeBittenThePlayer() && !hasPlayerMovedToLadder() && !playerHasReachedOutsideBoard()){
//            // simply update player position by adding the currentDiceThrowByPlayer to its current Player position
//        }

        ifSnakeBittenThePlayerThenUpdateThePlayerPosition(playerCordinatesOnBoardBeforeThrow, updatedPlayerCordinatesOnBoardAfterThrow, currentDiceThrowerPlayerIndex, currentDiceThrowByPlayer, snakeCordinatesMap);

        ifLadderReachedThePlayerThenUpdateThePlayerPosition(playerCordinatesOnBoardBeforeThrow, updatedPlayerCordinatesOnBoardAfterThrow, currentDiceThrowerPlayerIndex, currentDiceThrowByPlayer, ladderCordinatesMap);

        computeCurrentPostionOnBoardAndCheckIfGameOver(playerCordinatesOnBoardBeforeThrow, updatedPlayerCordinatesOnBoardAfterThrow, currentDiceThrowerPlayerIndex, currentDiceThrowByPlayer, resultMap, snakeCordinatesMap, ladderCordinatesMap, playersNameMap);

    }

    private boolean isStablePositionForCurrentThorwerReached(Map<Integer, Integer> playerCordinatesOnBoardBeforeThrow, Integer currentDiceThrowerPlayerIndex, Integer currentDiceThrowByPlayer, Map<Integer, Integer[]> snakeCordinatesMap, Map<Integer, Integer[]> ladderCordinatesMap) {
        Integer positionToJudge = playerCordinatesOnBoardBeforeThrow.get(currentDiceThrowerPlayerIndex) + currentDiceThrowByPlayer;
        for (int x = 1; x<= snakeCordinatesMap.size(); x++){
            if (Objects.equals(snakeCordinatesMap.get(x)[0], positionToJudge)){
                return false;
            }
        }
        for (int x = 1; x<= ladderCordinatesMap.size(); x++){
            if (Objects.equals(ladderCordinatesMap.get(x)[0], positionToJudge)){
                return false;
            }
        }
        return true;
    }

    private void ifLadderReachedThePlayerThenUpdateThePlayerPosition(Map<Integer, Integer> playerCordinatesOnBoardBeforeThrow, Map<Integer, Integer> updatedPlayerCordinatesOnBoardAfterThrow, Integer currentDiceThrowerPlayerIndex, Integer currentDiceThrowByPlayer, Map<Integer, Integer[]> ladderCordinatesMap) {

        for (int x = 1; x <= ladderCordinatesMap.size(); x++){
             Integer[] bottomAndTop = ladderCordinatesMap.get(x);
             Integer bottom = bottomAndTop[0];
             Integer top = bottomAndTop[1];

             if (bottom == playerCordinatesOnBoardBeforeThrow.get(currentDiceThrowerPlayerIndex) + currentDiceThrowByPlayer){
                 // ladder has been reached so move the player to table's top
                 updatedPlayerCordinatesOnBoardAfterThrow.put(currentDiceThrowerPlayerIndex, top);
                 ifLadderReachedThePlayerThenUpdateThePlayerPosition(updatedPlayerCordinatesOnBoardAfterThrow, updatedPlayerCordinatesOnBoardAfterThrow, currentDiceThrowerPlayerIndex, currentDiceThrowByPlayer, ladderCordinatesMap);
                 return;
             } else if (playerCordinatesOnBoardBeforeThrow == updatedPlayerCordinatesOnBoardAfterThrow) {
                 // check if there is another ladder at the updated position
                 if (bottom.equals(playerCordinatesOnBoardBeforeThrow.get(currentDiceThrowerPlayerIndex))){
                     // player has reached another table so update position
                     updatedPlayerCordinatesOnBoardAfterThrow.put(currentDiceThrowerPlayerIndex, top);
                     ifLadderReachedThePlayerThenUpdateThePlayerPosition(updatedPlayerCordinatesOnBoardAfterThrow, updatedPlayerCordinatesOnBoardAfterThrow, currentDiceThrowerPlayerIndex, currentDiceThrowByPlayer, ladderCordinatesMap);
                     return;
                 }
             }
        }

    }

    private void ifSnakeBittenThePlayerThenUpdateThePlayerPosition(Map<Integer, Integer> playerCordinatesOnBoardBeforeThrow, Map<Integer, Integer> updatedPlayerCordinatesOnBoardAfterThrow, Integer currentDiceThrowerPlayerIndex, Integer currentDiceThrowByPlayer, Map<Integer, Integer[]> snakeCordinatesMap) {

        for (int x = 1; x<= snakeCordinatesMap.size(); x++){
            Integer[] headAndTailOfSnakeArray = snakeCordinatesMap.get(x);
            Integer head = headAndTailOfSnakeArray[0];
            Integer tail = headAndTailOfSnakeArray[1];
            if ( (playerCordinatesOnBoardBeforeThrow != updatedPlayerCordinatesOnBoardAfterThrow) && head == playerCordinatesOnBoardBeforeThrow.get(currentDiceThrowerPlayerIndex) + currentDiceThrowByPlayer){
                // player is bitten so move the player to snake's tail
                updatedPlayerCordinatesOnBoardAfterThrow.put(currentDiceThrowerPlayerIndex, tail);
                ifSnakeBittenThePlayerThenUpdateThePlayerPosition(updatedPlayerCordinatesOnBoardAfterThrow, updatedPlayerCordinatesOnBoardAfterThrow, currentDiceThrowerPlayerIndex, currentDiceThrowByPlayer, snakeCordinatesMap);
                return;
            }
            else if (playerCordinatesOnBoardBeforeThrow == updatedPlayerCordinatesOnBoardAfterThrow){
                // check if there is another snake at updated position
                if (Objects.equals(head, playerCordinatesOnBoardBeforeThrow.get(currentDiceThrowerPlayerIndex))){
                    // player is bitten again by another snake so update position
                    updatedPlayerCordinatesOnBoardAfterThrow.put(currentDiceThrowerPlayerIndex, tail);
                    ifSnakeBittenThePlayerThenUpdateThePlayerPosition(updatedPlayerCordinatesOnBoardAfterThrow, updatedPlayerCordinatesOnBoardAfterThrow, currentDiceThrowerPlayerIndex, currentDiceThrowByPlayer, snakeCordinatesMap);
                    return;
                }
            }
        }

    }

    private boolean playerHasReachedOutsideBoard(Map<Integer, Integer> playerCordinatesOnBoardBeforeThrow, Integer currentDiceThrowerPlayerIndex, Integer currentDiceThrowByPlayer) {
        return playerCordinatesOnBoardBeforeThrow.get(currentDiceThrowerPlayerIndex) + currentDiceThrowByPlayer > 100;
    }


    private boolean computeIsTheGameOver(Map<Integer, Integer> updatedPlayerCordinatesOnBoardAfterThrow, Integer playerThrowerIndex) {

        return updatedPlayerCordinatesOnBoardAfterThrow.get(playerThrowerIndex) == 100;
    }

    private Integer generateRandomDiceThrow() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

}
