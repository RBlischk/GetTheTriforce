package com.tapsi.getthetriforce.sprites.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.tapsi.getthetriforce.screens.others.PlayScreen;
import com.tapsi.getthetriforce.sprites.link.Link;

/**
 * Class that helps generate Enemies in general
 */
public abstract class Enemy extends Sprite {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Vector2 velocity;
    protected boolean toDestroy;
    protected boolean destroyed;

    public Enemy(PlayScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        toDestroy = false;
        destroyed = false;
        setPosition(x, y);
        defineEnemy();
        velocity = new Vector2(-1, -2);
        b2body.setActive(false);
    }

    protected abstract void defineEnemy();
    public abstract void update(float dt);
    public abstract void hitOnHead(Link link);
    public abstract void hitByEnemy(Enemy enemy);

    public  abstract void destroy();


    public void reverseVelocity(boolean x, boolean y){
        if(x)
            velocity.x = - velocity.x;
        if(y)
            velocity.y = - velocity.y;
    }
}
