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
            List<TNode> thisLineNodes = line.split(" ").collect { i -> new TNode(Integer.parseInt(i)) }

            if (lastLineNodes != null && thisLineNodes.size() != lastLineNodes.size() + 1) {
                throw new InvalidInputException("Invalid number of nodes on line: " + line)
            }

            if (root == null) {   
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
    
    // Type-safe clone()
    private LinkedList<Integer> newList(Collection<Integer> original) {
        new LinkedList<Integer>(original)
    }

    // Compute the maxPath at each node
    private LinkedList<Integer> traverse(TNode node) {
        
      // If node has already been computed don't recompute it
      if (node.maxPath != null) {
        newList(node.maxPath)
          
      // Leaf node
      } else if (node.left == null && node.right == null) {
        newList([node.value])
          
      } else {  // Recurse depth-first
        def listLeft = traverse(node.left)
        def listRight = traverse(node.right)
        node.maxPath = listLeft.sum() > listRight.sum() ? listLeft : listRight
        node.maxPath.addFirst(node.value)
        newList(node.maxPath)
      }
    }  
}