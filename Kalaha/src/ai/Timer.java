package ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christian on 19/09/2016.
 */
public class Timer {

    static HashMap<String, Timer> m_timers = new HashMap<String, Timer>();
    long m_timeStart;
    long m_timeStop;
    long m_fullTime;
    long m_maxTime = 0;
    int m_nrCalls = 0;

    public static Timer GetTimer(String p_name)
    {
        Timer r_timer = m_timers.get(p_name);
        if(r_timer == null)
        {
            r_timer = new Timer();
            m_timers.put(p_name, r_timer);
        }

        return r_timer;
    }

    public void Start()
    {
        m_timeStart = System.currentTimeMillis();
        m_nrCalls++;
    }

    public void Stop()
    {
        m_timeStop = System.currentTimeMillis();
        m_fullTime += m_timeStop - m_timeStart;
        m_maxTime = Math.max(m_maxTime, m_timeStop - m_timeStart);
    }


    public static void PrintTimers()
    {
        for(Map.Entry<String, Timer> entry : m_timers.entrySet())
        {
            //AIClient.addText();
            System.out.println(entry.getKey() + " " + entry.getValue().m_fullTime + " Calls " + entry.getValue().m_nrCalls + " Longest " + entry.getValue().m_maxTime + " DeltaTime " + (float)entry.getValue().m_fullTime/(float)entry.getValue().m_nrCalls);
        }
    }
}
