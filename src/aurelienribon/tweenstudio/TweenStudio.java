package aurelienribon.tweenstudio;

public class TweenStudio {
    public static Editor edit(Editor editor) {
		editor.run();
		return editor;
	}

	public static Player play(Player player) {
		player.run();
		return player;
	}
}
