package com.msa.petsearch.shared.core.util.sharedviewmodel.store

interface StateMapper<S : NanoRedux.State, RS : NanoRedux.RenderState> {
    fun mapToRenderState(state: S): RS
}
