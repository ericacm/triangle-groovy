package triangle

// Binary tree
class TNode {
    final int value

    TNode left = null, right = null

    // Keep track of the max path at each node
    // Use linked list so we can do O(1) prepend
    LinkedList<Integer> maxPath = null
    long sum

    TNode(int value) { this.value = value }
}