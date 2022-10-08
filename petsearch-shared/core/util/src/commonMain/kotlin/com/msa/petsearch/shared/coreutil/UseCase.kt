package com.msa.petsearch.shared.coreutil

abstract class UseCase<Arg, ReturnType> {

    abstract suspend fun run(arg: Arg, onError: ((Throwable?) -> ReturnType)?): ReturnType

    suspend operator fun invoke(arg: Arg, onError: ((Throwable?) -> ReturnType)? = null): ReturnType =
        run(arg, onError)
}

// suspend operator fun <Type> UseCase<Unit, Type>.invoke(): Type = this(Unit)
