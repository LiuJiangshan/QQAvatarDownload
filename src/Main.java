import java.io.File;

public class Main
{
	static File dir = new File("head");

	public static void main(String[] args)
	{
		if (!dir.exists())
			dir.mkdir();
		for (int i = 0; i < 10; i++)
			new DownThread().start();
		// File[] files = new File(dir, "null").listFiles();
		// for (File f : files)
		// System.out.println(Md5.getMD5(f));
	}
}
