package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Saleh on 9/11/16.
 */
public class MyContactListener implements ContactListener {
    //Called when 2 fixtures start to collide
    public void beginContact(Contact c) {
        System.out.println("Begin Contact!");
        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();
        System.out.println(fa.getUserData() + ", " + fb.getUserData());
    }

    // called when 2 fixtures no longer collide
    public void endContact(Contact c) {
        System.out.println("End contact!");
    }



    public void preSolve(Contact c, Manifold m) {}
    public void postSolve(Contact c, ContactImpulse ci) {}
}
