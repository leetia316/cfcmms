package com.jfok.cfcmms.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.util.Random;


public class IdentifyingCodeServlet {

	public static BufferedImage generateIdentifyingCode(String value) {
		Random rand = new Random(System.currentTimeMillis());
		// 图片宽高
		int width = 90 ;//+ rand.nextInt(25);
		int height = 30 ; //+ rand.nextInt(10);
		Graphics2D g = null;
	
			BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			g = bimage.createGraphics();
			// 设置随机背景色
//			Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
			Color color = new Color(255,255,255);

			// 填充深色背景
			g.setColor(color.darker());
			g.fillRect(0, 0, width, height);
			// 设置字体
			g.setFont(new Font("arial", Font.BOLD, 36));
			// 随机生成字符,根据截取的位数决定产生的数字
			int w = (g.getFontMetrics()).stringWidth(value);
			int d = (g.getFontMetrics()).getDescent();
			int a = (g.getFontMetrics()).getMaxAscent();
			int x = 0, y = 0;
			// 设置随机线条,15这个数值越大图片中线条越稀蔬
			for (int i = 0; i < height;) {
				i += 8 + rand.nextInt(15);
				g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
				g.drawLine(x, y + i, width, y + i);
			}
			// reset x and y
			x = 0;
			y = 0;
			// 设置随机线条,15这个数值越大图片中线条越稀蔬
			for (int i = 0; i < height;) {
				i += 8 + rand.nextInt(15);
				g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
				g.drawLine(x, y + d - i, width + w, height + d - i);
			}
			// 展示验证码中颜色,随机
			g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)).brighter());
			// 设置文字出现位置为中央
			x = width / 2 - w / 2;
			y = height / 2 + a / 2 - 6;
			// 文字变形设置
			AffineTransform fontAT = new AffineTransform();
			int xp = x - 2;
			// 每个文字都变形
			for (int c = 0; c < value.length(); c++) {
				// 产生弧度
				int rotate = rand.nextInt(25);
				fontAT.rotate(rand.nextBoolean() ? Math.toRadians(rotate) : -Math.toRadians(rotate / 2));
				Font fx = new Font(new String[] { "Times New Roman", "Verdana", "arial" }[rand.nextInt(2)],
						rand.nextInt(5), 20 + rand.nextInt(16)).deriveFont(fontAT);
				g.setFont(fx);
				// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
				Random random = new Random();
				int red = random.nextInt(255);
				int green = random.nextInt(255);
				int blue = random.nextInt(255);
				// 用随机产生的颜色将验证码绘制到图像中。
				g.setColor(new Color(red, green, blue));
				String ch = String.valueOf(value.charAt(c));
				int ht = rand.nextInt(3);
				// 打印字并移动位置
				g.drawString(ch, xp, y + (rand.nextBoolean() ? -ht : ht));
				// 移动指针.
				xp += g.getFontMetrics().stringWidth(ch) + 2;
			}
			// 打印出图片
	
				g.dispose();
		
		return bimage;
	}

}
