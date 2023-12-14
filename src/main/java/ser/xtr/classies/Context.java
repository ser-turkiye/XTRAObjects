package ser.xtr.classies;

import com.ser.blueline.IDocumentServer;
import com.ser.blueline.ISerClassFactory;
import com.ser.blueline.ISession;
import ser.xtr.XTRObjects;

import java.util.ArrayList;
import java.util.List;

public class Context {
    ISession session;
    IDocumentServer server;
    String principalName;

    ISerClassFactory factory;
    List<String> databases = new ArrayList<String>();
    int maxHits = 0;
    public Context() throws Exception {
        session = XTRObjects.getSession();
        if (session == null) {
            throw new Exception("Please set session with XTRObjects.init function.");
        }
        server = XTRObjects.getServer();
        principalName = XTRObjects.getPrincipalName();
    }
}
