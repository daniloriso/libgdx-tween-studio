package aurelienribon.tweenstudio;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class EditorInputProcessor extends InputAdapter {
	private final Editor editor;
	private final Vector2 input = new Vector2();
	private final Vector2 lastInput = new Vector2();

	public EditorInputProcessor(Editor editor) {
		this.editor = editor;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		System.out.println("hello");
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return true;
	}
}
