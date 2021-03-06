package com.tapsi.getthetriforce.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.tapsi.getthetriforce.mainGameClass.GetTheTriforce;
import com.tapsi.getthetriforce.sprites.enemies.Enemy;
import com.tapsi.getthetriforce.sprites.items.Item;
import com.tapsi.getthetriforce.sprites.link.Link;
import com.tapsi.getthetriforce.sprites.tileObjects.InteractiveTileObject;

/**
 * handles collision detection
 */
public class WorldContactListener implements ContactListener{

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef){
           case GetTheTriforce.LINK_HEAD_BIT | GetTheTriforce.BRICK_BIT:
            case GetTheTriforce.LINK_HEAD_BIT | GetTheTriforce.STONE_BIT:
                if(fixA.getFilterData().categoryBits == GetTheTriforce.LINK_HEAD_BIT)
                    ((InteractiveTileObject) fixB.getUserData()).onHeadHit((Link) fixA.getUserData());
                else
                    ((InteractiveTileObject) fixA.getUserData()).onHeadHit((Link) fixB.getUserData());
                break;
            case GetTheTriforce.ENEMY_HEAD_BIT | GetTheTriforce.LINK_BIT:
                if(fixA.getFilterData().categoryBits == GetTheTriforce.ENEMY_HEAD_BIT)
                    ((Enemy)fixA.getUserData()).hitOnHead((Link) fixB.getUserData());
                else
                    ((Enemy)fixB.getUserData()).hitOnHead((Link) fixA.getUserData());
                break;
            case GetTheTriforce.ENEMY_BIT | GetTheTriforce.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == GetTheTriforce.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case GetTheTriforce.LINK_BIT | GetTheTriforce.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == GetTheTriforce.LINK_BIT)
                    ((Link) fixA.getUserData()).hit((Enemy) fixB.getUserData());
                else
                    ((Link) fixB.getUserData()).hit((Enemy) fixA.getUserData());
                break;

            case GetTheTriforce.ITEM_BIT | GetTheTriforce.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == GetTheTriforce.ITEM_BIT)
                    ((Item)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Item)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case GetTheTriforce.ITEM_BIT | GetTheTriforce.LINK_BIT:
                if(fixA.getFilterData().categoryBits == GetTheTriforce.ITEM_BIT)
                    ((Item)fixA.getUserData()).use((Link) fixB.getUserData());
                else
                    ((Item)fixB.getUserData()).use((Link) fixA.getUserData());
                break;
            case GetTheTriforce.ENEMY_BIT | GetTheTriforce.ENEMY_BIT:
                ((Enemy)fixA.getUserData()).hitByEnemy((Enemy)fixB.getUserData());
                ((Enemy)fixB.getUserData()).hitByEnemy((Enemy)fixA.getUserData());
                break;

            case GetTheTriforce.LINK_BIT | GetTheTriforce.END_BIT:
                if(fixA.getFilterData().categoryBits == GetTheTriforce.LINK_BIT)
                    ((Link)fixA.getUserData()).endMethod();
                else
                    ((Link)fixB.getUserData()).endMethod();
                break;

            case GetTheTriforce.LINK_BIT | GetTheTriforce.HOLE_BIT:
                if(fixA.getFilterData().categoryBits == GetTheTriforce.LINK_BIT)
                    ((Link)fixA.getUserData()).die();
                else
                    ((Link)fixB.getUserData()).die();
                break;


            case GetTheTriforce.ITEM_BIT | GetTheTriforce.HOLE_BIT:
                if(fixA.getFilterData().categoryBits == GetTheTriforce.ITEM_BIT)
                    ((Item)fixA.getUserData()).destroy();
                else
                    ((Item)fixB.getUserData()).destroy();
                break;

            case GetTheTriforce.ENEMY_BIT | GetTheTriforce.HOLE_BIT:
                if(fixA.getFilterData().categoryBits == GetTheTriforce.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).destroy();
                else
                    ((Enemy)fixB.getUserData()).destroy();

                break;

        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
