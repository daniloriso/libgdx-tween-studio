package aurelienribon.tweenstudio;

import aurelienribon.tweenstudio.elements.TweenStudioObject;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import java.util.Map;

public abstract class TweenStudioEditor {
    protected abstract void getFieldNames(Map<TweenStudioObject, String> map);
	protected abstract InputProcessor getCurrentInputProcessor();
	protected abstract Vector2 getPositionFromInput(Vector2 inputPos);
}
