package uz.safargo.driver.core.error

import uz.safargo.driver.core.models.Message


sealed interface Either<T> {
    class Right<T>(val data: T) : Either<T>
    class Left<T>(val errorMessage: Message) : Either<T>

}