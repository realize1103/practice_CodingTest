package programmers.dynamicProgramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionNTest {

    @Test
    void solution() {
        ExpressionN expressionN = new ExpressionN();
        assertEquals(4,expressionN.solution(5,12));
    }
}