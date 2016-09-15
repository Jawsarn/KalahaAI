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
    

}
