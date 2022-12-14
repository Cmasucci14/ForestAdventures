package wiu.cji.cs492.coreGame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import wiu.cji.cs492.Objects.Collectables;

import wiu.cji.cs492.Objects.DeathWall;
import wiu.cji.cs492.Objects.Enemy;
import wiu.cji.cs492.Objects.Finish;
import wiu.cji.cs492.Objects.Food;
import wiu.cji.cs492.Objects.Player;
import wiu.cji.cs492.coreGame.ForestAdventures;

public class WorldContactListener implements ContactListener {
    protected ForestAdventures game;
    public WorldContactListener(ForestAdventures game){
        this.game = game;

    }


    @Override
    public void beginContact(Contact contact)  {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        Gdx.app.log("Begin Contact", ""+ (fixA.getUserData()== null ? fixB.getUserData(): fixB.getUserData()));

        if (fixA.getUserData() == "head" || fixB.getUserData() =="head"){
            Fixture head = fixA.getUserData() == "head" ? fixA:fixB;
            Fixture object = head == fixA ? fixB:fixA;
            if (object.getUserData() != null){
                try {
                    if (Collectables.class.isAssignableFrom(object.getUserData().getClass())) {
                        ((Collectables) object.getUserData()).onHeadHit();
                    }
                    if (DeathWall.class.isAssignableFrom(object.getUserData().getClass()) && Player.class.isAssignableFrom(head.getUserData().getClass())) {
                        ((DeathWall) object.getUserData()).onhit();
                    }
                }catch (NullPointerException e){
                    Gdx.app.log("Collision", "Object no longer exsist");
                }
            }
        }
        else if(fixA.getUserData() == "Player Body" || fixB.getUserData() =="Player Body"){
            Fixture head = fixA.getUserData() == "Player Body" ? fixA:fixB;
            Fixture object = head == fixA ? fixB:fixA;
            if (object.getUserData() != null){
                    Gdx.app.log("Collision", " found this object "+object.getUserData()+ " "+ object.getUserData().getClass().toString());
              Object temp = object.getUserData().getClass();
                if (Food.class.isAssignableFrom(object.getUserData().getClass())) {
                    Vector2 v = new Vector2(1,1);
                    if (Player.class.isAssignableFrom(head.getUserData().getClass())){
                        v = ((Player)(head.getUserData())).getBody().getLinearVelocity();
                    }

                    ((Food) object.getUserData()).onHeadHits(v);
            }
                else if (DeathWall.class.isAssignableFrom(object.getUserData().getClass())){//(DeathWall.class.isAssignableFrom(object.getUserData().getClass())){
                    Gdx.app.log("DeathWall", " collides with player");
                    ((DeathWall)object.getUserData()).onhit();
                }
                else if (Enemy.class.isAssignableFrom(object.getUserData().getClass())){//(DeathWall.class.isAssignableFrom(object.getUserData().getClass())){
                    Gdx.app.log("EnemyDeath", " Enemy collides with player");
                    ((Enemy)object.getUserData()).onhit();
                }
                else if (Finish.class.isAssignableFrom(object.getUserData().getClass())){//(DeathWall.class.isAssignableFrom(object.getUserData().getClass())){
                    Gdx.app.log("Finish", " Finish collides with player");
                    ((Finish)object.getUserData()).onhit();
                }

            }
        }


      //  int contactDef = fixA.getFilterData().categoryBits| fixB.getFilterData().categoryBits;

       // switch (contactDef){
           // case
        //}
    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("End Contact", "");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        //contact.setEnabled(false);
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
