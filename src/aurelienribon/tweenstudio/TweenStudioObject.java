package aurelienribon.tweenstudio;

import aurelienribon.libgdx.tween.Tweenable;

public abstract class TweenStudioObject implements Tweenable {
	public static final int ORIGIN_XY = 1;
	public static final int POSITION_XY = 2;
	public static final int SCALE_XY = 3;
	public static final int ROTATION = 4;
	public static final int OPACITY = 5;

	@Override
	public int getTweenedAttributeCount(int tweenType) {
		switch (tweenType) {
			case ORIGIN_XY:
			case POSITION_XY:
			case SCALE_XY:
				return 2;

			case ROTATION:
			case OPACITY:
				return 1;

			default:
				assert false;
				return 0;
		}
	}

	public static String getTweenTypeDesc(int tweenType) {
		switch (tweenType) {
			case OPACITY: return "Opacity";
			case ORIGIN_XY: return "Origin";
			case POSITION_XY: return "Position";
			case ROTATION: return "Rotation";
			case SCALE_XY: return "Scale";
			default: assert false; return "???";
		}
	}

	public static String getTweenTypeName(int tweenType) {
		switch (tweenType) {
			case OPACITY: return "TweenStudioObject.OPACITY";
			case ORIGIN_XY: return "TweenStudioObject.ORIGIN_XY";
			case POSITION_XY: return "TweenStudioObject.POSITION_XY";
			case ROTATION: return "TweenStudioObject.ROTATION";
			case SCALE_XY: return "TweenStudioObject.SCALE_XY";
			default: assert false; return "???";
		}
	}

	protected void setToState(TweenStudioObjectState state) {
		tweenUpdated(TweenStudioObject.OPACITY, state.opacity);
		tweenUpdated(TweenStudioObject.ORIGIN_XY, state.origin);
		tweenUpdated(TweenStudioObject.POSITION_XY, state.position);
		tweenUpdated(TweenStudioObject.ROTATION, state.rotation);
		tweenUpdated(TweenStudioObject.SCALE_XY, state.scale);
	}
}
