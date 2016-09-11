import java.io.File;
import java.util.HashMap;

public class Main
{
	static File dir = new File("head");
	static HashMap<String, String> nullHead = new HashMap<>();
	// qq位数
	static int qqNum;
	// 线程数量
	static int threadNum;

	public static void main(String[] args)
	{
		if (args.length != 2)
		{
			printDoc();
			return;
		}
		else if (!isNumber(args[0]))
		{
			System.out.println("qq位数不是数字");
			return;
		}
		else if (!isNumber(args[1]))
		{
			System.out.println("线程位数不是数字");
			return;
		}
		else
		{
			qqNum = Integer.parseInt(args[0]);
			threadNum = Integer.parseInt(args[1]);
			System.out.println("qq位数:" + qqNum);
			System.out.println("线程数量:" + threadNum);
			System.out.println("5秒后开始");
			for (int i = 5; i >= 0; i--)
				try
				{
					System.out.println(i);
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
				}
			if (!dir.exists())
				dir.mkdir();
			for (int i = 0; i < threadNum; i++)
				new DownThread().start();
			// File[] files = new File(dir, "null").listFiles();
			// for (File f : files)
			// System.out.println(Md5.getMD5(f));
		}
	}

	static boolean isNumber(String numStr)
	{
		try
		{
			Integer.parseInt(numStr);
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	static void printDoc()
	{
		System.out.println("使用:");
		System.out.println("\tjava Main qq位数 线程数量");
	}
}
