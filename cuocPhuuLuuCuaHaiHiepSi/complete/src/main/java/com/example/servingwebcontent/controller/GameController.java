package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.dto.GameInput;
import com.example.servingwebcontent.dto.GameState;
import com.example.servingwebcontent.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/game")
    public String gamePage() {
        return "game";
    }

    @GetMapping("/state")
    @ResponseBody
    public ResponseEntity<GameState> getGameState() {
        gameService.update();
        return ResponseEntity.ok(gameService.getGameState());
    }

    @PostMapping("/input")
    @ResponseBody
    public ResponseEntity<GameState> processInput(@RequestBody GameInput input) {
        gameService.processInput(input);
        gameService.update();
        return ResponseEntity.ok(gameService.getGameState());
    }

    @PostMapping("/reset")
    @ResponseBody
    public ResponseEntity<GameState> resetGame() {
        gameService.resetGame();
        return ResponseEntity.ok(gameService.getGameState());
    }

    @GetMapping("/textures")
    @ResponseBody
    public ResponseEntity<String[]> getTexturePaths() {
        String[] textures = {
            "/texture/Sprites/Idle1.png",
            "/texture/Sprites/runner.png", 
            "/texture/Sprites/ATKfinal.png",
            "/texture/Sprites/gethit.png",
            "/texture/Sprites/JUMP1.png",
            "/texture/Nightborne/Idle1.png",
            "/texture/Nightborne/Run.png",
            "/texture/Nightborne/Attack.png",
            "/texture/Background/background1.png",
            "/texture/Background/background2.png",
            "/texture/Background/background3.png",
            "/texture/Background/background4a.png"
        };
        return ResponseEntity.ok(textures);
    }
} 