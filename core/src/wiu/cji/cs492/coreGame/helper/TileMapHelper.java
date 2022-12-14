package wiu.cji.cs492.coreGame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;

import wiu.cji.cs492.Objects.Collectables;
import wiu.cji.cs492.Objects.DeathWall;
import wiu.cji.cs492.Objects.Enemy;
import wiu.cji.cs492.Objects.Finish;
import wiu.cji.cs492.Objects.Food;
import wiu.cji.cs492.Objects.Player;
import wiu.cji.cs492.coreGame.GameScreen;
import wiu.cji.cs492.coreGame.LevelListScreen;

import static wiu.cji.cs492.coreGame.helper.Constants.PPM;

import java.util.Iterator;
import java.util.Locale;

public class TileMapHelper {
    private TiledMap tiledMap;
    private GameScreen gameScreen;
    private String levelName;
     // can create an array of maps to cycle through

    public TileMapHelper(GameScreen gameScreen, String levelName){
        this.gameScreen = gameScreen;
        this.levelName = levelName;
    }

    //Method to render the map and objects
    public OrthogonalTiledMapRenderer setUpMap(){
        //grabs the objects and map files
        try {
            if (levelName != ""){
                tiledMap = new TmxMapLoader().load(levelName+".tmx");
            }
            else{
                tiledMap = new TmxMapLoader().load("MapAssets/Map1.1.tmx");
            }
        }
        catch (NullPointerException n){
            Gdx.app.log("Layers", "Map does not exist");

        }
        //THIS WAS THE WORST PART
        //can create a string array and parse through all layers in a loop
        getLayer("Death");
        getLayer("Ground Object");
       // parseMapObjects(tiledMap.getLayers().get("Ground Object").getObjects());
        getLayer("Player");
        //parseMapObjects(tiledMap.getLayers().get("Player").getObjects());
        getLayer("Collectables");
        getLayer("Enemies");
        // not a layer getLayer("Finish");

        //returns to the game screen
        return new OrthogonalTiledMapRenderer(tiledMap);
    }
    private void getLayer(String s){
        try{
            parseMapObjects(tiledMap.getLayers().get(s).getObjects(), s);}
        catch (NullPointerException n){
            Gdx.app.log("Map", s+"  could not load");
        };
    }
    public void parseMapObjects(MapObjects mapObjects, String s){
        //Iteration through map objects
        for(MapObject mapObject : mapObjects){

            if(mapObject instanceof PolygonMapObject){
                Body body = createStaticBody((PolygonMapObject) mapObject);

            }

            //Rectangle Case
            if(mapObject instanceof RectangleMapObject){
                RectangleMapObject rectangleObject = (RectangleMapObject) mapObject;
                Rectangle rectangle = rectangleObject.getRectangle();
                String tempName = mapObject.getName();
                if (tempName != null){
                    Body body = BodyHelperService.createBody(
                            rectangle.getX() + rectangle.getWidth()/2,
                            rectangle.getY()+rectangle.getHeight()/2,
                            rectangle.getWidth(),
                            rectangle.getHeight(),
                            false,
                            gameScreen.getWorld()
                    );
                    if (tempName.equals("Bunny")){
                        Iterator<Fixture> tmp = body.getFixtureList().iterator();
                        tmp.next().setUserData("Player Body");
                        Gdx.app.log("Player", "Player object started at x: "+body.getPosition().x + " y: "+body.getPosition().y);
                        gameScreen.setPlayer(new Player(rectangle.width, rectangle.height, body, "Bunny"));
                    }
                    else if (tempName.equals("Raccoon")){
                        Iterator<Fixture> tmp = body.getFixtureList().iterator();
                        tmp.next().setUserData("Player Body");
                        Gdx.app.log("Player", "Player object started at x: "+body.getPosition().x + " y: "+body.getPosition().y);
                        gameScreen.setPlayer(new Player(rectangle.width, rectangle.height, body, "Raccoon"));
                    }
                    else if (tempName.equals("Squirrel")){
                        Iterator<Fixture> tmp = body.getFixtureList().iterator();
                        tmp.next().setUserData("Player Body");
                        Gdx.app.log("Player", "Player object started at x: "+body.getPosition().x + " y: "+body.getPosition().y);
                        gameScreen.setPlayer(new Player(rectangle.width, rectangle.height, body, "Squirrel"));
                    }

                   // else if (tempName.equals("enemy")) { //|| s.equals("Collectables")){
                        //gameScreen.addCollectables(new Food(rectangle.width, rectangle.height, body, "Carrot"));
                     //   Gdx.app.log("sprites", "Sprite Position is x:"+body.getPosition().x + " y:"+body.getPosition().y);
                    //}
                    else if (tempName.equals("Carrot")) { //|| s.equals("Collectables")){
                        gameScreen.addCollectables(new Food(rectangle.width, rectangle.height, body, "Carrot"));
                        Gdx.app.log("Collection", "width should be "+rectangle.getWidth());
                        Gdx.app.log("sprites", "Sprite Position is x:"+body.getPosition().x + " y:"+body.getPosition().y);
                    }
                    else if (tempName.equals("Acorn")) { //|| s.equals("Collectables")){
                        gameScreen.addCollectables(new Food(rectangle.width, rectangle.height, body, "Acorn"));
                        Gdx.app.log("Collection", "width should be "+rectangle.getWidth());
                        Gdx.app.log("sprites", "Sprite Position is x:"+body.getPosition().x + " y:"+body.getPosition().y);
                    }
                    else if (tempName.equals("Trash")) { //|| s.equals("Collectables")){
                        gameScreen.addCollectables(new Food(rectangle.width, rectangle.height, body, "Trash"));
                        Gdx.app.log("Collection", "width should be "+rectangle.getWidth());
                        Gdx.app.log("sprites", "Sprite Position is x:"+body.getPosition().x + " y:"+body.getPosition().y);
                    }
                    else if(tempName.equals("Death")){
                        gameScreen.addDeathWall (new DeathWall(rectangle.width, rectangle.height, body));
                        Gdx.app.log("sprites", "Death Position is x:"+body.getPosition().x + " y:"+body.getPosition().y);
                    }
                    else if(tempName.equals("Fox")){
                        gameScreen.addEnemy (new Enemy(rectangle.width, rectangle.height, body, "Fox", Math.round(60.0f* ( mapObject.getProperties().get("WalkTime",Float.class) == null ? 1 : mapObject.getProperties().get("WalkTime",Float.class)) )));
                        Gdx.app.log("sprites", "enemy Position is x:"+body.getPosition().x + " y:"+body.getPosition().y);
                    }
                    else if(tempName.equals("Snake")){
                        gameScreen.addEnemy (new Enemy(rectangle.width, rectangle.height, body, "Snake", Math.round(60.0f* ( mapObject.getProperties().get("WalkTime",Float.class) == null ? 1 : mapObject.getProperties().get("WalkTime",Float.class)) )));
                        Gdx.app.log("sprites", "enemy Position is x:"+body.getPosition().x + " y:"+body.getPosition().y);
                    }
                    else if(tempName.equals("Wolf")){
                        gameScreen.addEnemy (new Enemy(rectangle.width, rectangle.height, body, "Wolf",  Math.round(60.0f* ( mapObject.getProperties().get("WalkTime",Float.class) == null ? 1 : mapObject.getProperties().get("WalkTime",Float.class)) )));
                        Gdx.app.log("sprites", "enemy Position is x:"+body.getPosition().x + " y:"+body.getPosition().y);
                        //Gdx.app.log("speed", "enemy speed:"+ Math.round(60.0f* ( mapObject.getProperties().get("WalkTime",Float.class) == null ? 1 : mapObject.getProperties().get("WalkTime",Float.class)) ));
                    }
                    else if(tempName.equals("Finish")){
                        gameScreen.addFinish (new Finish(rectangle.width, rectangle.height, body, "NextLevel"));
                        Gdx.app.log("sprites", "Finish Position is x:"+body.getPosition().x + " y:"+body.getPosition().y);
                    }
                    else if(tempName.equals("End")){
                        gameScreen.addFinish (new Finish(rectangle.width, rectangle.height, body, "EndGame"));
                        Gdx.app.log("sprites", "Finish Position is x:"+body.getPosition().x + " y:"+body.getPosition().y);
                    }

                }

            }
        }
    }


    private BodyDef getBodyDef(float x, float y)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);

        return bodyDef;
    }

    private Body createStaticBody(PolygonMapObject polygonMapObject){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = gameScreen.getWorld().createBody(bodyDef);
        Shape shape = createPolygonShape(polygonMapObject);
        body.createFixture(shape, 1000);
        shape.dispose();
        return body;
    }

    private Shape createPolygonShape(PolygonMapObject polygonMapObject) {
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVeritces = new Vector2[vertices.length/2];
        for (int i = 0; i< vertices.length/2 ; i++){
            Vector2 current = new Vector2(vertices[i*2]/PPM, vertices[i*2 +1]/PPM); // need to devide to match the world "scaling"
            worldVeritces[i] = current;
        }

        PolygonShape shape = new PolygonShape();
        shape.set(worldVeritces);
        return shape;
    }


}