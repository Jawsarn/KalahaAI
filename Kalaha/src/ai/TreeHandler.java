package ai;

import kalaha.GameState;

/**
 * Created by Konrad on 2016-09-15.
 */
public class TreeHandler {
    public static int m_maxDepth;
    public static int m_playerMax;
    public static int m_playerMin;
    public static long m_startTime;

    private int m_move;
    Node m_rootNode;
    public TreeHandler()
    {

    }

    public void CreateTree(GameState p_currentState, int p_playerMax, int p_maxDepth)
    {
        m_maxDepth = p_maxDepth;

        m_playerMax = p_playerMax;
        if (p_playerMax == 1)
            m_playerMin=2;
        else
            m_playerMin=1;

        m_move = getRandom();
        // start time
        m_startTime = System.currentTimeMillis();

        while(true) {
            try {
                m_rootNode = new Node(0, p_currentState.clone(), true); // TODO do we need to clone?
                m_rootNode.CreateChildren();
                m_move = m_rootNode.GetBestMove();
                AIClient.addText("Tree took " + (System.currentTimeMillis() - m_startTime));
                m_maxDepth += 1;
            } catch (Exception e) {
                break;
            }
        }
        Timer.PrintTimers();
    }

    public void SetTreeMaxDepth(int p_maxDepth)
    {
        m_maxDepth = p_maxDepth;
    }

    public int GetBestMove()
    {
        return m_move;
    }
    public int getRandom()
    {
        return 1 + (int)(Math.random() * 6);
    }

    public void ReleaseTree()
    {

    }
}
