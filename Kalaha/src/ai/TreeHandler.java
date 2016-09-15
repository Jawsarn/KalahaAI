package ai;

import kalaha.GameState;

/**
 * Created by Konrad on 2016-09-15.
 */
public class TreeHandler {
    public static int m_maxDepth;
    public TreeHandler(int p_maxDepth)
    {
        m_maxDepth = p_maxDepth;
    }

    public void CreateTree(GameState p_currentState)
    {

    }

    public int GetBestMove()
    {
        return getRandom();
    }
    public int getRandom()
    {
        return 1 + (int)(Math.random() * 6);
    }
}
