class WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var nextPostId = 1
    private var nextCommentId = 1

    fun add(post: Post): Post {
        val newPost = post.copy(
            id = nextPostId++,
            date = if (post.date == 0L) System.currentTimeMillis() else post.date
        )
        posts += newPost
        return newPost
    }

    fun update(post: Post): Boolean {
        for ((index, existingPost) in posts.withIndex()) {
            if (existingPost.id == post.id) {
                posts[index] = post.copy(date = existingPost.date)
                return true
            }
        }
        return false
    }

    @Throws(PostNotFoundException::class)
    fun createComment(postId: Int, comment: Comment): Comment {
        if (!posts.any { it.id == postId }) {
            throw PostNotFoundException("Post with id $postId not found")
        }

        val newComment = comment.copy(
            id = nextCommentId++,
            postId = postId,
            date = if (comment.date == 0L) System.currentTimeMillis() else comment.date
        )
        comments += newComment
        return newComment
    }

    fun getComments(postId: Int): List<Comment> {
        return comments.filter { it.postId == postId }
    }

    fun getPostById(id: Int): Post? {
        return posts.find { it.id == id }
    }

    fun clear() {
        posts = emptyArray()
        comments = emptyArray()
        nextPostId = 1
        nextCommentId = 1
    }
}