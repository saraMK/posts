package com.example.postsapplication.strategyDP

/*
* behavior design pattern
* some functions do the same thing but in different ways so enable selecting an algorithm in runtime
* achieve open-close principle (SOLID) >> open class for extention but closed for modification
* */
fun main() {
    val pawn :Movements= PawnMovements()
    val rock :Movements= RockMovements()
    val bishop :Movements= BishopMovements()

    pawn.doMove()
    rock.doMove()
    bishop.doMove()

}

interface Movements {

    fun doMove()
}

class PawnMovements : Movements {
    override fun doMove() {
        println("Pawns only move forward")
    }
}

class RockMovements : Movements {
    override fun doMove() {
        println("Rooks move in a continuous line forwards, backwards and side-to-side")
    }
}

class BishopMovements : Movements {
    override fun doMove() {
        println("Bishops move in continuous diagonal lines in any direction")
    }
}
