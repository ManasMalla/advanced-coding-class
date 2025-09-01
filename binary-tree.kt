data class Node(val value: Int, var left: Node? = null, var right: Node? = null)
fun insertNode(num: Int, n: Node?, q: MutableList<Node?>): Node? {
    var node = n
    if(q.isEmpty()){
        node = Node(num)
        q.add(node)
    }else{
        if(q.firstOrNull()?.left == null){
            q.firstOrNull()?.left = Node(num)
            q.add(q.firstOrNull()?.left)
        }else{
            q.firstOrNull()?.right = Node(num)
            q.add(q.firstOrNull()?.right)
        	q.removeFirst()
        }
    }
    return node
}
fun main(){
    var node: Node? = null
    val arr = listOf(1,2,3,4,5,6,7)
    val queue = mutableListOf<Node?>()
    for(num in arr){
        node = insertNode(num, node, queue)
    }
    print(node)
}
