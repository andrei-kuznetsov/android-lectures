import java.awt.Font
import java.awt.Graphics
import java.util.*
import javax.swing.*
import javax.imageio.ImageIO
import java.io.File
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage


object Lab01Task02 {
    data class Box(val id: String, val cols: IntRange, val rows: IntRange)

    class Model {
        val MAX_C = 5;
        val MAX_R = 5;
        private val DEF_W = 2;
        private val DEF_H = 2;
        private val field = Array(MAX_C) { Array(MAX_R) { 0 } }
        val boxes = mutableListOf<Box>()
        private val rnd = Random()

        fun generate() {
            reset()

            val figCount = rnd.nextInt(2) + 6;
            val vertCount = rnd.nextInt(2) + 2;
            val horCount = figCount - vertCount;

            for (i in 1..figCount) {
                if (i <= horCount) {
                    placeHorAtRandomPos(i)
                } else {
                    placeVertAtRandomPos(i)
                }
            }
            assert(boxes.size == figCount)
        }

        private fun reset() {
            setRange(0..MAX_C - 1, 0..MAX_R - 1, 0)
            boxes.clear()
        }

        private fun placeHorAtRandomPos(id: Int) = placeBoxAtRandomPos(id, w = DEF_W)
        private fun placeVertAtRandomPos(id: Int) = placeBoxAtRandomPos(id, h = DEF_H)

        private fun placeBoxAtRandomPos(id: Int, h: Int = 1, w: Int = 1) {
            var success = false
            var retries = 100;
            do {
                val colsRange = getValidColsRange(w)
                val rowsRange = getValidRowsRange(h)

                if (checkRangeEmpty(colsRange, rowsRange)) {
                    setRange(colsRange, rowsRange, id)
                    boxes.add(Box(id.toString(), cols = colsRange, rows = rowsRange))
                    success = true;
                }

                retries--;
            } while (!success && retries > 0)

            if (retries <= 0) {
                throw RuntimeException("Known bug encountered. Please restart the app.")
            }
        }

        private fun getValidColsRange(blkSize: Int) = getValidRange(MAX_C, blkSize)
        private fun getValidRowsRange(blkSize: Int) = getValidRange(MAX_R, blkSize)

        private fun getValidRange(maxVal: Int, blkSize: Int): IntRange {
            val v = rnd.nextInt(maxVal - blkSize + 1)
            return v..v + blkSize - 1
        }

        private fun checkRangeEmpty(colsRange: IntRange, rowsRange: IntRange): Boolean {
            for (c in colsRange) {
                for (r in rowsRange) {
                    if (field[c][r] != 0) return false
                }
            }
            return true;
        }

        private fun setRange(colsRange: IntRange, rowsRange: IntRange, id: Int) {
            for (c in colsRange) {
                for (r in rowsRange) {
                    field[c][r] = id
                }
            }
        }
    }

    class PngDumper(private val model: Model) {
        private val BOX_SIZE = 50
        fun Model.draw(g: Graphics2D) {

            for (box in boxes) {
                val x1 = box.cols.first * BOX_SIZE
                val x2 = (box.cols.last + 1) * BOX_SIZE
                val y1 = box.rows.first * BOX_SIZE
                val y2 = (box.rows.last + 1) * BOX_SIZE
                val w = x2 - x1
                val h = y2 - y1

                assert(w > 0)
                assert(h > 0)
                g.drawRect(x1, y1, w, h)

                val fm = g.fontMetrics
                val strOffX = (w - fm.stringWidth(box.id)) / 2
                val strOffY = fm.ascent + (h - (fm.ascent + fm.descent)) / 2
                g.drawString(box.id, x1 + strOffX, y1 + strOffY)
            }
        }

        fun dump(fileName: String) {
            val width = model.MAX_C * BOX_SIZE + 1
            val height = model.MAX_R * BOX_SIZE + 1

            val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
            val g2d = bufferedImage.createGraphics()

            g2d.color = Color.white
            g2d.fillRect(0, 0, width, height)

            g2d.color = Color.black
            model.draw(g2d)

            // Disposes of this graphics context and releases any system resources that it is using.
            g2d.dispose()

            // Save as PNG
            val file = File(fileName)
            ImageIO.write(bufferedImage, "png", file)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val model = Model()
        val pngDumper = PngDumper(model)

        for (i in 1..20) {
            model.generate()
            pngDumper.dump("lab01_constraint_v%02d.png".format(i))
        }
    }
}