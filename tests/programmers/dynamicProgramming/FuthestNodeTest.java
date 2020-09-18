package programmers.dynamicProgramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuthestNodeTest {

    @Test
    void solution() {
        FuthestNode f = new FuthestNode();
        assertEquals(3, f.solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
    }
}