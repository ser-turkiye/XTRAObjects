package ser.xtr.samples;

import com.ser.blueline.IDatabase;
import com.ser.blueline.ISession;
import de.ser.doxis4.agentserver.UnifiedAgent;
import ser.xtr.XTRObjects;

public class _XTRObjects_Sample01 extends UnifiedAgent {
    @Override
    protected Object execute() {
        ISession ses = getSes();
        try {
            XTRObjects.setSession(ses);
            XTRObjects.callDatabases((db) -> {
                System.out.println(" DB ::: " + db.getName());
                return true;
            });

            IDatabase prjDB = XTRObjects.getDatabase("PRJ_DOC");
            if(prjDB != null) {
                System.out.println(" PROJECT-DB ::: " + prjDB.getName());
                XTRObjects.database(prjDB).callAssignedDescriptors((desc) -> {
                    System.out.println(" --> Desc : " + desc.getName());
                    return true;
                });
            }

            XTRObjects.database("PRJ_DOC").callAssignedDescriptors((desc) -> {
                System.out.println(" --> Desc : " + desc.getName());
                return true;
            });
        } catch (Exception e) {
            System.out.println("Exception       : " + e.getMessage());
            System.out.println("    Class       : " + e.getClass());
            System.out.println("    Stack-Trace : " + e.getStackTrace() );
            return resultRestart("Exception : " + e.getMessage(),10);
        }
        System.out.println("Finished");
        return resultSuccess("Ended successfully");
    }
}
