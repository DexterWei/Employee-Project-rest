package edu.sjsu.cmpe282.hw1.dao;

import com.mongodb.*;
import edu.sjsu.cmpe282.hw1.model.employee;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by dexter on 2/28/16.
 */
@Repository("employeedao")
public class employeeDAO {
    private static MongoClient client=null;

    public static DBCollection Connect(){
        if(client==null) {
            try {
                client = new MongoClient("localhost", 27017);
            } catch (UnknownHostException ex) {
                System.err.println(ex);
            }
        }
        return client.getDB("cmpe282XiaoWei742").getCollection("employee");
    }

    public static void DisConnect(){
        client.close();
        client = null;
    }

    public static boolean createEmployee(int id, String fname, String lname){
        DBCollection clnt = Connect();
        BasicDBObject empdb = new BasicDBObject().append("id",id);
        DBCursor rst = clnt.find(empdb);
        boolean rtn;
        if(rst.count()==0){
            empdb.append("firstName",fname).append("lastName",lname);
            clnt.insert(empdb);
            rtn = true;
        }else{
            rtn = false;
        }
        DisConnect();
        return rtn;
    }

    public static boolean updateEmployee(int id, String fname, String lname){
        DBCollection clnt = Connect();
        BasicDBObject empdb = new BasicDBObject().append("id",id);
        DBCursor rst = clnt.find(empdb);
        boolean rtn;
        if(rst.count() != 0){
            DBObject obj = rst.next();
            if(fname != null){
                obj.put("firstName",fname);
            }
            if(lname != null){
                obj.put("lastName",lname);
            }
            clnt.update(empdb,obj);
            rtn = true;
        }else{
            rtn = false;
        }
        DisConnect();
        return rtn;
    }

    public static boolean deleteEmployee(int id){
        DBCollection clnt = Connect();
        BasicDBObject empdb = new BasicDBObject().append("id",id);
        DBCursor rst = clnt.find(empdb);
        boolean rtn;
        if(rst.count() != 0){
            clnt.remove(empdb);
            rtn = true;
        }else{
            rtn = false;
        }
        DisConnect();
        return rtn;
    }

    public static employee findEmployeeById(int id){
        DBCollection clnt = Connect();
        BasicDBObject empdb = new BasicDBObject().append("id",id);
        DBCursor rst = clnt.find(empdb);
        employee rtn;
        if(rst.count()==0){
            rtn = null;
        }else{
            DBObject e = rst.next();
            rtn = new employee((Integer)e.get("id"),(String)e.get("firstName"),(String) e.get("lastName"));
        }
        DisConnect();
        return rtn;
    }

    public static ArrayList<employee> findAllEmployees(){
        DBCollection clnt = Connect();
        DBCursor rst = clnt.find();
        ArrayList<employee> rtn = new ArrayList<employee>();
        while(rst.hasNext()){
            DBObject e = rst.next();
            rtn.add(new employee((Integer)e.get("id"),(String)e.get("firstName"),(String) e.get("lastName")));
        }
        DisConnect();
        return rtn;
    }
}
