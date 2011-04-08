package aurelienribon.tweenstudio.tweenables;

import aurelienribon.tweenstudio.TweenStudioObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TweenableSprite extends TweenStudioObject {
	private Sprite sprite;

	public TweenableSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	@Override
	public void getTweenValues(int tweenType, float[] returnValues) {
		switch (tweenType) {
			case OPACITY:
				returnValues[0] = sprite.getColor().a;
				break;

			case ORIGIN_XY:
				returnValues[0] = sprite.getOriginX();
				returnValues[1] = sprite.getOriginY();
				break;

			case POSITION_XY:
				returnValues[0] = sprite.getX();
				returnValues[1] = sprite.getY();
				break;

			case ROTATION: 
				returnValues[0] = sprite.getRotation();
				break;

			case SCALE_XY:
				returnValues[0] = sprite.getScaleX();
				returnValues[1] = sprite.getScaleY();
				break;
				
			default: assert false;
		}
	}

	@Override
	public void tweenUpdated(int tweenType, float[] newValues) {
		switch (tweenType) {
			case OPACITY:
				setOpacity(newValues[0]);
				break;

			case ORIGIN_XY:
				sprite.setOrigin(newValues[0], newValues[1]);
				break;

			case POSITION_XY:
				sprite.setPosition(newValues[0], newValues[1]);
				break;

			case ROTATION:
				sprite.setRotation(newValues[0]);
				break;

			case SCALE_XY:
				sprite.setScale(newValues[0], newValues[1]);
				break;

			default: assert false;
		}
	}

	private void setOpacity(float a) {
		Color c = sprite.getColor();
		c.set(c.r, c.g, c.b, a);
		sprite.setColor(c);
	}
}
