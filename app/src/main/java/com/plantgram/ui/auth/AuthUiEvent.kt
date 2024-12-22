sealed class AuthUiEvent {
    data class EmailChanged(val email: String) : AuthUiEvent()
    data class PasswordChanged(val password: String) : AuthUiEvent()
    data class DisplayNameChanged(val displayName: String) : AuthUiEvent()
    object SignIn : AuthUiEvent()
    object SignUp : AuthUiEvent()
    object GoogleSignIn : AuthUiEvent()
}