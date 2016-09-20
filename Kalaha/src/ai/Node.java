package ai;
import kalaha.*;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by TAJMS on 2016-09-15.
 */
public class Node{
    int m_depth;
    int m_maxDepth;
    GameState m_gameState;
    int m_bestValue = 0;
    int m_bestNodeIndex = -1;
    boolean m_max;
    Node(int p_depth, GameState p_gameState, boolean p_max) throws Exception {
        Timer time1 = Timer.GetTimer("Node consturctor");
        time1.Start();
        if (System.currentTimeMillis() - TreeHandler.m_startTime > 5000) // max 5 sec
        {
            AIClient.addText("Time in throw1 " + (System.currentTimeMillis() - TreeHandler.m_startTime));
            throw new Exception("Out of time");
        }
        m_depth = p_depth;
        m_gameState = p_gameState;
        m_maxDepth = TreeHandler.m_maxDepth;
        m_max = p_max;
        time1.Stop();
    }

    // Returns the score in the current node
    int CreateChildren() throws Exception
    {
        int r_score;
        Timer time1 = Timer.GetTimer("Create Children full");
        time1.Start();

        // Do we have time?
        if (System.currentTimeMillis() - TreeHandler.m_startTime > 5000) // max 5 sec
        {
            AIClient.addText("Time in throw2 " + (System.currentTimeMillis() - TreeHandler.m_startTime));
            throw new Exception("Out of time");
        }

        if (m_depth >= m_maxDepth || m_gameState.gameEnded())
        {
            //börja propagera uppåt å skit
            Timer time2 = Timer.GetTimer("Utility func");
            time2.Start();
            r_score = UtilityFunction();
            time2.Stop();
        }
        else{
            int t_bestChild = -1;
            if (m_max)
                r_score = Integer.MIN_VALUE;
            else
                r_score = Integer.MAX_VALUE;

            for (int i = 0; i < 6; i++) // From 1-6
            {
                Timer time3 = Timer.GetTimer("MakeMove clone");
                time3.Start();
                GameState t_gameState = m_gameState.clone();
                time3.Stop();

                Timer time33 = Timer.GetTimer("MakeMove func");
                boolean t_successful = t_gameState.makeMove(i + 1);
                time33.Stop();

                Timer time2 = Timer.GetTimer("Utility func");
                time2.Start();

                // Do we have time?
                if (System.currentTimeMillis() - TreeHandler.m_startTime > 5000) // max 5 sec
                {
                    AIClient.addText("Time in throw3 " + (System.currentTimeMillis() - TreeHandler.m_startTime));
                    throw new Exception("Out of time");
                }
                if (t_successful) {
                    Timer time4 = Timer.GetTimer("Node new call");
                    time4.Start();
                    Node newNode = new Node(m_depth + 1, t_gameState, t_gameState.getNextPlayer() == TreeHandler.m_playerMax);
                    time4.Stop();

                    int t_childScore = newNode.CreateChildren(); // TODO Launch a new thread here

                    Timer time5 = Timer.GetTimer("Add score");
                    time5.Start();
                    if (System.currentTimeMillis() - TreeHandler.m_startTime > 5000) // max 5 sec
                    {
                        AIClient.addText("Time in throw4 " + (System.currentTimeMillis() - TreeHandler.m_startTime));
                        throw new Exception("Out of time");
                    }
                    // Check what child had the best score
                    if ((m_max && t_childScore > r_score) || (!m_max && t_childScore < r_score)) {
                        r_score = t_childScore;
                        t_bestChild = i + 1;
                    }
                    time5.Stop();
                }
            }

            // Save the best score and move
            m_bestNodeIndex = t_bestChild;
            m_bestValue = r_score;
            //Creatat allt lägg kod här för det som ska hända efter
        }
        if (System.currentTimeMillis() - TreeHandler.m_startTime > 5000) // max 5 sec
        {
            AIClient.addText("Time in throw5 " + (System.currentTimeMillis() - TreeHandler.m_startTime));
            throw new Exception("Out of time");
        }
        time1.Stop();
        return r_score;
    }

    private int UtilityFunction()
    {
        int r_value = 0;
        r_value += m_gameState.getScore(TreeHandler.m_playerMax);
        r_value -= m_gameState.getScore(TreeHandler.m_playerMin);
        return r_value;
    }

    public int GetBestMove()
    {
        return m_bestNodeIndex;
    }
}
