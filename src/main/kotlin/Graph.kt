/**
 * Class that represent a graph
 * You can access and modify [vertex] with [Graph].[vertex] attribute
 * You can initialize [vertex] with a [List] of [String]
 * To add an edge use [addEdge] method
 * If the graph is oriented specify value <b>true</b> to param <b>oriented</b> of [addEdge] method
 * @author MacaronFR
 */
class Graph{
	val vertex = mutableListOf<String>()
	private val adjacency = mutableMapOf<String, MutableMap<String, Int>>()

	/**
	 * Init the vertex with a lambda that return a [List]
	 * @param init The lambda to execute that return [List]
	 */
	fun initVertex(init: () -> List<String>){
		init().forEach {
			vertex.add(it)
		}
	}

	/**
	 * Add edge to the graph
	 * Must be between 2 existing vertex
	 * @param from The starting vertex of the edge (important if the graph is oriented)
	 * @param to The ending vertex of the edge (important if the graph is oriented)
	 * @param weight The weight of the edge
	 * @param oriented If false, make an edge from [from] to [to] AND from [to] to [from]
	 * @return false if the edge is invalid, true otherwise
	 */
	fun addEdge(from: String, to: String, weight: Int, oriented: Boolean = false): Boolean{
		if(from !in vertex || to !in vertex){
			return false
		}
		if(adjacency[from] == null){
			adjacency[from] = mutableMapOf()
		}
		adjacency[from]!![to] = weight
		if(!oriented){
			if(adjacency[to] == null){
				adjacency[to] = mutableMapOf()
			}
			adjacency[to]!![from] = weight
		}
		return true
	}

	/**
	 * Get the minimum recovering tree of the graph from [startVertex] using the Jàrnik-Prim algorithme
	 * @param startVertex The starting vertex of the tree
	 * @return The root node of the tree
	 */
	fun getMinimumRecoverTree(startVertex: String): Node{
		val res = Node(startVertex)
		val vertexTree = mutableListOf(startVertex)
		val vertexTmp = vertex.toMutableList()
		var min: Int
		var vG: String
		var vT: String
		vertexTmp.remove(startVertex)
		while(vertexTmp.isNotEmpty()){
			min = Int.MAX_VALUE
			vG = ""
			vT = ""
			vertexTree.forEach { vTree ->
				adjacency[vTree]?.forEach {vNear ->
					if(vNear.key in vertexTmp && vNear.value < min){
						vG = vNear.key
						vT = vTree
						min = vNear.value
					}
				}
			}
			vertexTmp.remove(vG)
			vertexTree.add(vG)
			res.addTo(vT, vG)
		}
		return res
	}
}