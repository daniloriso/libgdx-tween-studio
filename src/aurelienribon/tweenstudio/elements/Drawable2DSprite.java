package aurelienribon.tweenstudio.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Drawable2DSprite extends Drawable2D {
	private Sprite sprite;

	public Drawable2DSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	@Override
	public int getTweenValues(int tweenType, float[] returnValues) {
		switch (tweenType) {
			case ORIGIN_XY:
				returnValues[0] = sprite.getOriginX();
				returnValues[1] = sprite.getOriginY();
				return 2;

			case POSITION_XY:
				returnValues[0] = sprite.getX();
				returnValues[1] = sprite.getY();
				return 2;

			case SCALE_XY:
				returnValues[0] = sprite.getScaleX();
				returnValues[1] = sprite.getScaleY();
				return 2;

			case ROTATION: 
				returnValues[0] = sprite.getRotation();
				return 1;
				
			case OPACITY:
				returnValues[0] = sprite.getColor().a;
				return 1;

			default:
				assert false;
				return -1;
		}
	}

	@Override
	public void tweenUpdated(int tweenType, float[] newValues) {
		switch (tweenType) {
			case OPACITY:
				Color c = sprite.getColor();
				c.set(c.r, c.g, c.b, newValues[0]);
				sprite.setColor(c);
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

//	@Override
//	public void getChangeFromUiMouseDown(int tweenType, Vector2 pos, Vector2 lastPos, float[] returnValues) {
//		float centerX = sprite.getX() + sprite.getWidth()/2;
//		float centerY = sprite.getY() + sprite.getHeight()/2;
//
//		switch (tweenType) {
//			case OPACITY:
//				float dist = new Vector2(pos.x - centerX, 0).len();
//				float ret = dist / sprite.getWidth();
//				ret = ret > 1 ? 1 : ret;
//				ret = ret < 0 ? 0 : ret;
//				returnValues[0] = ret;
//				break;
//
//			case ORIGIN_XY:
//				returnValues[0] = pos.x - sprite.getX();
//				returnValues[1] = pos.y - sprite.getY();
//				break;
//
//			case POSITION_XY:
//				returnValues[0] = sprite.getX() + pos.x - lastPos.x;
//				returnValues[1] = sprite.getY() + pos.y - lastPos.y;
//				break;
//
//			case ROTATION:
//				float angle = MathUtils.atan2(pos.y - centerY, pos.x - centerX) * MathUtils.radiansToDegrees;
//				float lastAngle = MathUtils.atan2(lastPos.y - centerY, lastPos.x - centerX) * MathUtils.radiansToDegrees;
//				returnValues[0] = sprite.getRotation() + angle - lastAngle;
//				break;
//
//			case SCALE_XY:
//				returnValues[0] = (pos.x - centerX) / (sprite.getWidth()/2);
//				returnValues[1] = (pos.y - centerY) / (sprite.getHeight()/2);
//				break;
//
//			default: assert false;
//		}
//	}
}
