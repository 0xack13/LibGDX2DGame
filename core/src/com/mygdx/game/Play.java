package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Saleh on 9/10/16.
 */
public class Play extends GameState {

    private World world;
    private Box2DDebugRenderer b2dr;

    private OrthographicCamera b2dcam;

    private BitmapFont font = new BitmapFont();

    public Play(GameStateManager gsm) {
        super(gsm);
        world = new World(new Vector2(0, -9.81f), true);
        world.setContactListener(new MyContactListener());
        b2dr = new Box2DDebugRenderer();

        //create platform
        BodyDef bdef = new BodyDef();
        bdef.position.set(160/B2DVars.PPM, 120/B2DVars.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50/B2DVars.PPM, 5/B2DVars.PPM);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVars.BIT_GROUND;
        fdef.filter.maskBits = B2DVars.BIT_BOX | B2DVars.BIT_BALL;
        //fdef.restitution = 1f;
        body.createFixture(fdef);
        Fixture fixture = body.createFixture(fdef);
        fixture.setUserData("ground");

        // create falling box
        bdef.position.set(160 / B2DVars.PPM, 200 / B2DVars.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        shape.setAsBox(5/B2DVars.PPM,5/B2DVars.PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVars.BIT_BOX;
        fdef.filter.maskBits = B2DVars.BIT_GROUND;
        body.createFixture(fdef).setUserData("box");


        //create ball
        bdef.position.set(153/B2DVars.PPM, 220/B2DVars.PPM);
        body = world.createBody(bdef);


        CircleShape cshape = new CircleShape();
        cshape.setRadius(5 / B2DVars.PPM);
        fdef.shape = cshape;

        fdef.filter.categoryBits = B2DVars.BIT_BALL;
        fdef.filter.maskBits = B2DVars.BIT_GROUND;
        body.createFixture(fdef).setUserData("ball");
        //body.createFixture(fdef);

        b2dcam = new OrthographicCamera();
        b2dcam.setToOrtho(false, MyGdxGame.V_WIDTH / B2DVars.PPM, MyGdxGame.V_HEIGHT / B2DVars.PPM);

    }

    public void handleInput() {
        if(MyInput.isDown(MyInput.BUTTON2)){
            System.out.println("Hold X!");
        }
        if(MyInput.isUp(MyInput.BUTTON1)){
            System.out.println("Pressed Z!");
        }
    }

    public void update(float dt) {
        handleInput();
        world.step(dt, 6, 2);

    }
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //sb.setProjectionMatrix(cam.combined);
        //sb.begin();
        //font.draw(sb, "play state", 100, 100);
        //sb.end();
        b2dr.render(world, b2dcam.combined);
    }
    public void dispose() {}
}
