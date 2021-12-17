package fr.macaron.dev.graph

fun main() {
	val graph = getGraph()
	val tree = graph.getMinimumRecoverTreeJarnikPrim("A")
	tree.print()
	val graph2 = graph.getMinimumRecoverTreeKruskal()
	graph2.edgeFromAdjency().forEach {
		println("From ${it.vertexFrom} to ${it.vertexTo} with weight ${it.weight}")
	}
}

fun getGraph(): Graph {
	val graph = Graph()
	graph.initVertex {
		('A'..'G').map {
			it.toString()
		}
	}
	graph.addEdge("A", "B", 5)
	graph.addEdge("A", "C", 4)
	graph.addEdge("B", "C", 10)
	graph.addEdge("B", "D", 2)
	graph.addEdge("B", "E", 2)
	graph.addEdge("C", "E", 8)
	graph.addEdge("C", "F", 5)
	graph.addEdge("D", "E", 1)
	graph.addEdge("D", "G", 6)
	graph.addEdge("E", "F", 3)
	graph.addEdge("F", "G", 6)
	return graph
}