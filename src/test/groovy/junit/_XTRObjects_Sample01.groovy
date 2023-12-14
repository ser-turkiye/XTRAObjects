package junit

import de.ser.doxis4.agentserver.AgentExecutionResult
import org.junit.*

class _XTRObjects_Sample01 {

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
        def agent = new ser.xtr.samples._XTRObjects_Sample01();

        binding["AGENT_EVENT_OBJECT_CLIENT_ID"] = "SD07PRJ_DOC24c9f328fb-e867-456a-93f8-898cc77c4b25182023-12-08T09:31:18.580Z011"
        //binding["AGENT_EVENT_OBJECT_CLIENT_ID"] = "SR0aPRJ_FOLDER245b3e7202-c582-41df-a21a-f943ddde7c5f182023-11-15T07:10:36.815Z011"
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
