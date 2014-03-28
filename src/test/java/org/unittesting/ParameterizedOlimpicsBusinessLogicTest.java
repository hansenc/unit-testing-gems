package org.unittesting;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.collect.Lists;

/**
 * Parameterized tests requirements:
 * <ol>
 * <li>@RunWith(Parameterized.class) in the test class</li>
 * <li>@Parameters on a method that serves data</li>
 * <li>public constructor that takes one row of values at a time</li>
 * </ol>
 */
@RunWith(Parameterized.class)
public class ParameterizedOlimpicsBusinessLogicTest
{

    private OlimpicsBusinessLogic.Impl logic;

    private TrainingEffort[] competitors;

    private TrainingEffort me;

    private Medal expected;

    private static final String HIGH = TrainingEffort.HIGH.name();

    private static final String COUCH_POTATO = TrainingEffort.COUCH_POTATO.name();

    private static final String MEH = TrainingEffort.MEH.name();

    private static final String MEDIUM = TrainingEffort.MEDIUM.name();

    private static final String GOLD = Medal.GOLD.name();

    private static final String IRONMAN = TrainingEffort.IRONMAN.name();

    private static final String SILVER = Medal.SILVER.name();

    private static final String BRONZE = Medal.BRONZE.name();

    private static final String NONE_TRY_AGAIN_NEXT_TIME = Medal.NONE_TRY_AGAIN_NEXT_TIME.name();

    @Before
    public void setUp()
    {
        logic = new OlimpicsBusinessLogic.Impl();
    }

    @Parameters
    public static Collection<Object[]> data()
    {
        Object[][] data = new Object[][] {
                  { HIGH,   new Object[] { COUCH_POTATO, MEDIUM, MEH },   GOLD }
                , { HIGH,   new Object[] { HIGH, HIGH, HIGH },            GOLD }
                , { HIGH,   new Object[] { HIGH, HIGH, IRONMAN },       SILVER }
                , { MEDIUM, new Object[] { HIGH, HIGH, MEH },           BRONZE }
                , { MEDIUM, new Object[] { IRONMAN, HIGH, IRONMAN },    NONE_TRY_AGAIN_NEXT_TIME }
        };
        return Arrays.asList(data);
    }

    @Test
    public void test()
    {
        // WHEN
        Medal actual = logic.win(me, competitors);
        // THEN
        assertEquals(expected, actual);
    }

    public ParameterizedOlimpicsBusinessLogicTest(
            Object me, Object[] competitors, Object expected)
    {
        this.me = TrainingEffort.valueOf((String) me);
        this.competitors = competitors(competitors);
        this.expected = Medal.valueOf((String) expected);
    }

    private TrainingEffort[] competitors(Object[] competitors)
    {
        Collection<TrainingEffort> result = Lists.newArrayList();
        for(Object each : competitors)
        {
            result.add(TrainingEffort.valueOf((String) each));
        }
        return result.toArray(new TrainingEffort[] {});
    }

}
