package aurelienribon.tweenstudio;

import com.badlogic.gdx.InputAdapter;

public class TweenStudioInputProcessor extends InputAdapter {
	private final TweenStudio studio;

	public TweenStudioInputProcessor(TweenStudio studio) {
		this.studio = studio;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		studio.processUiInput(x, y);
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		studio.processUiInput(x, y);
		return true;
	}
}
