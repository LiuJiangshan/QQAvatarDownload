import java.awt.image.BufferedImage;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

public class DownThread extends Thread
{
	Random random = new Random();

	@Override
	public void run()
	{
		String qq = null;
		File saveFile = null;
		while (true)
		{
			qq = "";
			
			for (int i = 0; i < Main.qqNum; i++)
			{
				if (i == 0)
					qq = (random.nextInt(9) + 1) + "";
				else
					qq += random.nextInt(10);
			}

			saveFile = new File(Main.dir, qq + ".jpg");
			if (saveFile.exists())
				continue;

			String urlStr = "http://qlogo1.store.qq.com/qzone/" + qq + "/" + qq + "/640";

			urlStr = "http://q4.qlogo.cn/g?b=qq&nk=" + qq + "&s=640";

			HttpURLConnection con = null;
			BufferedImage bufferedImage = null;
			try
			{
				con = (HttpURLConnection) new URL(urlStr).openConnection();
				bufferedImage = ImageIO.read(con.getInputStream());
				int w = bufferedImage.getWidth();
				int h = bufferedImage.getHeight();
				if (w > 40 && h > 40)
				{
					System.out.println(qq + ":w=" + w + ",h=" + h);
					ImageIO.write(bufferedImage, "jpg", saveFile);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				bufferedImage = null;
				if (con != null)
				{
					con.disconnect();
					con = null;
				}
			}
		}
	}
}
