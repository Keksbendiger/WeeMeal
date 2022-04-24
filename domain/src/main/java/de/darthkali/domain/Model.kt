package de.fhe.ai.weemeal.domain

enum class AsyncOperationState {
    LOADING,
    SAVING,
    SUCCESS,
    ERROR,
    UNDEFINED;
}

data class AsyncOperation(val status: AsyncOperationState, val message: String, val payload: Any = Unit) {

    companion object {

        fun success(message: String = "Async Op Successful", payload: Any = Unit): AsyncOperation {
            return AsyncOperation(AsyncOperationState.SUCCESS, message, payload)
        }

        fun saving(message: String = "Async Saving"): AsyncOperation {
            return AsyncOperation(AsyncOperationState.SAVING, message)
        }

        fun error(message: String = "Error on Async Op", payload: Any = Unit): AsyncOperation {
            return AsyncOperation(AsyncOperationState.ERROR, message, payload)
        }

        fun loading(message: String = "Async Loading"): AsyncOperation {
            return AsyncOperation(AsyncOperationState.LOADING, message)
        }

        fun undefined(message: String = "No Async Op / Undefined"): AsyncOperation {
            return AsyncOperation(AsyncOperationState.UNDEFINED, message)
        }
    }
}

data class User(val name: String, val id: Long = 0)
