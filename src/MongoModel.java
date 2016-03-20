
import java.net.UnknownHostException;


import javax.swing.JOptionPane;

import com.mongodb.*;

public class MongoModel 
{
	private DB dB;
	private DBCollection dBCollection;
	//private BasicDBObject basicDBObject;
	
	public MongoModel()
	{
		try {
			this.dB = (new MongoClient("localhost", 27017)).getDB("SonarPingDatabase");
			
			this.dBCollection = dB.getCollection("UserDB");
			//this.basicDBObject = new BasicDBObject();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	public void insertMongoDB(String first, String last, String email, String pass, String type)	
	{	
		BasicDBObject basicDBObject;
		
		try{
			basicDBObject = new BasicDBObject(); 
			basicDBObject.append("First Name",first);
			basicDBObject.append("Last Name",last);
			basicDBObject.append("Email",email);
			basicDBObject.append("Password",pass);
			basicDBObject.append("Type",type);
			this.dBCollection.insert(new DBObject[] {basicDBObject});		
		}
		
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		close();
	
	}
	
	public void deleteMongoDB(String identifier)
	{
		BasicDBObject basicDBObject;
		
		try
		{	
			basicDBObject = new BasicDBObject();		
			basicDBObject.append("First Name", identifier);
			this.dBCollection.remove(basicDBObject);
		}		
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, identifier);
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		close();
	}
	
	public void updateMongoDB(String first, String last, String email, String pass, String type)
	{
		BasicDBObject basicDBObject;
		try{
			basicDBObject = new BasicDBObject();
			basicDBObject.put("First Name", first);
			this.dBCollection.remove(basicDBObject);
			
			basicDBObject = null;
			basicDBObject = new BasicDBObject(); 
			
			basicDBObject.append("First Name",first);
			basicDBObject.append("Last Name",last);
			basicDBObject.append("Email",email);
			basicDBObject.append("Password",pass);
			basicDBObject.append("Type",type);
			
			this.dBCollection.insert(new DBObject[] {basicDBObject});	
		}
		
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		close();
	}
	
	public String selectMongoDB(String key, String value)
	{
		BasicDBObject basicDBObject;
		String str = "";
		
		basicDBObject = new BasicDBObject();
		basicDBObject.put(key, value);
		DBCursor dbCursor = this.dBCollection.find(basicDBObject);		
		while (dbCursor.hasNext()) 
			str = str + " | " + dbCursor.next();
		
		
		close();
		return str;
	}
	
	
	
	public String browseMongoDB(MongoView theView, MongoModel theModel)
	{
		String str = "";
		String firstn, lastn, email, passwd, type/*, id*/;
		DBCursor dbCursor = dBCollection.find();
		DBObject obj;
		
		int rowCount = theView.tblmodelView.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			theView.tblmodelView.removeRow(i);
		}
		
		while (dbCursor.hasNext()) {
			//System.out.println(dbCursor.next());
			//str = str + "\n" + dbCursor.next();
			obj = dbCursor.next();
			str = str +	"\n" + obj;
			//theView.userid.setText("");
        	//theView.userid.setText(obj.get("_id").toString());
        	//JOptionPane.showMessageDialog(null, obj.get("_id").toString());
			firstn = (String)obj.get("First Name");
			lastn  = (String)obj.get("Last Name");
			email  = (String)obj.get("Email");
			passwd = (String)obj.get("Password");
			type   = (String)obj.get("Type");
			//id     = obj.get("_id").toString();
			theView.tblmodelView.addRow(new Object[] {firstn, lastn, email, passwd, type/*, id*/});			
		}
		
		close();
		return str;
	}
	
	public String getAllEmails()
	{
		String str = "";
		String placeHolderString = "";
		String finalEmailString = "";
		
		
		BasicDBObject userTypeQuery = new BasicDBObject();
		BasicDBObject emailQuery = new BasicDBObject();
		
		
		userTypeQuery.append("Type", "DEPENDENT");
		emailQuery.append("Email","1");
		
		
		
		DBCursor dbCursor = dBCollection.find(userTypeQuery,emailQuery);
		
		while (dbCursor.hasNext()){ 
			str = ""+dbCursor.next();
			placeHolderString = (str.substring(str.indexOf(","), str.length()-1));
			finalEmailString += placeHolderString.substring(placeHolderString.indexOf(":"), placeHolderString.length()-1).substring(3) + ",";
		}
		
		
		close();
		
		return finalEmailString.substring(0, finalEmailString.length()-1);
	}
	
	public int removeallMongo()
	{
		int i=0;
		
		this.dBCollection.remove(new BasicDBObject());
		return i;
	}
	
	public void close(){
		//this.basicDBObject = null;
	}

}
