package packet.maybyNBp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuSCR implements Screen {

    Main main;
    Vector3 touch;
    Viewport viewport;
    Batch batch;
    Button tittle;
    Button play;
    Button plains;
    Button start;
    Button underground;
    Button exit;
    BitmapFont tittleFont;
    int worldset;
    String page;

    public MenuSCR(Main main){
        this.main = main;
        this.worldset = main.worldset;
        this.touch = main.touch;
        this.viewport = main.viewport;
        this.batch = main.batch;
        this.tittleFont = main.tittleFont;
        page = "main";
        tittle = new Button(110,105,tittleFont,"STRELALKA \nKRYTAYA");
        play = new Button(20,50,tittleFont,"Play");
        exit = new Button(20,25, tittleFont, "Exit");

        plains = new Button(8,50,tittleFont,"Plains");
        underground = new Button(8,25,tittleFont,"Underground");
        start = new Button(110,105,tittleFont,"Start");
    }

    @Override
    public void show() {
        page = "main";
    }

    @Override
    public void render(float delta) {
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            viewport.getCamera().unproject(touch);
            System.out.println(touch.x + " " + touch.y);
            switch (page) {
                case "main": {
                    if (play.hit(touch)) {
                        page = "worldChose";
                    }
                    if (exit.hit(touch)) {
                        Gdx.app.exit();
                    }
                    break;
                }
                case "worldChose": {
                    if (plains.hit(touch)) {
                        worldset = 1;
                        underground.text = "Underground";
                        plains.text = "Plains(selected)";
                    }
                    if (underground.hit(touch)) {
                        worldset = 2;
                        plains.text = "Plains";
                        underground.text = "Underground(selected)";
                    }
                    if (start.hit(touch) && worldset != 0) {
                        main.worldset = worldset;
                        main.setScreen(main.game);
                    }
                }
            }
        }
        batch.setProjectionMatrix(viewport.getCamera().combined);
        ScreenUtils.clear(Color.CYAN);
        batch.begin();
        switch (page) {
            case"main": {
                tittle.font.draw(batch, tittle.text, tittle.x, tittle.y);
                play.font.draw(batch, play.text, play.x, play.y);
                exit.font.draw(batch, exit.text, exit.x, exit.y);
                break;
            }
            case"worldChose": {
                start.font.draw(batch, start.text, start.x, start.y);
                plains.font.draw(batch, plains.text, plains.x, plains.y);
                underground.font.draw(batch, underground.text, underground.x, underground.y);
                break;
            }
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.apply();    }

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

    }
}
