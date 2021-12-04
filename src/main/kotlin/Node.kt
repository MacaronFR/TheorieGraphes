class Node(val name: String, val parent: Node? = null) {
	val children = mutableListOf<Node>()

	fun isRoot() = parent == null
	fun isLeave() = children.size == 0

	/**
	 * Add to [node] a [newChild]
	 * @param node The name of the parent [Node]
	 * @param newChild The name of the child [Node]
	 * @return false on invalid [node], true otherwise
	 */
	fun addTo(node: String, newChild: String): Boolean{
		if(node == name){
			children.add(Node(newChild, this))
			return true
		}
		if(children.isEmpty()){
			return false
		}
		children.forEach { child ->
			if(child.addTo(node, newChild)){
				return true
			}
		}
		return false
	}

	/**
	 * Print the tree
	 */
	fun print(){
		children.forEach {
			println("From $name to ${it.name}")
			it.print()
		}
	}
}