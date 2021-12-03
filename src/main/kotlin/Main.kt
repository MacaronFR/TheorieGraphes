fun main() {
	val graph = getGraph()
	val tree = graph.getMinimumRecoverTree("A")
	tree.print()
}

fun getGraph(): Graph{
	val graph = Graph()
	graph.vertex.add("A")
	graph.vertex.add("B")
	graph.vertex.add("C")
	graph.vertex.add("D")
	graph.vertex.add("E")
	graph.vertex.add("F")
	graph.vertex.add("G")
	graph.addEdge("A", "B",  5)
	graph.addEdge("A", "C",  4)
	graph.addEdge("B", "C",  10)
	graph.addEdge("B", "D",  2)
	graph.addEdge("B", "E",  2)
	graph.addEdge("C", "E",  8)
	graph.addEdge("C", "F",  5)
	graph.addEdge("D", "E",  1)
	graph.addEdge("D", "G",  6)
	graph.addEdge("E", "F",  3)
	graph.addEdge("F", "G",  6)
	return graph
}