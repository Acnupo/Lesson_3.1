sealed class Attachment {
    abstract val type: String
}

data class PhotoAttachment(val photo: Photo) : Attachment() {
    override val type = "photo"
}

data class VideoAttachment(val video: Video) : Attachment() {
    override val type = "video"
}

data class AudioAttachment(val audio: Audio) : Attachment() {
    override val type = "audio"
}

data class DocAttachment(val doc: Doc) : Attachment() {
    override val type = "doc"
}

data class LinkAttachment(val link: Link) : Attachment() {
    override val type = "link"
}

data class Photo(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String,
    val photo1280: String? = null
)

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val duration: Int,
    val date: Long = System.currentTimeMillis()
)

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int
)

data class Doc(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int,
    val ext: String,
    val url: String
)

data class Link(
    val url: String,
    val title: String,
    val description: String,
    val previewPhoto: Photo? = null
)