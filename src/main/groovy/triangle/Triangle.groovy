package triangle

class Triangle {
    private final List<Integer> maxPath

    public List<Integer> getMaxPath() { maxPath }
    
    public Triangle(String filename) {
        TNode root = null

        try {
            root = buildTree(filename)

        } catch (NumberFormatException ex) {
            throw new InvalidInputException("Error parsing " + filename)
        }

        if (root == null) {
            throw new InvalidInputException("No triangle in " + filename)
        }

        maxPath = traverse(root)
    }
    
    private TNode buildTree(String filename) {
        TNode root = null
        List<TNode> lastLineNodes = null

        new File(filename).eachLine { line ->
            if (line.length() == 0) return  // Skip blank lines

            // Parse current line into nodes without children set
            List<TNode> thisLineNodes = line.tokenize().collect { i -> new TNode(Integer.parseInt(i)) }

            if (lastLineNodes != null && thisLineNodes.size() != lastLineNodes.size() + 1) {
                throw new InvalidInputException("Invalid number of values on line: " + line)
            }

            if (root == null) {   
                if (thisLineNodes.size() != 1) throw new InvalidInputException("First line has more than one value: " + line)
                // First line has one node, use it as root 
                root = thisLineNodes[0]
                
            } else {
                // Using the current line, set the children of the line above
                thisLineNodes.eachWithIndex { node, i ->
                    if (i > 0) {
                        lastLineNodes[i-1].right = node
                    }
                    if (i < lastLineNodes.size()) {
                        lastLineNodes[i].left = thisLineNodes[i]
                    }
                }
            }
            lastLineNodes = thisLineNodes
        }

        root
    }

    // Compute the maxPath at each node
    private LinkedList<Integer> traverse(TNode node) {
        
      // If node has already been computed don't recompute it
      if (node.maxPath != null) {
        node.maxPath
          
      // Leaf node
      } else if (node.left == null && node.right == null) {
        node.sum = node.value  
        copyList([node.value])                                                 
          
      } else {  // Recurse depth-first
        def listLeft = traverse(node.left)
        def listRight = traverse(node.right)
        if (node.left.sum > node.right.sum) {
            node.sum = node.left.sum + node.value
            node.maxPath = copyList(listLeft)
        } else {
            node.sum = node.right.sum + node.value
            node.maxPath = copyList(listRight)
        }
        node.maxPath.addFirst(node.value)
        node.maxPath
      }
    }

    // Clone list
    private LinkedList<Integer> copyList(Collection<Integer> original) {
        new LinkedList<Integer>(original)
    }
}