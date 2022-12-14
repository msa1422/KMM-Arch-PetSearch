package com.msa.petsearch.shared.core.util.sharedviewmodel.store

interface ArgsMapper<S : NanoRedux.State> {
    fun mapArgsToState(currentState: S, args: HashMap<String, String>): S
}
