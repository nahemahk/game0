package hk.nahema.game0;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture cartxr;
    private Sprite car;
    private BitmapFont font;
    private int Height;
    private static int acceleration;
    private static int speed;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        Height = Gdx.graphics.getHeight();
        cartxr = new Texture("assets/image/car.jpg");
        car = new Sprite(cartxr);
        car.setPosition(0, 0);
        acceleration = 0;
        speed = 0;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL32.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.draw(batch, "Hello world!", 10, Height - 10);
        car.draw(batch);
        batch.end();
        entry();
    }

    void entry() {
        boolean up = Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean left = Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        boolean canMove = (left != right);

        if (canMove) {
            if (left & acceleration >= -120f) {
                acceleration -= 20f;
            } else if (right & acceleration <= 120f) {
                acceleration += 30f;
            }
        } else {
            acceleration = 0;

            if (Math.abs(speed) > 0.5f) {
                speed *= 0.95f;
            } else {
                speed = 0;
            }
        }

        float time = Gdx.graphics.getDeltaTime();
        float posX = car.getX();

        speed += acceleration * time;
        posX += acceleration * time + 0.5 * acceleration * Math.pow(time, 2);
        car.setX(posX);
        /*
        if (up & !down) {
            y += speed * delta;
        } else if (down & !up) {
            y -= speed * delta;
        }
        
        if (right & !left) {
            x += speed * delta;
        } else if (left & !right) {
            x -= speed * delta;
        }
        
        car.setPosition(x, y);*/
    }

    @Override
    public void dispose() {
        batch.dispose();
        cartxr.dispose();
        super.dispose();
    }

    private void renderCar() {

    }

}
