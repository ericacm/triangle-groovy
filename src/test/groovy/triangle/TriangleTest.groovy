package triangle

import org.junit.Test
import org.junit.Assert

class TriangleTest {
    final String RESOURCES = "src/test/resources/"

    @Test
    void smallTriangle() {
        String filename = RESOURCES + "triangle_test_4rows.txt"
        Triangle t = new Triangle(filename)
        Assert.assertEquals(27, t.maxPath.sum())
    }

    @Test
    void bigTriangle() {
        String filename = RESOURCES + "triangle_test_100rows.txt"
        Triangle t = new Triangle(filename)
        Assert.assertEquals(732506, t.maxPath.sum())    // Assuming this is correct
    }

    @Test
    void triangleWithBlankLines() {
        String filename = RESOURCES + "triangle_test_4rows_with_blank_lines.txt"
        Triangle t = new Triangle(filename)
        Assert.assertEquals(27, t.maxPath.sum())
    }

    @Test
    void oneLineTriangle() {
        String filename = RESOURCES + "triangle_test_1row.txt"
        Triangle t = new Triangle(filename)
        Assert.assertEquals(42, t.maxPath.sum())
    }

    @Test
    void bertTriangle() {
        String filename = RESOURCES + "bert.txt"
        Triangle t = new Triangle(filename)
        Assert.assertEquals(10001, t.maxPath.sum())
    }

    @Test(expected = InvalidInputException.class)
    void noInput() {
        String filename = RESOURCES + "triangle_test_empty.txt"
        new Triangle(filename)
    }

    @Test(expected = InvalidInputException.class)
    void nonNumeric() {
        String filename = RESOURCES + "triangle_test_non_numeric.txt"
        new Triangle(filename)
    }

    @Test(expected = InvalidInputException.class)
    void fewerNodes() {
        String filename = RESOURCES + "triangle_test_fewer_nodes.txt"
        new Triangle(filename)
    }

    @Test(expected = InvalidInputException.class)
    void moreNodes() {
        String filename = RESOURCES + "triangle_test_more_nodes.txt"
        new Triangle(filename)
    }

    @Test(expected = InvalidInputException.class)
    void firstLineMore() {
        String filename = RESOURCES + "triangle_test_first_line_more.txt"
        new Triangle(filename)
    }
}
