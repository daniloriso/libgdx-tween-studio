package aurelienribon.tweenstudio;

import aurelienribon.tweenstudio.elements.TweenStudioObjectState;
import aurelienribon.tweenstudio.elements.TweenStudioObject;
import aurelienribon.libgdx.tween.Tween;
import aurelienribon.libgdx.tween.TweenSequence;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * 
 */
public class TweenStudio {
	private final TweenSequence sequence;
	private final TweenStudioEditor editor;
	private final Map<TweenStudioObject, String> nameMap;
	private final Map<TweenStudioObject, TweenStudioObjectState> initStates;
	private MainWindow window;

	public TweenStudio(TweenSequence sequence, TweenStudioEditor editor) {
		this.sequence = sequence;
		this.editor = editor;
		this.nameMap = new LinkedHashMap<TweenStudioObject, String>();
		this.initStates = new LinkedHashMap<TweenStudioObject, TweenStudioObjectState>();
	}

	/**
	 * Shows the Tween Studio editor
	 */
	public void edit() {
		if (editor == null)
			throw new RuntimeException("You cannot show the editor if the studio was built without an editor");

		editor.getFieldNames(nameMap);
		Gdx.input.setInputProcessor(new TweenStudioInputProcessor(this));
		buildInitStates();
		createMainWindow();
	}

	/**
	 * Do not show the editor but directly plays the given sequence.
	 */
	public void play() {
		sequence.start();
	}

	// -------------------------------------------------------------------------
	// Package-only
	// -------------------------------------------------------------------------

	void processUiInput(Vector2 pos, Vector2 lastPos) {
		Vector2 convertedPos = editor.getPositionFromInput(pos.cpy());
		Vector2 convertedLastPos = editor.getPositionFromInput(lastPos.cpy());
		TweenStudioObject obj = window.getSelectionObject();
		int tweenType = window.getSelectionTweenType();

		if (obj != null) {
			assert tweenType >= 0;
			float[] newValues = new float[3];
			obj.getChangeFromUiMouseDown(tweenType, convertedPos, convertedLastPos, newValues);
			obj.tweenUpdated(tweenType, newValues);
			window.updateSelectionValues(newValues);
		}
	}

	/**
	 * Disposes of the studio, setting everything as before its creation.
	 */
	void close() {
		Gdx.input.setInputProcessor(editor.getCurrentInputProcessor());
	}

	/**
	 * Returns a copy of the tweens from the given timeline. The automatically
	 * added delays are removed from the copies.
	 */
	Tween[] getCorrectedTimeline() {
		List<Tween> tweens = new ArrayList<Tween>();

		for (Tween tween : sequence.getTweens())
			tweens.add(tween);

		for (int i=tweens.size()-1; i>0; i--) {
			int correction = tweens.get(i-1).getDurationMillis() + tweens.get(i-1).getDelayMillis();
			tweens.get(i).delay(-correction);
		}

		return tweens.toArray(new Tween[0]);
	}

	/**
	 * Returns an object from its associated name.
	 */
	TweenStudioObject getObjectFromName(String name) {
		for (Entry<TweenStudioObject, String> entry : nameMap.entrySet()) {
			if (entry.getValue().equals(name))
				return entry.getKey();
		}
		return null;
	}

	/**
	 * Returns a name from its associaed object
	 */
	String getNameFromObject(TweenStudioObject tso) {
		return nameMap.get(tso);
	}

	/**
	 * Gets the names of all the objects.
	 */
	String[] getObjectNames() {
		return nameMap.values().toArray(new String[0]);
	}

	/**
	 * Gets all the objects.
	 */
	TweenStudioObject[] getObjects() {
		return nameMap.keySet().toArray(new TweenStudioObject[0]);
	}

	/**
	 * Gets the initial state of an object.
	 */
	TweenStudioObjectState getInitState(TweenStudioObject tso) {
		return initStates.get(tso);
	}

	// -------------------------------------------------------------------------
	// Internals
	// -------------------------------------------------------------------------

	/**
	 * Builds and shows the main window.
	 */
	private void createMainWindow() {
		final TweenStudio studio = this;

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				UIManager.put("swing.boldMetal", Boolean.FALSE);

				if (window != null)
					window.dispose();
				window = new MainWindow(studio);
				window.setVisible(true);
			}
		});
	}

	/**
	 * Builds all initial object states.
	 */
	private void buildInitStates() {
		initStates.clear();
		for (TweenStudioObject tso : nameMap.keySet()) {
			TweenStudioObjectState state = TweenStudioObjectState.fromObject(tso);
			initStates.put(tso, state);
		}
	}
}
