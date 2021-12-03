class Graph{
	val vertex = mutableListOf<String>()
	val edge = mutableMapOf<Pair<String, String>, Int>()

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
				vertexTmp.forEach { vGraph ->
					edge[Pair(vTree, vGraph)]?.let {
						if(it < min){
							vG = vGraph
							vT = vTree
							min = it
						}
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