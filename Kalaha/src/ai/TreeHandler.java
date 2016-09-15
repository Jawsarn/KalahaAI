package ai;

import kalaha.GameState;

/**
 * Created by Becca on 2016-09-15.
 */
public class TreeHandler {

    void CreateTree(GameState currentBoard)
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
