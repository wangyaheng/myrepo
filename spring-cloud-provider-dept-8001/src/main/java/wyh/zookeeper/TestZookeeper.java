package wyh.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class TestZookeeper<T> {

	
	public static void main(String[] args) throws IOException {
		ZooKeeper zookeeper = new ZooKeeper("localhost:2181", 3000, new Watcher(){

			@Override
			public void process(WatchedEvent arg0) {
				// 监控所有被触发的时间
				
				System.out.println("==========="+arg0.getPath()+"----"+arg0.getType());
				
			}
			
		});
		
		
		try {
			
			zookeeper.delete("/testRootPath/testChildPathOne",-1); 
			 // 删除父目录节点
			zookeeper.delete("/testRootPath",-1);
			zookeeper.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE,
					   CreateMode.PERSISTENT); 
					 // 创建一个子目录节点
			regist(zookeeper);
			zookeeper.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),
					   Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
			System.out.println(new String(zookeeper.getData("/testRootPath",false,null))); 
					 // 取出子目录节点列表
			System.out.println(zookeeper.getChildren("/testRootPath",true)); 
					 // 修改子目录节点数据
			zookeeper.setData("/testRootPath","modifyChildDataOne".getBytes(),-1); 
			System.out.println("目录节点状态：["+zookeeper.exists("/testRootPath",true)+"]"); 
			zookeeper.setData("/testRootPath","modifyChildDataOne2".getBytes(),-1); 
			
			/*zookeeper.delete("/testRootPath/testChildPathOne",-1); 
			 // 删除父目录节点
			zookeeper.delete("/testRootPath",-1);
			*/
			
			
		} catch (KeeperException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
			
		
	}
	 private static void regist(ZooKeeper zookeeper) throws KeeperException, InterruptedException{
		 Stat exists = zookeeper.exists("/testRootPath", new Watcher(){

				@Override
				public void process(WatchedEvent watchedEvent) {
					System.out.println(watchedEvent.getPath()+"++++++++触发了监听器");
					try {
						regist(zookeeper);
					} catch (KeeperException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			});
	 }
	
	
	
	
}

interface Zkprocess<T>{
	T process();
}
