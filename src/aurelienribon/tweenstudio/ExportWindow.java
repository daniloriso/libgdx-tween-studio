package aurelienribon.tweenstudio;

import aurelienribon.tweenstudio.elements.TweenStudioObject;
import aurelienribon.libgdx.tween.Tween;
import aurelienribon.libgdx.tween.TweenSequence;
import java.util.ArrayList;
import java.util.List;

public class ExportWindow extends javax.swing.JFrame {

    public ExportWindow() {
        initComponents();
		setSize(500, 500);
    }

	public void setSequence(TweenSequence sequence, TweenStudio studio) {
		Tween[] tweens = correctTimeline(sequence);

		StringBuilder sb = new StringBuilder();
		sb.append("TweenSequence.set(\n");
		if (sequence.getTweens().size > 0) {
			for (Tween tween : tweens) {
				sb.append("\tTween.to(");
				sb.append(studio.getNameFromObject((TweenStudioObject) tween.getTarget())).append(", ");
				sb.append(TweenStudioObject.getTweenTypeName(tween.getTweenType())).append(", ");
				sb.append(tween.getEquation().toString()).append(", ");
				sb.append(tween.getDurationMillis()).append(", ");
				for (int i=0; i<tween.getCombinedTweenCount(); i++)
					sb.append(tween.getTargetValues()[i]).append("f, ");
				sb.delete(sb.length()-3, sb.length());
				sb.append("f).delay(").append(tween.getDelayMillis()).append("),\n");
			}
			sb.deleteCharAt(sb.length()-2);
		}
		sb.append(")");
		textArea.setText(sb.toString());
	}

	private Tween[] correctTimeline(TweenSequence sequence) {
		List<Tween> tweens = new ArrayList<Tween>();

		for (Tween tween : sequence.getTweens())
			tweens.add(tween);

		for (int i=tweens.size()-1; i>0; i--) {
			int correction = tweens.get(i-1).getDurationMillis() + tweens.get(i-1).getDelayMillis();
			tweens.get(i).delay(-correction);
		}

		return tweens.toArray(new Tween[0]);
	}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tween Studio Export");

        jTextPane1.setEditable(false);
        jTextPane1.setForeground(new java.awt.Color(102, 102, 102));
        jTextPane1.setText("This is your whole animation represented as a TweenSequence.\n\nJust pass it to your TweenStudio constructor. The studio will analyze it on next launch and build the initial timeline from it.\n\nIf you are happy with this animation, you can also change \"play()\" instead of \"edit\" on your studio instance if you do not want the editor to show up anymore.");
        jTextPane1.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jTextPane1.setOpaque(false);
        getContentPane().add(jTextPane1, java.awt.BorderLayout.SOUTH);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("Monospaced", 0, 12));
        textArea.setRows(5);
        textArea.setTabSize(4);
        jScrollPane1.setViewportView(textArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

}
