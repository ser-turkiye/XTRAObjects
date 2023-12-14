package ser.xtr.samples;

import com.ser.blueline.IDocument;
import com.ser.blueline.ISession;
import de.ser.doxis4.agentserver.UnifiedAgent;
import ser.xtr.XTRObjects;
import ser.xtr.classies.AutoText;

public class AutoText_Sample01 extends UnifiedAgent {
    @Override
    protected Object execute() {
        IDocument document = getEventDocument();
        if(document == null) {
            return resultError("EventInfObj is null.");
        }
        ISession ses = getSes();
        try {
            XTRObjects.setSession(ses);

            String testAutoText = "*********** {XYZ} {ccmPRJCard_code} {ccmPrjDocDueDate/dd.MM.yyyy}";

            AutoText autoText = new AutoText();
            autoText.parameter("XYZ", "T.E.S.T");
            autoText.with(document);
            String testName1 = autoText.run(testAutoText);
            System.out.println("test1 : " + testName1);

            String testName2 = AutoText.init().param("XYZ", "T.E.S.T").with(document).run(testAutoText);
            System.out.println("test2 : " + testName2);

            String testName3 = XTRObjects.autoText().param("XYZ", "T.E.S.T").with(document).run(testAutoText);
            System.out.println("test3 : " + testName3);

            String testName4 = XTRObjects.autoText(document).param("XYZ", "T.E.S.T").run(testAutoText);
            System.out.println("test4 : " + testName4);

            String testName5 = XTRObjects.autoText(document, testAutoText);
            System.out.println("test5 : " + testName5);


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
