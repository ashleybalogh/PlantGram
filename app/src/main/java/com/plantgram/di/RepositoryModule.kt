@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePlantRepository(
        firestore: FirebaseFirestore,
        storage: FirebaseStorage
    ): PlantRepository = PlantRepositoryImpl(firestore, storage)

    @Provides
    @Singleton
    fun providePostRepository(
        firestore: FirebaseFirestore,
        storage: FirebaseStorage
    ): PostRepository = PostRepositoryImpl(firestore, storage)

    @Provides
    @Singleton
    fun provideUserRepository(
        firestore: FirebaseFirestore,
        storage: FirebaseStorage
    ): UserRepository = UserRepositoryImpl(firestore, storage)

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()
}