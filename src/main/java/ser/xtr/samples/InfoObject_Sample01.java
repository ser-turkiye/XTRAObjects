package ser.xtr.samples;

import com.ser.blueline.IDocument;
import com.ser.blueline.ISession;
import de.ser.doxis4.agentserver.UnifiedAgent;
import ser.xtr.XTRObjects;
import ser.xtr.classies.InfoObject;

public class InfoObject_Sample01 extends UnifiedAgent {
    @Override
    protected Object execute() {
        IDocument document = getEventDocument();
        if(document == null) {
            return resultError("EventInfObj is null.");
        }
        ISession ses = getSes();
        try {
            XTRObjects.setSession(ses);
            XTRObjects.setExportPath("c:/tmp2/xtr-objects/");

            InfoObject iobj = new InfoObject(document);
            String testAutoText1 = iobj.autoText("Test {ccmPRJCard_code} {ccmPrjDocDueDate[dd.MM.yyyy]}");
            System.out.println("autoText-1 : " + testAutoText1);

            String testExportDocument1 = iobj.exportDocument("Test {ccmPRJCard_code} {ccmPrjDocDueDate[dd.MM.yyyy]}.%EXTN%");
            System.out.println("exportDocument-1 : " + testExportDocument1);

            System.out.println("hasDescriptor[ccmPRJCard_code]-1 : " + iobj.hasDescriptor("ccmPRJCard_code"));

            String testAutoText2 = XTRObjects.infoObject(document).autoText("Test {ccmPRJCard_code} {ccmPrjDocDueDate[dd.MM.yyyy]}");
            System.out.println("autoText-2 : " + testAutoText2);

            String testExportDocument2 = XTRObjects.infoObject(document).exportDocument("c:/tmp2/sample-export", "%FNAM%.%EXTN%");
            System.out.println("exportDocument-2 : " + testExportDocument2);

            System.out.println("hasDescriptor[ccmPRJCard_code]-2 : " + XTRObjects.infoObject(document).hasDescriptor("ccmPRJCard_code"));

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
