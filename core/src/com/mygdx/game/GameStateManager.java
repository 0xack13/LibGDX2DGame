package com.mygdx.game;

import java.util.Stack;

/**
 * Created by Saleh on 9/10/16.
 */
public class GameStateManager {
    private MyGdxGame game;
    private Stack<GameState> gameStates;
    public static final int PLAY = 919192;

    public GameStateManager(MyGdxGame game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(PLAY);
    }

    public void update(float dt) {
        gameStates.peek().update(dt);
    }

    public MyGdxGame game() {
        return game;
    }

    public void render() {
        gameStates.peek().render();
    }

    private GameState getState(int state) {
        if(state == PLAY) return new Play(this);
        return null;
    }

    private void setState(int state) {
        popState();
        pushState(state);
    }

    private void pushState(int state) {
        gameStates.push(getState(state));
    }

    public void popState() {
        GameState g = gameStates.pop();
        g.dispose();
    }
}
