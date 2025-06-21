package com.example.servingwebcontent.dto;

public class GameInput {
    private boolean keyA;
    private boolean keyD;
    private boolean keySpace;
    private boolean keyW;

    public GameInput() {}

    public GameInput(boolean keyA, boolean keyD, boolean keySpace, boolean keyW) {
        this.keyA = keyA;
        this.keyD = keyD;
        this.keySpace = keySpace;
        this.keyW = keyW;
    }

    // Getters and Setters
    public boolean isKeyA() { return keyA; }
    public void setKeyA(boolean keyA) { this.keyA = keyA; }
    
    public boolean isKeyD() { return keyD; }
    public void setKeyD(boolean keyD) { this.keyD = keyD; }
    
    public boolean isKeySpace() { return keySpace; }
    public void setKeySpace(boolean keySpace) { this.keySpace = keySpace; }
    
    public boolean isKeyW() { return keyW; }
    public void setKeyW(boolean keyW) { this.keyW = keyW; }
} 