package com.eren.mancalagame.service;

import com.eren.mancalagame.entity.GameState;

public interface GamePlayService {
    GameState initializeGame();
    GameState playTurn(int playerId, int pitId);
}
