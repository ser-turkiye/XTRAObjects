package ser.xtr.classies;

import com.ser.blueline.*;
import com.spire.ms.System.Exception;
import org.json.JSONObject;
import ser.xtr.XTRObjects;

import java.util.ArrayList;
import java.util.List;

public class HitResultset {
    ISession session;
    IDocumentServer server;
    String principalName;

    ISerClassFactory factory;
    JSONObject databases;
    String where;
    String sort;
    int maxHits = 0;
    IDocumentHitList hits;
    public
    HitResultset() throws Exception {
        load();
    }
    HitResultset(String[] dbn, String qwhr) throws Exception {
        load();
        setDatabases(dbn);
        setWhere(qwhr);
    }
    HitResultset(String[] dbn, String qwhr, String sort) throws Exception {
        load();
        setDatabases(dbn);
        setWhere(qwhr);
        setSort(sort);
    }
    HitResultset(String[] dbn, String qwhr, String sort, int mhit) throws Exception {
        load();
        setDatabases(dbn);
        setWhere(qwhr);
        setSort(sort);
        setMaxHits(mhit);
    }
    public void
    load() throws Exception {
        session = XTRObjects.getSession();
        if (session == null) {
            throw new Exception("Please set session with XTRObjects.init function.");
        }
        server = XTRObjects.getServer();
        factory = server.getClassFactory();
        principalName = XTRObjects.getPrincipalName();
        databases = new JSONObject();
        where = "";
        sort = "";
    }
    public void
    run(){
        ISerClassFactory fac = server.getClassFactory();
        IQueryParameter que = fac.getQueryParameterInstance(
                session,
                getDbNames() ,
                fac.getExpressionInstance(where) ,
                null,null);

        if(maxHits > 0) {
            que.setMaxHits(maxHits);
            que.setHitLimit(maxHits + 1);
            que.setHitLimitThreshold(maxHits + 1);
        }
        if(!sort.isEmpty()){
            IOrderByExpression oexr = fac.getOrderByExpressionInstance(
                    session.getDocumentServer().getInternalDescriptor(session, sort), true);
            que.setOrderByExpression(oexr);
        }

        hits = (que.getSession() != null ? que.getSession().getDocumentServer().query(que, que.getSession()) : null);
    }
    public static HitResultset
    init() throws Exception {
        return new HitResultset();
    }
    public void
    setDatabases(String[] dbs){
        for(String dbn : dbs){
            IDatabase db = session.getDatabaseByName(dbn);
            if(db == null){continue;}
            databases.put(db.getName(), db);
        }
    }
    public void
    setDatabase(String dbn){
        databases = new JSONObject();
        IDatabase db = session.getDatabaseByName(dbn);
        if(db != null){
            databases.put(db.getName(), db);
        }
    }
    public void
    setWhere(String whr){
        where = whr;
    }
    public void
    setSort(String srt){
        sort = srt;
    }
    public String[]
    getDbNames(){
        List<String> rtrn = new ArrayList<>();
        rtrn.addAll(databases.keySet());
        return rtrn.toArray(new String[rtrn.size()]);
    }
    public void
    setMaxHits(int mhit){
        maxHits = mhit;
    }
    public int
    getMaxHits(){
        return maxHits;
    }
    public HitResultset
    addDb(String db){
        setDatabase(db);
        return this;
    }
    public HitResultset
    setDbs(String[] dbs){
        setDatabases(dbs);
        return this;
    }
    public HitResultset
    maxHits(int mhit){
        maxHits = mhit;
        return this;
    }
}
