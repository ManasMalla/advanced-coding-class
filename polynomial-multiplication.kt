data class Polynomial(val coefficient:Int, val power: Int)

fun getPolynomial(p1: String): List<Polynomial>{
    val p = mutableListOf<Polynomial>()
    for(x in p1.replace("-", "+-").split("+")){
        if(x.contains("x")){
            val (coefficient, power) = x.split("x")
            val ex = Polynomial(if(coefficient == "") 1 else coefficient.toInt(), if(power == "") 1 else power.replace("^", "").toInt())
            p.add(ex)
        }else{
            val ex = Polynomial(x.toInt(), 0)
            p.add(ex)
        }
    }
    return p.toList()
}

fun multiplyTerm(x: Polynomial, y: Polynomial): Polynomial{
    return Polynomial(x.coefficient * y.coefficient, x.power + y.power)
}

fun main(){
    print("Enter first polynomial: ")
    val temp_p1 = readln();
    print("Enter second polynomial: ")
    val temp_p2 = readln();
    
    val p1 = getPolynomial(temp_p1)
    val p2 = getPolynomial(temp_p2)
    val temp_p3 = mutableListOf<Polynomial>()
    
    for(x in p1){
        for(y in p2){
            temp_p3.add(multiplyTerm(x, y))
        }
    }

    val p3 = temp_p3.groupBy{ it.power }.map{ (key, x) ->
        x.reduce{ a,b ->
            Polynomial(a.coefficient + b.coefficient, a.power)
        }
    }.sortedByDescending{
        it.power
    }.map{ x ->
        if(x.power == 1){
            if(x.coefficient == 1){
             "x"
            }else{
             "${x.coefficient}x"
            }
        }else if(x.power == 0){
             x.coefficient
        }else{
            if(x.coefficient == 1){
                "x^${x.power}"
            }else{
                "${x.coefficient}x^${x.power}"
            }
             
        }
    }.joinToString("+").replace("+-", "-")

    println(p3)

}
