package com.eren.mancalagame.controller;

import com.eren.mancalagame.entity.GameState;
import com.eren.mancalagame.service.GamePlayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GamePlayController {

    private final GamePlayService gamePlayService;

    @GetMapping("/init")
    public ResponseEntity<GameState> initializeGame() {
        return ResponseEntity.ok(gamePlayService.initializeGame());
    }

    @GetMapping("/playturn")
    public ResponseEntity<GameState> playTurn(@RequestParam int playerId, @RequestParam int pitId) {
        return ResponseEntity.ok(gamePlayService.playTurn(playerId, pitId));
    }


    @ExceptionHandler({Exception.class,RuntimeException.class})
    public ResponseEntity<String> gamePlayError(Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
