fun main() {
    val service = WallService()

    // Создаем пост
    val post = service.add(Post(
        id = 0,
        ownerId = 1,
        fromId = 1,
        date = System.currentTimeMillis(),
        text = "Новый пост"
    ))
    println("Создан пост с ID: ${post.id}")

    try {
        // Добавляем комментарий
        val comment = service.createComment(post.id, Comment(
            id = 0,
            fromId = 2,
            postId = post.id,
            date = System.currentTimeMillis(),
            text = "Отличный пост!"
        ))
        println("Добавлен комментарий с ID: ${comment.id}")

        // Пытаемся добавить комментарий к несуществующему посту
        service.createComment(999, Comment(
            id = 0,
            fromId = 3,
            postId = 999,
            date = System.currentTimeMillis(),
            text = "Этот комментарий не должен добавиться"
        ))
    } catch (e: PostNotFoundException) {
        println("Ошибка: ${e.message}")
    }

    // Выводим все комментарии к посту
    println("Комментарии к посту ${post.id}:")
    service.getComments(post.id).forEach {
        println("- ${it.text} (автор: ${it.fromId})")
    }
}