package fr.macaron.dev.graph

/**
 * Class that represent a [Node] of a Tree <br/>
 * Have [name], [children] and [parent] <br/>
 * Can be root or leave<br/>
 * We can add a child to a named [Node] (this one or any of [children])<br/>
 * @author MacaronFR
 * @constructor Provide a [name] and optionnaly a [parent] to the new [Node] (if [parent] is null, the [Node] is considered the root of the tree
 */
class Node(val name: String, parent: Node? = null) {

	var parent: Node? = null
	set(value) {
		parent?.let{
			if(!searchChild(it.name)){
				field = value
			}
		}
	}
	val children = mutableListOf<Node>()

	init {
		this.parent = parent
	}

	fun isRoot() = parent == null
	fun isLeave() = children.size == 0

	/**
	 * Add to [node] a [newChild]
	 * @param node The name of the parent [Node]
	 * @param newChild The name of the child [Node]
	 * @return false on invalid [node], true otherwise
	 */
	fun addTo(node: String, newChild: String): Boolean {
		if(node == name) {
			children.add(Node(newChild, this))
			return true
		}
		children.forEach { child ->
			if(child.addTo(node, newChild)) {
				return true
			}
		}
		return false
	}

	/**
	 * Print the tree
	 */
	fun print() {
		children.forEach {
			println("From $name to ${it.name}")
			it.print()
		}
	}

	fun searchChild(name: String): Boolean{
		children.forEach { child ->
			if(child.name == name || child.searchChild(name)){
				return true
			}
		}
		return false
	}
}