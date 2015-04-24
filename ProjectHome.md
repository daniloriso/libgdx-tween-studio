# Introduction #

TweenStudio is a library that enables you to create animations and motions (aka "Tweens") with a visual editor. It is currently in an alpha phase. You won't find a lot of information here, so I **strongly** encourage you to go look at the provided example. Play with the test executable, and look at its code ;)

Just click on your game render to update the target values of a motion, you don't need to use the spinners, that's the strength of the tool. Next revision will go a lot deeper in direct render interactions!

# Warning ! #

Since this is only an alpha release, the UI will deeply change during the next updates. I plan to show the timeline with a keyframed panel like in every commercial product involving animation. So I won't produce youtube videos for some "how to use this version of the studio". However, the Ui should be self explanatory and simple enough.

# Dependencies #

This library depends on the following libraries:
-- LibGDX (with JoGL backend) : http://code.google.com/p/libgdx/
-- LGDX-Tween-Engine : http://code.google.com/p/libgdx-tween-engine/

You can find all of the requirements in the test executable archive, or in the sources, under the "test-libs" folder.

# Current version screenshot #

![http://i1194.photobucket.com/albums/aa367/Obligen/Sanstitre-3.jpg](http://i1194.photobucket.com/albums/aa367/Obligen/Sanstitre-3.jpg)

# Micro tutorial #

A studio can be either used to edit a timeline, or to play it. You should start by editing it if you don't have one :)

```
TweenStudio studio = createStudio();
studio.edit();
//studio.play();
```


The creation of a TweenStudio involves this kind of code:

```
private TweenStudio createStudio() {
	return new TweenStudio(
		// Initial timeline (just return `TweenSequence.set()` if you don't have one)
		TweenSequence.set(
			Tween.to(logoSpriteTween1, TweenStudioObject.POSITION_XY, Cubic.INOUT, 500, -1.0625f, 3.5625f).delay(0),
			Tween.to(logoSpriteTween1, TweenStudioObject.SCALE_XY, Cubic.INOUT, 500, 0.3f, 0.3f).delay(-400),
			Tween.to(logoSpriteTween1, TweenStudioObject.POSITION_XY, Cubic.INOUT, 800, -0.96875f, -5.96875f).delay(0),
			Tween.to(logoSpriteTween1, TweenStudioObject.SCALE_XY, Cubic.INOUT, 800, 1.0f, 1.0f).delay(-600),
			Tween.to(logoSpriteTween1, TweenStudioObject.POSITION_XY, Cubic.INOUT, 500, -1.0f, -1.0f).delay(0),
			Tween.to(logoSpriteTween1, TweenStudioObject.SCALE_XY, Cubic.INOUT, 500, 3.53125f, 3.40625f).delay(-300),
			Tween.to(logoSpriteTween1, TweenStudioObject.OPACITY, Cubic.INOUT, 500, 0.203125f).delay(-500)
		),

		// Editor that you must implement
		new TweenStudioEditor() {
			// These names will be those used when exporting your timeline
			@Override protected void getFieldNames(Map<TweenStudioObject, String> map) {
				map.put(logoSpriteTween1, "logoSpriteTween1");
				map.put(logoSpriteTween2, "logoSpriteTween2");
				map.put(logoSpriteTween3, "logoSpriteTween3");
			}

			// If you have an InputProcessor already set, declare it here
			@Override
			protected InputProcessor getCurrentInputProcessor() {
				return null;
			}

			// A method to get a correct world position from a screen input
			@Override protected Vector2 getPositionFromInput(Vector2 inputPos) {
				inputPos.y = Gdx.graphics.getHeight() - inputPos.y;

				float metersPerPixel = SCREEN_WIDTH_METERS / Gdx.graphics.getWidth();
				inputPos.mul(metersPerPixel * camera.zoom);

				float screenHeightMeters = SCREEN_WIDTH_METERS * (float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
				inputPos.x += camera.position.x - SCREEN_WIDTH_METERS / 2 * camera.zoom;
				inputPos.y += camera.position.y - screenHeightMeters / 2 * camera.zoom;

				return inputPos;
			}
		}
	);
}
```