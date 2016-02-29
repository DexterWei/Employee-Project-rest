package edu.sjsu.cmpe282.hw1.dao;

import com.mongodb.*;
import edu.sjsu.cmpe282.hw1.model.employee;
import edu.sjsu.cmpe282.hw1.model.project;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by dexter on 2/29/16.
 */
@Repository("projectdao")
public class projectDAO {
    private static MongoClient client=null;

    public static MongoClient Connect(){
        if(client!=null)
            return client;
        try{
            client = new MongoClient("localhost" , 27017);
        }catch(UnknownHostException ex){
            System.err.println(ex);
        }
        return client;
    }

    public static void DisConnect(){
        client.close();
        client = null;
    }

    public static boolean createProject(int id, String name, float budget){
        Connect();
        DB db = client.getDB("test");
        DBCollection clnt = db.getCollection("project");
        BasicDBObject prjdb = new BasicDBObject().append("id",id);
        DBCursor rst = clnt.find(prjdb);
        boolean rtn;
        if(rst.count()==0){
            prjdb.append("name",name).append("budget",budget);
            clnt.insert(prjdb);
            rtn = true;
        }else{
            rtn = false;
        }
        DisConnect();
        return rtn;
    }

    public static boolean updateProject(int id, String name, Float budget){
        Connect();
        DB db = client.getDB("test");
        DBCollection clnt = db.getCollection("project");
        BasicDBObject prjdb = new BasicDBObject().append("id",id);
        DBCursor rst = clnt.find(prjdb);
        boolean rtn;
        if(rst.count() != 0){
            DBObject obj = rst.next();
            if(name != null){
                obj.put("name",name);
            }
            if(budget != null){
                obj.put("budget",budget);
            }
            clnt.update(prjdb,obj);
            rtn = true;
        }else{
            rtn = false;
        }
        DisConnect();
        return rtn;
    }

    public static boolean deleteProject(int id){
        Connect();
        DB db = client.getDB("test");
        DBCollection clnt = db.getCollection("project");
        BasicDBObject prjdb = new BasicDBObject().append("id",id);
        DBCursor rst = clnt.find(prjdb);
        boolean rtn;
        if(rst.count() != 0){
            clnt.remove(prjdb);
            rtn = true;
        }else{
            rtn = false;
        }
        DisConnect();
        return rtn;
    }

    public static project findProjectById(int id){
        Connect();
        DB db = client.getDB("test");
        DBCollection clnt = db.getCollection("project");
        BasicDBObject prjdb = new BasicDBObject().append("id",id);
        DBCursor rst = clnt.find(prjdb);
        project rtn;
        if(rst.count()==0){
            rtn = null;
        }else{
            DBObject e = rst.next();
            rtn = new project((Integer)e.get("id"), (String)e.get("name"), ((Number)e.get("budget")).floatValue());
        }
        DisConnect();
        return rtn;
    }

    public static ArrayList<project> findAllProjects(){
        Connect();
        DB db = client.getDB("test");
        DBCollection clnt = db.getCollection("project");
        DBCursor rst = clnt.find();
        ArrayList<project> rtn = new ArrayList<project>();
        while(rst.hasNext()){
            DBObject e = rst.next();
            rtn.add(new project((Integer)e.get("id"), (String)e.get("name"), ((Number)e.get("budget")).floatValue()));
        }
        DisConnect();
        return rtn;
    }
}
