package cn.itcast.demo;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class Test {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
		//1、加载配置文件
		ClientGlobal.init("E:/store-workspace/fastDFS-demo/src/main/resources/fdfs_client.conf");
		//2、构建一个管理者客户端
		TrackerClient client = new TrackerClient();
		//3、连接管理者服务端
		TrackerServer trackerServer = client.getConnection();
		//4、声明存储服务端
		StorageServer storageServer = null;
		//5、获取存储服务的客户端对象
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//6、上传文件
		String[] files = storageClient.upload_file("C:\\Users\\win10\\Documents\\WXWork\\1688851577364273\\Cache\\Image\\2019-04\\$M60[EW6YK}4B`W}WZ@W7`O.gif", "gif", null);
		//7、显示上传结果
		for (String filString : files) {
			System.out.println(filString);
		}
	}
}
