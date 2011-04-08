package aurelienribon.tweenstudio;

import aurelienribon.libgdx.tween.Tween;
import aurelienribon.libgdx.tween.TweenSequence;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public abstract class Editor {
	private final Map<TweenStudioObject, String> nameMap;
	private final Map<TweenStudioObject, TweenStudioObjectState> initStates;

	public Editor() {
		nameMap = new LinkedHashMap<TweenStudioObject, String>();
		initStates = new LinkedHashMap<TweenStudioObject, TweenStudioObjectState>();
	}

	// -------------------------------------------------------------------------
	// To implement
	// -------------------------------------------------------------------------

	protected abstract TweenSequence getTimeline();
    protected abstract void getFieldNames(Map<TweenStudioObject, String> map);
	protected abstract void convertInputToPosition(Vector2 input);

	// -------------------------------------------------------------------------
	// Package-only
	// -------------------------------------------------------------------------

	protected void run() {
		getFieldNames(nameMap);
		buildInitStates();
		createMainWindow();
	}

	protected Tween[] getCorrectedTimeline() {
		List<Tween> tweens = new ArrayList<Tween>();

		for (Tween tween : getTimeline().getTweens())
			tweens.add(tween);

		for (int i=tweens.size()-1; i>0; i--) {
			int correction = tweens.get(i-1).getDurationMillis() + tweens.get(i-1).getDelayMillis();
			tweens.get(i).delay(-correction);
		}

		return tweens.toArray(new Tween[0]);
	}

	protected TweenStudioObject getObjectFromName(String name) {
		for (Entry<TweenStudioObject, String> entry : nameMap.entrySet()) {
			if (entry.getValue().equals(name))
				return entry.getKey();
		}
		return null;
	}

	protected String getNameFromObject(TweenStudioObject tso) {
		return nameMap.get(tso);
	}

	protected String[] getObjectNames() {
		return nameMap.values().toArray(new String[0]);
	}

	protected TweenStudioObject[] getObjects() {
		return initStates.keySet().toArray(new TweenStudioObject[0]);
	}

	protected TweenStudioObjectState getInitState(TweenStudioObject tso) {
		return initStates.get(tso);
	}

	// -------------------------------------------------------------------------
	// Internals
	// -------------------------------------------------------------------------

	private void createMainWindow() {
		final Editor editor = this;

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				MainWindow mw = new MainWindow();
				mw.setEditor(editor);
				mw.setVisible(true);
			}
		});
	}

	private void buildInitStates() {
		initStates.clear();
		for (TweenStudioObject tso : nameMap.keySet()) {
			TweenStudioObjectState state = TweenStudioObjectState.fromObject(tso);
			initStates.put(tso, state);
		}
	}
}
