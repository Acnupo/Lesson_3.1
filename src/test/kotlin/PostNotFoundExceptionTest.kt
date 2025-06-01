import org.junit.Test

class PostNotFoundExceptionTest {
    @Test(expected = PostNotFoundException::class)
    fun shouldThrowWhenPostNotFound() {
        val service = WallService()
        service.createComment(999, Comment(0, 1, 999,23000, text = "Test"))
    }
}