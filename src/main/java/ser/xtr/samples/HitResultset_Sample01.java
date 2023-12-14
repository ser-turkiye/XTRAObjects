package ser.xtr.samples;

import com.ser.blueline.IDocument;
import com.ser.blueline.ISession;
import de.ser.doxis4.agentserver.UnifiedAgent;
import ser.xtr.XTRObjects;
import ser.xtr.classies.HitResultset;

public class HitResultset_Sample01 extends UnifiedAgent {
    @Override
    protected Object execute() {
        IDocument document = getEventDocument();
        if(document == null) {
            return resultError("EventInfObj is null.");
        }
        ISession ses = getSes();
        try {
            XTRObjects.setSession(ses);
            HitResultset hrset = new HitResultset();
            hrset.setDatabase("PRJ_DOC");
            hrset.setWhere("");


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
