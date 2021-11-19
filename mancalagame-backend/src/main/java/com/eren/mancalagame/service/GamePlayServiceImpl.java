package com.eren.mancalagame.service;

import com.eren.mancalagame.entity.GameState;
import com.eren.mancalagame.entity.Player;
import com.eren.mancalagame.util.MathHelper;
import com.eren.mancalagame.util.PitCreator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GamePlayServiceImpl implements GamePlayService {

    private final static int PLAYER_COUNT = 2;
    private final static int STONE_COUNT = 6;
    private final static int PIT_COUNT = 7;
    private static GameState gameState;
    private static List<Player> playersInGame;
    private static int turn = 0;

    @Override
    public GameState initializeGame() {
        playersInGame = new ArrayList<>();
        for (int i = 0; i < PLAYER_COUNT; i++) {
            playersInGame.add(new Player(i, PitCreator.createInitialPits(PIT_COUNT, STONE_COUNT)));
        }
        gameState = new GameState(0, playersInGame, false, -1);
        turn = gameState.getTurn();
        return gameState;
    }

    @Override
    public GameState playTurn(int playerId, int pitId) {
        if (gameState.isEndOfGame() || pitId == PIT_COUNT - 1 || playersInGame.get(playerId).getPits()[pitId] == 0) {
            return gameState;
        }
        if (turn != playerId) {
            throw new RuntimeException("It is not Player" + (playerId + 1) + "'s turn!");
        }

        int lastPlayedPitIndex = -1;
        int boardIndex = playerId;
        boolean isOpponentBigPit = playerId != boardIndex;
        boolean isCapture = false;
        int stoneCount = playersInGame.get(boardIndex).getPits()[pitId];
        int playPitIndex = pitId + 1;

        // collect all stones from pit
        playersInGame.get(boardIndex).getPits()[pitId] = 0;
        while (stoneCount > 0) {

            // skip opponent big pits
            if (playPitIndex == PIT_COUNT - 1 && isOpponentBigPit) {
                playPitIndex = 0;
                boardIndex = MathHelper.findMod (boardIndex + 1, PLAYER_COUNT);
                continue;
            }

            // Sow stone to next pit
            playersInGame.get(boardIndex).getPits()[playPitIndex]++;
            stoneCount--;
            lastPlayedPitIndex = playPitIndex;

            // check if eligible for capture
            if (stoneCount == 0 && boardIndex == playerId && playPitIndex < PIT_COUNT -1 && playersInGame.get(boardIndex).getPits()[playPitIndex] == 1) {
                isCapture = true;
            }

            // overlap to next player's board
            if (playPitIndex == PIT_COUNT - 1) {
                playPitIndex = 0;
                boardIndex = MathHelper.findMod(boardIndex + 1, PLAYER_COUNT);
                isOpponentBigPit = playerId != boardIndex;
            } else {
                // switch to next pit
                playPitIndex++;
            }
        }

        // capture stones
        if (isCapture) {
            captureStones(playerId, lastPlayedPitIndex);
        }

        // set end of game
        setEndOfGame();

        //set winner
        if (gameState.isEndOfGame()) {
            setWinner();
        }

        // Pass turn to next player if last stone not landed on own big pit
        if ((MathHelper.compareToZero(stoneCount))) {
            if (lastPlayedPitIndex != PIT_COUNT - 1) {
                turn = MathHelper.findMod(turn + 1, PLAYER_COUNT);
            }
        }

        gameState.setTurn(turn);
        return gameState;
    }

    private void captureStones(int boardIndex, int pitId) {
        int captureSum = 0;
        int base = PIT_COUNT - 2;
        playersInGame.get(boardIndex).getPits()[pitId] = 0;
        captureSum++;
        captureSum += playersInGame.get(MathHelper.findMod(boardIndex + 1, PLAYER_COUNT)).getPits()[base - pitId];
        playersInGame.get(MathHelper.findMod(boardIndex + 1, PLAYER_COUNT)).getPits()[base - pitId] = 0;
        playersInGame.get(boardIndex).getPits()[PIT_COUNT - 1] += captureSum;
    }

    private void setEndOfGame() {
        for (int i = 0; i < playersInGame.size(); i++) {
            int playerPitsCount = 0;
            for (int j = 0; j < playersInGame.get(i).getPits().length - 1; j++) {
                playerPitsCount += playersInGame.get(i).getPits()[j];
            }
            if (playerPitsCount == 0) {
                gameState.setEndOfGame(true);
            }
        }
    }

    private void setWinner() {
        int winnerStoneCount = -1;
        for (int i = 0; i < playersInGame.size(); i++) {
            if (playersInGame.get(i).getPits()[PIT_COUNT - 1] > winnerStoneCount) {
                winnerStoneCount = playersInGame.get(i).getPits()[PIT_COUNT - 1];
                gameState.setWinnerIndex(i);
            }
        }
    }

    public static int getPlayerCount() {
        return PLAYER_COUNT;
    }

    public static int getStoneCount() {
        return STONE_COUNT;
    }

    public static int getPitCount() {
        return PIT_COUNT;
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static List<Player> getPlayersInGame() {
        return playersInGame;
    }

    public static int getTurn() {
        return turn;
    }
}
