import java.awt.Image.SCALE_FAST
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_4BYTE_ABGR
import java.io.File
import java.io.FileNotFoundException
import javax.imageio.ImageIO
import javax.swing.Icon
import javax.swing.ImageIcon

object IconCache {
    private val emptyImage = BufferedImage(200, 200, TYPE_4BYTE_ABGR)
    private val bufferedImages: ArrayList<BufferedImage> = ArrayList()
    private val scaledImages: ArrayList<Icon> = ArrayList()
    var sideScale: Int = 200

    init {
        val imageFolder = File(MainFrame::class.java.getResource("images")?.toURI() ?: throw FileNotFoundException())
        imageFolder
            .walkTopDown()
            .filter { it.isFile }
            .forEach {
                println("Reading file ${it.absoluteFile}")
                with(ImageIO.read(it)) {
                    bufferedImages.add(this)
                    scaledImages.add(scaledIcon(this))
                }
            }
        println(bufferedImages)
    }

    private fun isScaled(icon: Icon): Boolean =
        icon.iconWidth == sideScale && icon.iconHeight == sideScale

    private fun scaledIcon(image: BufferedImage): Icon =
        ImageIcon(image.getScaledInstance(sideScale, sideScale, SCALE_FAST))

    operator fun get(value: Int): Icon {
        if (value == -1) return scaledIcon(emptyImage)
        if (!isScaled(scaledImages[value]))
            scaledImages[value] = scaledIcon(bufferedImages[value])
        return scaledImages[value]
    }

}