package ai;
import kalaha.*;


/**
 * Created by Becca on 2016-09-15.
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
    }
    void CreateChildren()
    {
        if (m_depth >= m_maxDepth)
        {
            //börja propagera uppåt å skit
        }
        else{
            for (int i = 0; i <6; i++)
            {
                GameState t_gameState = m_gameState;
                t_gameState.makeMove(i);
                childNodes[i] = new Node(m_depth++, t_gameState);
            }
            //Creatat allt lägg kod här för det som ska hända efter
        }
    }

}
