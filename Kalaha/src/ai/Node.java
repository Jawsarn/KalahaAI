package ai;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import kalaha.*;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by TAJMS on 2016-09-15.
 */
public class Node{
    public static boolean m_allTeminationNodes;
    int m_depth;
    int m_maxDepth;
    GameState m_gameState;
    int m_bestValue = 0;
    int m_bestNodeIndex = -1;
    boolean m_max;
    int m_alpha;
    int m_beta;
    Node(int p_depth, GameState p_gameState, boolean p_max, int p_alpha, int p_beta) throws Exception {
        m_depth = p_depth;
        m_gameState = p_gameState;
        m_maxDepth = TreeHandler.m_maxDepth;
        m_max = p_max;
        m_alpha = p_alpha; // Max should be above
        m_beta = p_beta; // Max should be below

    }

    // Returns the score in the current node
    int CreateChildren() throws Exception
    {
        int r_score;

        // Do we have time?
        if (System.currentTimeMillis() - TreeHandler.m_startTime > 5000) // max 5 sec
        {
            throw new Exception("Out of time");
        }

        if (m_depth >= m_maxDepth || m_gameState.gameEnded())
        {
            if (!m_gameState.gameEnded())
            {
                m_allTeminationNodes = false;
            }
            //börja propagera uppåt å skit
            r_score = UtilityFunction();
        }
        else{
            int t_bestChild = -1;
            if (m_max)
                r_score = Integer.MIN_VALUE;
            else
                r_score = Integer.MAX_VALUE;

            for (int i = 0; i < 6; i++) // From 1-6
            {
                GameState t_gameState = m_gameState.clone();

                boolean t_successful = t_gameState.makeMove(i + 1);

                if (t_successful) {
                    Node newNode = new Node(m_depth + 1, t_gameState, t_gameState.getNextPlayer() == TreeHandler.m_playerMax,
                            m_alpha, m_beta);

                    int t_childScore = newNode.CreateChildren(); // TODO Launch a new thread here

                    // Check what child had the best score
                    if ((m_max && t_childScore > r_score) || (!m_max && t_childScore < r_score)) {
                        r_score = t_childScore;
                        t_bestChild = i + 1;
                        // Move pruning here
                        if(m_max)
                        {
                            m_alpha = Math.max(r_score, m_alpha);
                            if (m_alpha >= m_beta)
                                break;
                        }
                        else
                        {
                            m_beta = Math.min(r_score, m_beta);
                            if (m_beta <= m_alpha)
                                break;
                        }
                    }


                }
            }

            // Save the best score and move
            m_bestNodeIndex = t_bestChild;
            m_bestValue = r_score;
            //Creatat allt lägg kod här för det som ska hända efter
        }
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
