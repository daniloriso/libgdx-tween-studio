package aurelienribon.tweenstudio;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class TweenStudioInputProcessor extends InputAdapter {
	private final TweenStudio studio;
	private final Vector2 lastPos;

	public TweenStudioInputProcessor(TweenStudio studio) {
		this.studio = studio;
		this.lastPos = new Vector2();
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		lastPos.set(x, y);
		studio.processUiInput(new Vector2(x, y), lastPos);
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		studio.processUiInput(new Vector2(x, y), lastPos);
		lastPos.set(x, y);
		return true;
	}
}
