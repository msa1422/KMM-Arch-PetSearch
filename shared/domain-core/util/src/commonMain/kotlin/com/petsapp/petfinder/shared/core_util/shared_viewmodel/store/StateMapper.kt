package com.petsapp.petfinder.shared.core_util.shared_viewmodel.store

interface StateMapper<S : NanoRedux.State, RS : NanoRedux.RenderState> {
    fun mapToRenderState(state: S): RS
}