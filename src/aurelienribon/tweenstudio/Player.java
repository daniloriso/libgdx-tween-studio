package aurelienribon.tweenstudio;

import aurelienribon.libgdx.tween.TweenSequence;
import com.badlogic.gdx.math.Vector2;
import java.util.Map;

public abstract class Player {
	// -------------------------------------------------------------------------
	// To implement
	// -------------------------------------------------------------------------

	protected abstract TweenSequence getTimeline();

	// -------------------------------------------------------------------------
	// Stubs
	// -------------------------------------------------------------------------

    protected void getFieldNames(Map<TweenStudioObject, String> map) {}
	protected void convertInputToPosition(Vector2 input) {}

	// -------------------------------------------------------------------------
	// Package-only
	// -------------------------------------------------------------------------

	protected void run() {
		TweenSequence seq = getTimeline();
		seq.start();
	}
}
