package aurelienribon.tweenstudio.elements;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.Tweenable;
import com.badlogic.gdx.InputProcessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A TweenStudioObject represents a Tweenable implementation that is directly
 * supported in the Tween Studio. It features many extra methods to get its
 * current state or the list of its tweenable attributes with human-readable
 * descriptions.
 */
public abstract class TweenStudioObject implements Tweenable {
	/**
	 * TO_OVERRIDE
	 * Gets a list of the available tweenable types.
	 */
	public List<Integer> getAvailableTweenTypes() {
		return new ArrayList<Integer>();
	}

	/**
	 * TO_OVERRIDE
	 * Gets the full static name of the tween type.
	 * e.g.: Drawable2D.OPACITY
	 */
	public String getTweenTypeStaticName(int tweenType) {
		return null;
	}

	/**
	 * TO_OVERRIDE
	 * Gets a human readable description of the tween type.
	 */
	public String getTweenTypeDesc(int tweenType) {
		return null;
	}

	/**
	 * TO_OVERRIDE
	 * Gets an input processor associated with the current object.
	 */
	public InputProcessor getInputProcessor() {
		return null;
	}

	/**
	 * Gets the current state of this object. The same result can be get with
	 * 'new State(thisObject)'.
	 */
	public final State getCurrentState() {
		return new State(this);
	}

	/**
	 * Sets the object to the given state.
	 */
	public final void setState(State state) {
		for (Integer tweenType : getAvailableTweenTypes())
			if (state.contains(tweenType))
				tweenUpdated(tweenType, state.get(tweenType));
	}

	/**
	 * A TSO state is a list of values corresponding to every possible
	 * tweenable attribute.
	 */
	public final class State {
		private final Map<Integer, float[]> map;

		/**
		 * Ctor.
		 */
		public State(TweenStudioObject obj) {
			map = new HashMap<Integer, float[]>();

			for (Integer tweenType : getAvailableTweenTypes()) {
				assert !map.containsKey(tweenType);
				map.put(tweenType, getPart(tweenType));
			}
		}

		/**
		 * Returns true of the State contains such type of tweenable attribute.
		 */
		public boolean contains(int tweenType) {
			return map.containsKey(tweenType);
		}

		/**
		 * Gets the attribute values corresponding to a tween type.
		 * Returns null if the given type is not supported.
		 */
		public float[] get(int tweenType) {
			return map.get(tweenType);
		}
		
		private float[] getPart(int tweenType) {
			float[] statePart = new float[Tween.MAX_COMBINED_TWEENS];
			getTweenValues(tweenType, statePart);
			return statePart;
		}
	}
}
