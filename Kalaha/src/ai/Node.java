package ai;
import kalaha.*;


/**
 * Created by TAJMS on 2016-09-15.
 */
public class Node{
    int m_depth;
    int m_maxDepth;
    GameState m_gameState;
    Node[] childNodes = new Node[6];
    int m_bestValue = 0;
    int m_bestNodeIndex = -1;
    Node(int p_depth, GameState p_gameState){
        m_depth = p_depth;
        m_gameState = p_gameState;
        m_maxDepth = TreeHandler.m_maxDepth;
    }
    // Returns the score in the current node
    int CreateChildren()
    {
        int r_score = 0;
        if (m_depth >= m_maxDepth)
        {
            //börja propagera uppåt å skit
            r_score = UtilityFunction();
        }
        else{
            int t_bestChild = -1;
            int t_bestScore = -1000;

            for (int i = 0; i <6; i++)
            {
                GameState t_gameState = m_gameState.clone();
                boolean t_successful = t_gameState.makeMove(i);
                // Ifall vi får köra igen gör random move
                if (t_gameState.getNextPlayer() == TreeHandler)
                if (t_successful) {
                    childNodes[i] = new Node(m_depth+1, t_gameState);
                    childNodes[i].CreateChildren(); // TODO Launch a new thread here
                    if (r_score > t_bestScore)
                    {
                        t_bestScore = r_score;
                        t_bestChild = i;
                    }
                }
            }
            m_bestNodeIndex = t_bestChild;
            m_bestValue = t_bestScore;
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
