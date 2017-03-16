package com.ctis.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public final class ImageUtil {
	//字符个数
	private static final int SIZE = 4;
	//干扰线个数
	private static final int LINES = 5;
	//图片宽度
	private static final int WIDTH = 60;
	//图片高度
	private static final int HEIGHT = 22;
	//字体大小
	private static final int FONT_SIZE = 20;

	public static Map<String, BufferedImage> createImage() {
		StringBuffer sb = new StringBuffer();
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics graphic = image.getGraphics();
		//设置背景色
		graphic.setColor(new Color(196,211,222));
		//填充背景色
		graphic.fillRect(0, 0, WIDTH, HEIGHT);
		// 画随机字符
		for (int i = 1; i <= SIZE; i++) {
			String ranChar = getRandomChar();
			graphic.setColor(getRandomColor());
			graphic.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
			graphic.drawString(ranChar, (i - 1) * WIDTH / SIZE, (HEIGHT + FONT_SIZE) / 2-2);
			sb.append(ranChar);
		}
		// 画干扰线
		Random ran = new Random();
		for (int i = 1; i <= LINES; i++) {
			graphic.setColor(getRandomColor());
			graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
		}
		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		map.put(sb.toString(), image);
		System.out.println(sb.toString());
		return map;
	}

	//获取随机颜色
	private static Color getRandomColor() {
		Random ran = new Random();
		Color color = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
		return color;
	}

	//获取随机字符串
	private static String getRandomChar() {
		//生成一个0、1、2的随机数字
		int rand = (int) Math.round(Math.random() * 2);
		long itmp = 0;
		char ctmp = '\u0000';
		switch (rand) {
		//生成大写字母
		case 1:
			itmp = Math.round(Math.random() * 25 + 65);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);
			//生成小写字母
		case 2:
			itmp = Math.round(Math.random() * 25 + 97);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);
			//生成数字
		default:
			itmp = Math.round(Math.random() * 9);
			return itmp + "";
		}
	}

	public static InputStream getInputStream(BufferedImage image) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
		encoder.encode(image);
		byte[] imageBts = bos.toByteArray();
		InputStream in = new ByteArrayInputStream(imageBts);
		return in;
	}
}
