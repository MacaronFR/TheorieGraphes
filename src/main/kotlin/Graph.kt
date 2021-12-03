class Graph{
	val vertex = mutableListOf<String>()
	val adjency = mutableMapOf<String, MutableMap<String, Int>>()

	fun addEdge(from: String, to: String, weight: Int, oriented: Boolean = false): Boolean{
		if(from !in vertex || to !in vertex){
			return false
		}
		if(adjency[from] == null){
			adjency[from] = mutableMapOf()
		}
		adjency[from]!![to] = weight
		if(!oriented){
			if(adjency[to] == null){
				adjency[to] = mutableMapOf()
			}
			adjency[to]!![from] = weight
		}
		return true
	}

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
				adjency[vTree]?.forEach { vNear ->
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