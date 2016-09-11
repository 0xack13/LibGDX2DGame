package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Saleh on 9/10/16.
 */
public class Play extends GameState {

    private World world;
    private Box2DDebugRenderer b2dr;

    private BitmapFont font = new BitmapFont();

    public Play(GameStateManager gsm) {
        super(gsm);
        world = new World(new Vector2(0, -9.81f), true);
        b2dr = new Box2DDebugRenderer();

        //create platform
        BodyDef bdef = new BodyDef();
        bdef.position.set(160,120);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 5);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        body.createFixture(fdef);

        // create falling box
        bdef.position.set(160,200);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        shape.setAsBox(5,5);
        fdef.shape = shape;
        body.createFixture(fdef);

    }

    public void handleInput() {}
    public void update(float dt) {
        world.step(dt, 6, 2);
    }
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //sb.setProjectionMatrix(cam.combined);
        //sb.begin();
        //font.draw(sb, "play state", 100, 100);
        //sb.end();
        b2dr.render(world, cam.combined);
    }
    public void dispose() {}
}
