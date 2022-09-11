package com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store

interface ArgsMapper<S : NanoRedux.State> {
    fun mapArgsToState(currentState: S, args: HashMap<String, String>): S
}
