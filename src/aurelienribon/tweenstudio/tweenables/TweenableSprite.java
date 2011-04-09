package aurelienribon.tweenstudio.tweenables;

import aurelienribon.tweenstudio.elements.TweenStudioObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.MathUtils;

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

	@Override
	public void getChangeFromUiMouseDown(int tweenType, float x, float y, float[] returnValues) {
		float centerX = sprite.getX() + sprite.getWidth()/2;
		float centerY = sprite.getY() + sprite.getHeight()/2;

		switch (tweenType) {
			case OPACITY:
				float dist = new Vector2(x - centerX, 0).len();
				float ret = dist / sprite.getWidth();
				ret = ret > 1 ? 1 : ret;
				ret = ret < 0 ? 0 : ret;
				returnValues[0] = ret;
				break;

			case ORIGIN_XY:
				returnValues[0] = x - sprite.getX();
				returnValues[1] = y - sprite.getY();
				break;

			case POSITION_XY:
				returnValues[0] = x;
				returnValues[1] = y;
				break;

			case ROTATION:
				returnValues[0] = MathUtils.atan2(y - centerY, x - centerX) * MathUtils.radiansToDegrees;
				break;

			case SCALE_XY:
				returnValues[0] = (x - centerX) / (sprite.getWidth()/2);
				returnValues[1] = (y - centerY) / (sprite.getHeight()/2);
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
