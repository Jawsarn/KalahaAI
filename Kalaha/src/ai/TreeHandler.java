package ai;

import kalaha.GameState;

/**
 * Created by Konrad on 2016-09-15.
 */
public class TreeHandler {
    public static int m_maxDepth;
    Node m_rootNode;
    public TreeHandler(int p_maxDepth)
    {
        m_maxDepth = p_maxDepth;
    }

    public void CreateTree(GameState p_currentState)
    {
        //m_rootNode = RootNode(p_currentState.clone(),0);
        //m_rootNode.SimulateTurns();
    }

    public void SetTreeMaxDepth(int p_maxDepth)
    {
        m_maxDepth = p_maxDepth;
    }

    public int GetBestMove()
    {
        return getRandom();
    }
    public int getRandom()
    {
        return 1 + (int)(Math.random() * 6);
    }

    public void ReleaseTree()
    {

    }
}
