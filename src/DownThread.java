import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class DownThread extends Thread
{
	Random random = new Random();

	boolean checkMd5(File file)
	{
		long fileLength = file.length();
		if (fileLength == 1034 || fileLength == 2986 || fileLength == 2055)
		{
			String md5 = Md5.getMD5(file);
			if (md5.equals("c1bb6a4abacecb1ff0bbf838ab3a4e08") || md5.equals("9e981dc7788599d9372c8381d776c554")
					|| md5.equals("3b42d62450ffcb05428b958512bea22"))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void run()
	{
		String qq = null;
		File saveFile = null;
		while (true)
		{
			qq = "";
			int numCount = random.nextInt(5) + 6;
			numCount = 10;
			for (int i = 0; i < numCount; i++)
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
			HttpURLConnection con = null;
			OutputStream out = null;
			InputStream in = null;
			try
			{
				con = (HttpURLConnection) new URL(urlStr).openConnection();
				in = con.getInputStream();
				out = new FileOutputStream(saveFile);
				int i = 0;
				while ((i = in.read()) != -1)
				{
					out.write(i);
				}
				out.flush();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (in != null)
				{
					try
					{
						in.close();
						in = null;
					}
					catch (IOException e)
					{
					}
				}
				if (out != null)
				{
					try
					{
						out.close();
						out = null;
					}
					catch (IOException e)
					{
					}
				}
				if (con != null)
				{
					con.disconnect();
					con = null;
				}
			}
			if (saveFile != null && saveFile.exists())
			{
				if (checkMd5(saveFile))
					System.out.println(qq);
				else
					saveFile.delete();
			}
		}
	}
}
