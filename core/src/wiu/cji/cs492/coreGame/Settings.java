package wiu.cji.cs492.coreGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Settings implements Screen{
    final ForestAdventures game;
    private Stage stage;
    private Table table;
    private Texture Background;
    private Skin skin;
    private TextureAtlas atlas;
    private TextButton playButton;
    private TextButton musicButton;
    private TextButton backButton;
    private BitmapFont bitmapFont;
    private ProgressBar pBar;


    public Settings(final ForestAdventures game){

        this.game = game;

    }

    @Override
    public void show() {

        //These assets should be controlled by a manager class, but we can adapt to that later.
        //Sets the stage view to fit the device based on the constraints
        stage = new Stage(new FitViewport(800, 400));
        //Alows for input events on the stage
        Gdx.input.setInputProcessor(stage);
        //Adding image file to a texture object
        Background = new Texture("Main Menu Assets/forest.png");
        //Atlas lets there be images and text to an button object
        atlas = new TextureAtlas("Main Menu Assets/Buttons/Skin/uiskin.atlas");
        //Will let you apply the skin to the object
        skin = new Skin(atlas);
        //Used for the font in the buttons
        bitmapFont = new BitmapFont(Gdx.files.internal("Main Menu Assets/Buttons/Skin/default.fnt"));
        //Used to format the location of the buttons
        table = new Table(skin);
        table.setBounds(0, 0, 800, 400);

        //Creation of the buttons an its properties
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("default-round");
        textButtonStyle.down = skin.getDrawable("default-round-down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = bitmapFont;


        //Button object created with above properties
        playButton = new TextButton("PLAY", textButtonStyle);
        playButton.pad(20);

        musicButton = new TextButton("Music: On", textButtonStyle);
        musicButton.pad(20);

        backButton = new TextButton("Main Menu", textButtonStyle);
        backButton.pad(20);

        //Adding the button to the table and table to the stage
        refresh();

    }

    @Override
    public void render(float delta) {

        //Clears the Screen before rendering
        ScreenUtils.clear(0,0,0.2f,0);

        update(delta);
        //Draws the background before the buttons
        stage.getBatch().begin();
        stage.getBatch().draw(Background, 0,0,800,400);
        stage.getBatch().end();

        //Draws the actors or buttons
        stage.draw();



    }

    //Used to separate logic from rendering
    public void update(float delta){
        //calls any act method to the actors on stage
        stage.act(delta);
        //Will chnage screens when the button is pressed
        if(playButton.isTouchFocusListener() == true){
            game.setScreen(new GameScreen((ForestAdventures)game));
            dispose();
        }

        if(musicButton.isTouchFocusListener() == true){
            if(musicButton.getText().toString().equals("Music: Off")) {
                //turn music off when music is added******
                musicButton.setText("Music: On");
            }
            else{
                //turn music on when music is added******
                musicButton.setText("Music: Off");
            }

            refresh();
            //update screen so button changes****** still needs work
        }

        if(backButton.isTouchFocusListener() == true){
            game.setScreen(new MainMenuScreen((ForestAdventures)game));
            dispose();
        }
    }

    public void refresh()
    {
        //Adding the button to the table and table to the stage
        table.clear();
        table.add(playButton);
        table.add(musicButton).left().pad(20);
        table.add(backButton).pad(20);
        stage.clear();
        stage.addActor(table);
    }

    @Override
    public void resize(int width, int height) {
        //Will set the camera when stage is called, use true when using a HUD
        stage.getViewport().update(width, height, false);


    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        skin.dispose();
        atlas.dispose();
        stage.dispose();
    }

}