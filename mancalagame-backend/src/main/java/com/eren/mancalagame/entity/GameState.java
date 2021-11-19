package com.eren.mancalagame.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameState {
    private int turn;
    private List<Player> players;
    private boolean isEndOfGame;
    private int winnerIndex;
}
