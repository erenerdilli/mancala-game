package com.eren.mancalagame;

import com.eren.mancalagame.service.GamePlayServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class MancalagameApplicationTests {

    @Autowired
    private GamePlayServiceImpl gamePlayService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void initData() {
        gamePlayService.initializeGame();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void initializeGameTest() {

        gamePlayService.initializeGame();

        assertNotNull(GamePlayServiceImpl.getGameState());
        assertNotNull(GamePlayServiceImpl.getGameState().getPlayers());

        assertEquals(2, GamePlayServiceImpl.getPlayerCount());
        assertEquals(2, GamePlayServiceImpl.getGameState().getPlayers().size());
        assertEquals(0, GamePlayServiceImpl.getGameState().getTurn());
        assertEquals(-1, GamePlayServiceImpl.getGameState().getWinnerIndex());
        assertEquals(GamePlayServiceImpl.getGameState().getPlayers().size(), GamePlayServiceImpl.getPlayersInGame().size());
        assertEquals(0, GamePlayServiceImpl.getGameState().getPlayers().get(0).getId());
        assertEquals(1, GamePlayServiceImpl.getGameState().getPlayers().get(1).getId());
        assertEquals(7, GamePlayServiceImpl.getGameState().getPlayers().get(0).getPits().length);
        assertEquals(6, GamePlayServiceImpl.getGameState().getPlayers().get(0).getPits()[0]);
        assertEquals(0, GamePlayServiceImpl.getGameState().getPlayers().get(0).getPits()[6]);

        assertFalse(GamePlayServiceImpl.getGameState().isEndOfGame());

    }

    @Test
    public void playTurnTest() {
        gamePlayService.playTurn(0, 0);
        assertEquals(0, GamePlayServiceImpl.getGameState().getTurn());
        assertEquals(0, GamePlayServiceImpl.getGameState().getPlayers().get(0).getPits()[0]);

        assertFalse(GamePlayServiceImpl.getGameState().isEndOfGame());

        gamePlayService.playTurn(0,0);
        assertEquals(0, GamePlayServiceImpl.getGameState().getTurn());

        gamePlayService.playTurn(0,5);
        assertEquals(1, GamePlayServiceImpl.getGameState().getTurn());
    }

    @Test
    public void shouldReturnOk() throws Exception {
        //mockMvc.perform(get("/init))
    }


}
