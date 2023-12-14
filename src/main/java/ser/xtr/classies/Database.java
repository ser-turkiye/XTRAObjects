package ser.xtr.classies;

import com.ser.blueline.IDatabase;
import com.ser.blueline.IDescriptor;
import com.ser.blueline.IDocumentServer;
import com.ser.blueline.ISession;
import ser.xtr.XTRObjects;

import java.util.function.Function;

public class Database {
    ISession session;
    IDocumentServer server;
    String principalName;

    IDatabase database;
    public
    Database(IDatabase db) throws Exception {
        session = XTRObjects.getSession();
        if (session == null) {
            throw new Exception("Please set session with XTRObjects.init function.");
        }
        server = XTRObjects.getServer();
        principalName = XTRObjects.getPrincipalName();
        if (db == null) {
            throw new Exception("Db is null.");
        }
        database = db;
    }
    public static Database
    init(IDatabase db) throws Exception {
        return new Database(db);
    }
    public IDatabase
    get() throws Exception {
        return database;
    }
    public static Database
    init(String dbn) throws Exception {
        IDatabase db = XTRObjects.getDatabase(dbn);
        return new Database(db);
    }
    public void
    callAssignedDescriptors(Function<IDescriptor, Boolean> func) throws Exception {
        String[] dis = database.getAssignedDescriptorIDs();
        for(String di : dis){
            IDescriptor d = server.getDescriptor(di, session);
            if(d == null){continue;}
            if(!func.apply(d)){break;}
        }
    }
}
