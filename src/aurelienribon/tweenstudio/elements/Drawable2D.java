package aurelienribon.tweenstudio.elements;

import com.badlogic.gdx.InputProcessor;
import java.util.List;

public abstract class Drawable2D extends TweenStudioObject {
    public static final int ORIGIN_XY = 1;
	public static final int POSITION_XY = 2;
	public static final int SCALE_XY = 3;
	public static final int ROTATION = 4;
	public static final int OPACITY = 5;

	@Override
	public List<Integer> getAvailableTweenTypes() {
		List<Integer> list = super.getAvailableTweenTypes();
		list.add(ORIGIN_XY);
		list.add(POSITION_XY);
		list.add(SCALE_XY);
		list.add(ROTATION);
		list.add(OPACITY);
		return list;
	}

	@Override
	public String getTweenTypeStaticName(int tweenType) {
		if (super.getTweenTypeStaticName(tweenType) != null)
			return super.getTweenTypeStaticName(tweenType);

		switch (tweenType) {
			case ORIGIN_XY: return "Drawable2D.ORIGIN_XY";
			case POSITION_XY: return "Drawable2D.POSITION_XY";
			case SCALE_XY: return "Drawable2D.SCALE_XY";
			case ROTATION: return "Drawable2D.ROTATION";
			case OPACITY: return "Drawable2D.OPACITY";
			default: return null;
		}
	}

	@Override
	public String getTweenTypeDesc(int tweenType) {
		if (super.getTweenTypeDesc(tweenType) != null)
			return super.getTweenTypeDesc(tweenType);

		switch (tweenType) {
			case ORIGIN_XY: return "Origin XY";
			case POSITION_XY: return "Position XY";
			case SCALE_XY: return "Scale XY";
			case ROTATION: return "Rotation";
			case OPACITY: return "Opacity";
			default: return null;
		}
	}

	@Override
	public InputProcessor getInputProcessor() {
		return super.getInputProcessor();
	}
}
