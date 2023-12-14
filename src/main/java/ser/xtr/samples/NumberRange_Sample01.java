package ser.xtr.samples;

import com.ser.blueline.IInformationObject;
import com.ser.blueline.ISession;
import de.ser.doxis4.agentserver.UnifiedAgent;
import ser.xtr.classies.NumberRange;
import ser.xtr.XTRObjects;

public class NumberRange_Sample01 extends UnifiedAgent {
    @Override
    protected Object execute() {
        IInformationObject infObj = getEventInfObj();
        if(infObj == null) {
            return resultError("EventInfObj is null.");
        }
        ISession ses = getSes();
        try {
            XTRObjects.setSession(ses);
            String counterName = XTRObjects.autoText().with(infObj).run("Transmittal_Out.{ccmPRJCard_code}");

            NumberRange nr = new NumberRange();
            nr.with(infObj);
            for (var i = 0; i < 50; i++){
                String lval = nr.increment(counterName);
                System.out.println(" TEST-01 counter : " + lval);
            }

            for (var i = 0; i < 50; i++){
                String lval = NumberRange.init().with(infObj).increment(counterName);
                System.out.println(" TEST-02 counter : " + lval);
            }

            for (var i = 0; i < 50; i++){
                String lval = XTRObjects.numberRange().with(infObj).increment(counterName);
                System.out.println(" TEST-03 counter : " + lval);
            }

            for (var i = 0; i < 50; i++){
                String lval = XTRObjects.numberRange(infObj).increment(counterName);
                System.out.println(" TEST-04 counter : " + lval);
            }

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
