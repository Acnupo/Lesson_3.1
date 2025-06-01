import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.test.*
import java.util.concurrent.TimeUnit

class WallServiceTest {
    private lateinit var service: WallService
    private val fixedDate = 1672531200000L // 1 января 2023

    @Before
    fun setUp() {
        service = WallService()
    }

    @Test
    fun `add post should assign id and set date`() {
        val post = Post(0, 1, 1, text = "Test")
        val result = service.add(post)

        assertNotEquals(0, result.id)
        assertTrue(result.date > 0)
    }

    @Test
    fun `update should keep original date`() {
        val post = service.add(Post(0, 1, 1, text = "Original"))
        val updated = post.copy(text = "Updated")

        assertTrue(service.update(updated))
        assertEquals(post.date, service.getPostById(post.id)?.date)
    }

    @Test
    fun `createComment should set date when not provided`() {
        val post = service.add(Post(0, 1, 1, text = "Post"))
        val comment = Comment(0, 2, post.id, text = "Test")

        val result = service.createComment(post.id, comment)
        assertTrue(result.date > 0)
    }

    @Test(expected = PostNotFoundException::class)
    fun `createComment should throw for missing post`() {
        service.createComment(999, Comment(0, 1, 999, text = "Test"))
    }

    @Test
    fun `getComments should return correct comments`() {
        val post = service.add(Post(0, 1, 1, text = "Post"))
        service.createComment(post.id, Comment(0, 2, post.id, text = "Comment1"))
        service.createComment(post.id, Comment(0, 2, post.id, text = "Comment2"))

        assertEquals(2, service.getComments(post.id).size)
    }

    @Test
    fun `clear should reset service state`() {
        val post = service.add(Post(0, 1, 1, text = "Test"))
        service.createComment(post.id, Comment(0, 2, post.id, text = "Comment"))

        service.clear()

        assertEquals(0, service.getComments(post.id).size)
        assertNull(service.getPostById(post.id))
    }
}