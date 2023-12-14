package junit

import de.ser.doxis4.agentserver.AgentExecutionResult
import org.junit.*

class NumberRange_Sample01 {

    Binding binding

    @BeforeClass
    static void initSessionPool() {
        AgentTester.initSessionPool()
    }

    @Before
    void retrieveBinding() {
        binding = AgentTester.retrieveBinding()
    }

    @Test
    void testForAgentResult() {
        def agent = new ser.xtr.samples.NumberRange_Sample01();

        //binding["AGENT_EVENT_OBJECT_CLIENT_ID"] = "SD07PRJ_DOC249f95aff2-fc40-4996-aaf5-e364fa847f27182023-12-05T07:50:10.305Z011"
        binding["AGENT_EVENT_OBJECT_CLIENT_ID"] = "SR0aPRJ_FOLDER245b3e7202-c582-41df-a21a-f943ddde7c5f182023-11-15T07:10:36.815Z011"
        //binding["AGENT_EVENT_OBJECT_CLIENT_ID"] = "ST03BPM24f8c29b7f-c37d-4f10-9205-a354620c0acf182023-12-07T14:41:12.603Z011"

        def result = (AgentExecutionResult) agent.execute(binding.variables)
        assert result.resultCode == 0
    }

    @Test
    void testForJavaAgentMethod() {
        //def agent = new JavaAgent()
        //agent.initializeGroovyBlueline(binding.variables)
        //assert agent.getServerVersion().contains("Linux")
    }

    @After
    void releaseBinding() {
        AgentTester.releaseBinding(binding)
    }

    @AfterClass
    static void closeSessionPool() {
        AgentTester.closeSessionPool()
    }
}
