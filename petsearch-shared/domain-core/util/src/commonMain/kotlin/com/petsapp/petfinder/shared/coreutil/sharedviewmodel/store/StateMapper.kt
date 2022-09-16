package com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store

interface StateMapper<S : NanoRedux.State, RS : NanoRedux.RenderState> {
    fun mapToRenderState(state: S): RS
}
