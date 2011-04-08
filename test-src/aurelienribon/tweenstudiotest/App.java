package aurelienribon.tweenstudiotest;

import aurelienribon.libgdx.tween.Tween;
import aurelienribon.libgdx.tween.TweenSequence;
import aurelienribon.libgdx.tween.equations.Cubic;
import aurelienribon.tweenstudio.Editor;
import aurelienribon.tweenstudio.TweenStudio;
import aurelienribon.tweenstudio.TweenStudioObject;
import aurelienribon.tweenstudio.tweenables.TweenableSprite;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.util.Map;

public class App implements ApplicationListener {
	private static final float SCREEN_WIDTH_METERS = 10.0f;

	private SpriteBatch sb;
	private OrthographicCamera camera;
	private Texture logoTexture;
	private Sprite logoSprite1;
	private Sprite logoSprite2;
	private Sprite logoSprite3;
	private TweenableSprite logoSpriteTween1;
	private TweenableSprite logoSpriteTween2;
	private TweenableSprite logoSpriteTween3;

	@Override
	public void create() {
		sb = new SpriteBatch();
		float ratio = (float)Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight();
		camera = new OrthographicCamera(SCREEN_WIDTH_METERS, SCREEN_WIDTH_METERS / ratio);
		camera.update();
		
		logoTexture = new Texture(Gdx.files.internal("test-data/logo.png"));
		logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		logoSprite1 = new Sprite(logoTexture);
		logoSprite1.setSize(2, 2);
		logoSprite1.setOrigin(logoSprite1.getWidth()/2, logoSprite1.getHeight()/2);
		logoSprite1.setPosition(0 - logoSprite1.getOriginX(), 0 - logoSprite1.getOriginY());

		logoSprite2 = new Sprite(logoTexture);
		logoSprite2.setSize(2, 2);
		logoSprite2.setOrigin(logoSprite2.getWidth()/2, logoSprite2.getHeight()/2);
		logoSprite2.setPosition(-2 - logoSprite2.getOriginX(), 0 - logoSprite2.getOriginY());

		logoSprite3 = new Sprite(logoTexture);
		logoSprite3.setSize(2, 2);
		logoSprite3.setOrigin(logoSprite3.getWidth()/2, logoSprite3.getHeight()/2);
		logoSprite3.setPosition(+2 - logoSprite3.getOriginX(), 0 - logoSprite3.getOriginY());

		logoSpriteTween1 = new TweenableSprite(logoSprite1);
		logoSpriteTween2 = new TweenableSprite(logoSprite2);
		logoSpriteTween3 = new TweenableSprite(logoSprite3);

		TweenStudio.edit(new Editor() {
			@Override
			public void getFieldNames(Map<TweenStudioObject, String> map) {
				map.put(logoSpriteTween1, "logoSpriteTween1");
				map.put(logoSpriteTween2, "logoSpriteTween2");
				map.put(logoSpriteTween3, "logoSpriteTween3");
			}

			@Override
			protected TweenSequence getTimeline() {
				return TweenSequence.set(
				);
			}

			@Override
			protected void convertInputToPosition(Vector2 input) {
				input.y = Gdx.graphics.getHeight() - input.y;

				float metersPerPixel = SCREEN_WIDTH_METERS / Gdx.graphics.getWidth();
				input.mul(metersPerPixel * camera.zoom);

				float screenHeightMeters = SCREEN_WIDTH_METERS * (float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
				input.x += camera.position.x - SCREEN_WIDTH_METERS / 2 * camera.zoom;
				input.y += camera.position.y - screenHeightMeters / 2 * camera.zoom;
			}
		});
	}

	@Override
	public void resume() {
	}

	@Override
	public void render() {
		GL10 gl = Gdx.gl10;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		sb.begin();
		camera.apply(gl);
		logoSprite1.draw(sb);
		logoSprite2.draw(sb);
		logoSprite3.draw(sb);
		sb.end();

		Tween.update();
	}

	@Override
	public void resize(int i, int i1) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void dispose() {
	}
}
