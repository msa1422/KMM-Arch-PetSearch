package com.petsapp.petfinder.shared.core_util.shared_viewmodel.store

interface ArgsMapper<S : NanoRedux.State> {
    fun mapArgsToState(currentState: S, args: HashMap<String, String>): S
}