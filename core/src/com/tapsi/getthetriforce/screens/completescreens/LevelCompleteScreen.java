package com.tapsi.getthetriforce.screens.completescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tapsi.getthetriforce.mainGameClass.GetTheTriforce;
import com.tapsi.getthetriforce.scenes.Hud;
import com.tapsi.getthetriforce.screens.others.StartNavigationScreen;
import com.tapsi.getthetriforce.screens.others.LevelSelectionMenu;

import static com.badlogic.gdx.graphics.Color.GOLDENROD;
import static com.badlogic.gdx.graphics.Color.WHITE;

/**
 * Creates the screen between the levels
 */
public class LevelCompleteScreen implements Screen{
    private Viewport viewport;
    private Stage stage;
    private GetTheTriforce game;
    private SpriteBatch sb;
    private Texture texture;
    private TextButton selectLevelTB, exitGameTB;
    private Label completeLabel, descionLabel, pointsLabel;
    private Table table;
    private Music music;
    private Skin skin;
    private TextureAtlas buttonatlas;

    public LevelCompleteScreen(final GetTheTriforce game) {


        this.game = game;
        sb = game.batch;

        texture= new Texture("textures/back.jpg");


        viewport = new FitViewport(GetTheTriforce.V_WIDTH, GetTheTriforce.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        skin = new Skin();
        buttonatlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.txt"));
        skin.addRegions(buttonatlas);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = new BitmapFont();
        buttonStyle.fontColor = WHITE;
        buttonStyle.downFontColor = GOLDENROD;
        buttonStyle.up = skin.getDrawable("up");
        buttonStyle.down = skin.getDrawable("down");


        completeLabel = new Label("You completed the Level! Yeahr!", font);
        pointsLabel = new Label("You have reached " + Hud.getScore()+ " Points.", font);
        descionLabel = new Label("Do you want to: ", font);
        selectLevelTB = new TextButton("Select a new Level", buttonStyle);
        exitGameTB = new TextButton("Go back to the Menu", buttonStyle);



        selectLevelTB.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LevelSelectionMenu(game));
                dispose();
            }
        });

        exitGameTB.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new StartNavigationScreen(game));
                dispose();
            }
        });


        table = new Table();
        table.center();
        table.setFillParent(true);

        table.add(completeLabel).expandX();
        table.row();
        table.add(pointsLabel).expandX().padTop(10f);
        table.row();
        table.add(descionLabel).padTop(5f);
        table.row();
        table.add(selectLevelTB).expandX().padTop(5f);
        table.row();
        table.add(exitGameTB).expandX().padTop(5f);

        stage.addActor(table);

        music = GetTheTriforce.manager.get("audio/music/zelda.ogg", Music.class);
        music.setLooping(true);
        music.setVolume(0.3f);
        music.play();
    }


    @Override
    public void show() { Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        sb.draw(texture, 0, 0);
        sb.end();
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {

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
        stage.dispose();
        texture.dispose();
    }
}
