package aurelienribon.tweenstudio.elements;

public class TweenStudioObjectState {
    public final float[] origin = new float[3];
    public final float[] position = new float[3];
    public final float[] scale = new float[3];
    public final float[] rotation = new float[3];
    public final float[] opacity = new float[3];

	public static TweenStudioObjectState fromObject(TweenStudioObject tso) {
		TweenStudioObjectState state = new TweenStudioObjectState();
		tso.getTweenValues(TweenStudioObject.OPACITY, state.opacity);
		tso.getTweenValues(TweenStudioObject.ORIGIN_XY, state.origin);
		tso.getTweenValues(TweenStudioObject.POSITION_XY, state.position);
		tso.getTweenValues(TweenStudioObject.ROTATION, state.rotation);
		tso.getTweenValues(TweenStudioObject.SCALE_XY, state.scale);
		return state;
	}

	public void applyToObject(TweenStudioObject obj) {
		obj.tweenUpdated(TweenStudioObject.OPACITY, opacity);
		obj.tweenUpdated(TweenStudioObject.ORIGIN_XY, origin);
		obj.tweenUpdated(TweenStudioObject.POSITION_XY, position);
		obj.tweenUpdated(TweenStudioObject.ROTATION, rotation);
		obj.tweenUpdated(TweenStudioObject.SCALE_XY, scale);
	}
}
