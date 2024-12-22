@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthResult<User>?>(null)
    val authState = _authState.asStateFlow()

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: AuthUiEvent) {
        when (event) {
            is AuthUiEvent.EmailChanged ->
                _uiState.update { it.copy(email = event.email) }
            is AuthUiEvent.PasswordChanged ->
                _uiState.update { it.copy(password = event.password) }
            is AuthUiEvent.DisplayNameChanged ->
                _uiState.update { it.copy(displayName = event.displayName) }
            is AuthUiEvent.SignIn -> signInWithEmail()
            is AuthUiEvent.SignUp -> signUpWithEmail()
            is AuthUiEvent.GoogleSignIn -> signInWithGoogle()
        }
    }

    private fun signInWithEmail() {
        viewModelScope.launch {
            _authState.value = AuthResult.Loading()
            val result = authRepository.signInWithEmail(
                _uiState.value.email,
                _uiState.value.password
            )
            _authState.value = result
        }
    }

    private fun signUpWithEmail() {
        viewModelScope.launch {
            _authState.value = AuthResult.Loading()
            val result = authRepository.signUpWithEmail(
                _uiState.value.email,
                _uiState.value.password,
                _uiState.value.displayName
            )
            _authState.value = result
        }
    }
}