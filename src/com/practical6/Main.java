/*
 * Copyright (c) 2020 Ilya Kremnev
 * MIT License
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.practical6;

import com.Util.IMovable;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Random;

public class Main {

	public static MovableRect[] rects;

	public static void Draw(Graphics grp) {

		grp.setColor(Color.black);
		grp.fillRect(0, 0, 512, 512);
		for (int i = 0; i < 5; i++) {
			MovableRect ourRect = rects[i];

			IMovable.Vector2[] pos = ourRect.GetPositions();
			if (pos[0].x <= 0 || pos[1].x >= 512) {
				ourRect.SetVelocity(ourRect.GetVelocity().mul(new IMovable.Vector2(-1.05f, 1.05f)));
			} else if (pos[0].y <= 0 || pos[1].y >= 512) {
				ourRect.SetVelocity(ourRect.GetVelocity().mul(new IMovable.Vector2(1.05f, -1.05f)));
			}

			ourRect.Update();
			ourRect.Draw(grp);
		}

	}

	public static void main(String[] args) throws Exception {
		final BufferedImage image = new BufferedImage(
				512, 512, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		rects = new MovableRect[5];

		Random random = new Random();

		g.setColor(Color.black);
		g.fillRect(0, 0, 512, 512);

		for (int i = 0; i < 5; i++) {
			MovableRect ourRect = new MovableRect(256 + random.nextInt(100) - 50, 256 + random.nextInt(100) - 50, random.nextInt(100)+10,  random.nextInt(100)+10);
			ourRect.SetVelocity(new IMovable.Vector2(random.nextInt(20) - 10, random.nextInt(20) - 10));
			ourRect.SetColor(new Color(random.nextFloat(),random.nextFloat(),random.nextFloat()));
			ourRect.Draw(g);
			rects[i] = ourRect;
		}

		final JLabel label = new JLabel(new ImageIcon(image));

		ActionListener listener = new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				Draw(g);

				label.repaint();
			}
		};

		Timer timer = new Timer(100, listener);
		timer.start();

		JOptionPane.showMessageDialog(null, label);
	}
}
