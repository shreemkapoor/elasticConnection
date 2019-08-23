package es.configuration;

import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ESConnection{
	//private final static Logger log = Logger.getLogger(ESConnection.class);

	private static TransportClient client = null;
	

	private static String clusterName;
	private static int port;
	private static String hostName;
	
	public static Client getConnection() {
		
	Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
										  .put("client.transport.sniff", true).build();
try{
	client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress.getByName("10.25.214.209"), 9300));

	System.out.println(client.admin()); 
}
catch (Exception e) {
	e.printStackTrace();
	// TODO: handle exception
   }
	return client;
}

	public static String getClusterName() {
		return clusterName;
	}
	public static void setClusterName(String clusterName) {
		ESConnection.clusterName = clusterName;
	}
	public static int getPort() {
		return port;
	}
	public static void setPort(int port) {
		ESConnection.port = port;
	}
	public static String getHostName() {
		return hostName;
	}
	public static void setHostName(String hostName) {
		ESConnection.hostName = hostName;
	}
	
public static void getconnectionprops(){
		
		Properties prop = new Properties();
		String propFileName = "elastic.properties";
		InputStream inputStream;
		try{

		inputStream = ESConnection.class.getClassLoader().getResourceAsStream(propFileName);
		if (inputStream != null) {
			prop.load(inputStream);
			inputStream.close();
		} 
		}catch(Exception e){
			
		}
		clusterName=prop.getProperty("elasticcluster");
		port=Integer.parseInt(prop.getProperty("elasticport"));
		hostName=prop.getProperty("elastichost");
		
	}
}
