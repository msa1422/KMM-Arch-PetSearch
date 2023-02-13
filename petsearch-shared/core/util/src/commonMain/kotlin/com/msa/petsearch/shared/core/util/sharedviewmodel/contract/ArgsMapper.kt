package com.msa.petsearch.shared.core.util.sharedviewmodel.contract

interface ArgsMapper<NA : UiContract.NavArgs> {
    fun argsFrom(args: HashMap<String, String>): NA
}
