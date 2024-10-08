// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package junit;

import java.io.File;

import junit.DecrementPrefixTest;
import junit.IncrementSuffixTest;
import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import pass.*;

/**
 * JUnit test suite for running the j-- programs in tests/pass.
 */

public class JMinusMinusTestRunner {

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(HelloWorldTest.class);
//        suite.addTestSuite(FactorialTest.class);
//        suite.addTestSuite(GCDTest.class);
//        suite.addTestSuite(SeriesTest.class);
//        suite.addTestSuite(ClassesTest.class);
       suite.addTestSuite(DivisionTest.class);
//        suite.addTestSuite(RemainderTest.class);
//        suite.addTestSuite(BitwiseOrTest.class);
//        suite.addTestSuite(BitwiseXorTest.class);
//        suite.addTestSuite(BitwiseAndTest.class);
//        suite.addTestSuite(BitwiseUnaryCompTest.class);
//        suite.addTestSuite(SignedShiftLeftTest.class);
//        suite.addTestSuite(SignedShiftRightTest.class);
//        suite.addTestSuite(UnsignedShiftRightTest.class);
//        suite.addTestSuite(UnaryPlusOpTest.class);
        suite.addTestSuite(DecrementPrefixTest.class);
        suite.addTestSuite(IncrementSuffixTest.class);
        suite.addTestSuite(WhileStatementTest.class);
        suite.addTestSuite(ForStatementTest.class);
        suite.addTestSuite(ForEachStatementTest.class);
        suite.addTestSuite(StaticInitializationBlockTest.class);
        suite.addTestSuite(InstanceInitializationBlockTest.class);
        suite.addTestSuite(AssignAddTest.class);
        suite.addTestSuite(AssignDivTest.class);
        suite.addTestSuite(AssignMinusTest.class);
        suite.addTestSuite(AssignRemTest.class);
        suite.addTestSuite(AssignStarTest.class);
        suite.addTestSuite(LogicalOrTest.class);
        suite.addTestSuite(DoubleTypeTest.class);
        suite.addTestSuite(ThrowTryCatchTest.class);
        suite.addTestSuite(ThrowTryCatchFinallyTest.class);
        suite.addTestSuite(InterfacesTest.class);
        suite.addTestSuite(ConditionalExpressionTest.class);
        return suite;
    }

    /**
     * Runs the test suite using the textual runner.
     */

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

}