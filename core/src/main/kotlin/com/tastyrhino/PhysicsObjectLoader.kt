package com.tastyrhino

import com.badlogic.gdx.math.Vector2

object PhysicsObjectLoader {

    //val reindeer1Raw =
    val reindeer1 = listOf(
        listOf(
            Vector2(52.0f, 92.0f),
            Vector2(12.0f, 65.0f),
            Vector2(90.0f, 59.0f),
            Vector2(99.0f, 87.0f),
            Vector2(91.0f, 94.0f)
        ), listOf(
            Vector2(90.0f, 59.0f),
            Vector2(12.0f, 65.0f),
            Vector2(7.0f, 56.0f),
            Vector2(0.0f, 4.0f),
            Vector2(12.0f, 1.0f),
            Vector2(77.0f, 1.0f),
            Vector2(87.0f, 4.0f)
        )
    )
    val reindeer2 = listOf(
        listOf(
            Vector2(50.0f, 37.0f),
            Vector2(32.0f, 23.0f),
            Vector2(29.0f, 2.0f),
            Vector2(35.0f, 0.0f),
            Vector2(60.0f, 0.0f),
            Vector2(65.0f, 3.0f),
            Vector2(66.0f, 38.0f)
        )
    )
    val ground = listOf(
        listOf(Vector2(4.8f, 2.6000001f), Vector2(0.0f, 0.8f), Vector2(8.5f, 1.0f)),
        listOf(Vector2(18.6f, 2.9f), Vector2(8.5f, 1.0f), Vector2(24.7f, 1.5f)),
        listOf(Vector2(29.800001f, 2.9f), Vector2(24.7f, 1.5f), Vector2(33.8f, 2.1000001f)),
        listOf(Vector2(38.7f, 2.9f), Vector2(33.8f, 2.1000001f), Vector2(45.2f, 2.5f)),
        listOf(Vector2(33.8f, 2.1000001f), Vector2(24.7f, 1.5f), Vector2(65.8f, 2.0f), Vector2(45.2f, 2.5f)),
        listOf(Vector2(51.2f, 3.7f), Vector2(45.2f, 2.5f), Vector2(65.8f, 2.0f)),
        listOf(Vector2(24.7f, 1.5f), Vector2(8.5f, 1.0f), Vector2(65.8f, 2.0f)),
        listOf(
            Vector2(8.5f, 1.0f),
            Vector2(0.0f, 0.8f),
            Vector2(0.0f, 0.0f),
            Vector2(100.0f, -0.100000024f),
            Vector2(100.0f, 1.9f),
            Vector2(65.8f, 2.0f)
        ),
        listOf(Vector2(71.0f, 3.9f), Vector2(65.8f, 2.0f), Vector2(81.1f, 3.6000001f)),
        listOf(Vector2(85.6f, 4.7000003f), Vector2(81.1f, 3.6000001f), Vector2(92.4f, 3.3f)),
        listOf(Vector2(81.1f, 3.6000001f), Vector2(65.8f, 2.0f), Vector2(100.0f, 1.9f), Vector2(92.4f, 3.3f)),
        listOf(Vector2(97.200005f, 3.6000001f), Vector2(92.4f, 3.3f), Vector2(100.0f, 1.9f))
    )

    val building1 = listOf(
        listOf(Vector2(12.0f, 156.0f), Vector2(10.0f, 148.0f), Vector2(36.0f, 149.0f), Vector2(28.0f, 154.0f)),
        listOf(Vector2(48.0f, 151.0f), Vector2(36.0f, 149.0f), Vector2(73.0f, 145.0f)),
        listOf(
            Vector2(36.0f, 149.0f),
            Vector2(10.0f, 148.0f),
            Vector2(99.0f, 73.0f),
            Vector2(100.0f, 114.0f),
            Vector2(89.0f, 129.0f),
            Vector2(80.0f, 139.0f),
            Vector2(73.0f, 145.0f)
        ),
        listOf(Vector2(88.0f, 144.0f), Vector2(80.0f, 139.0f), Vector2(89.0f, 129.0f)),
        listOf(Vector2(99.0f, 73.0f), Vector2(10.0f, 148.0f), Vector2(10.0f, 129.0f), Vector2(100.0f, 0.0f)),
        listOf(
            Vector2(10.0f, 0.0f),
            Vector2(100.0f, 0.0f),
            Vector2(10.0f, 129.0f),
            Vector2(6.0f, 128.0f),
            Vector2(1.0f, 116.0f),
            Vector2(0.0f, 30.0f)
        )
    )

    val building2 = listOf(
        listOf(Vector2(12.0f, 144.0f), Vector2(11.0f, 130.0f), Vector2(21.0f, 139.0f)),
        listOf(Vector2(11.0f, 130.0f), Vector2(0.0f, 114.0f), Vector2(21.0f, 139.0f)),
        listOf(Vector2(21.0f, 139.0f), Vector2(0.0f, 114.0f), Vector2(49.0f, 146.0f), Vector2(27.0f, 146.0f)),
        listOf(Vector2(52.0f, 151.0f), Vector2(49.0f, 146.0f), Vector2(64.0f, 149.0f)),
        listOf(
            Vector2(49.0f, 146.0f),
            Vector2(0.0f, 114.0f),
            Vector2(94.0f, 129.0f),
            Vector2(90.0f, 148.0f),
            Vector2(83.0f, 149.0f),
            Vector2(64.0f, 149.0f)
        ),
        listOf(Vector2(71.0f, 153.0f), Vector2(64.0f, 149.0f), Vector2(83.0f, 149.0f)),
        listOf(Vector2(87.0f, 155.0f), Vector2(83.0f, 149.0f), Vector2(90.0f, 148.0f)),
        listOf(
            Vector2(94.0f, 129.0f),
            Vector2(0.0f, 114.0f),
            Vector2(0.0f, 0.0f),
            Vector2(90.0f, 0.0f),
            Vector2(100.0f, 31.0f),
            Vector2(100.0f, 116.0f)
        )
    )

    val sleigh = listOf(
        listOf(Vector2(33.0f, 13.0f), Vector2(30.0f, 9.0f), Vector2(50.0f, 15.0f), Vector2(41.0f, 15.0f)),
        listOf(Vector2(39.0f, 20.0f), Vector2(41.0f, 15.0f), Vector2(50.0f, 15.0f), Vector2(44.0f, 22.0f)),
        listOf(
            Vector2(50.0f, 15.0f),
            Vector2(30.0f, 9.0f),
            Vector2(37.0f, 8.0f),
            Vector2(67.0f, 6.0f),
            Vector2(64.0f, 10.0f),
            Vector2(56.0f, 15.0f)
        ),
        listOf(
            Vector2(69.0f, 2.0f), Vector2(67.0f, 6.0f), Vector2(37.0f, 8.0f), Vector2(36.0f, 1.0f), Vector2(52.0f, 0.0f)
        )

    )
}

fun List<Vector2>.scaleAndCenterX(sizeX: Float, sizeY: Float, offsetY: Float): Array<Vector2> {
    return this.map {
        Vector2(it.x / 100f * sizeX - sizeX * 0.5f, it.y / 100f * sizeY - sizeY * 0.5f + offsetY)
    }.toTypedArray()
}

fun listOfPolygonsFromString(s: String): Array<Vector2> {
    return s.trim().split(";").map { it.split(",").map { floatValue -> floatValue.toFloat() } }
        .map { Vector2(it.first(), it.last()) }.toTypedArray()
}


fun listOfPolygonSplitsFromString(s: String): List<Array<Vector2>> {
    return s.trim().split("|").map { listOfPolygonsFromString(it) }
}

fun listOfTriangleVertices(s: String): FloatArray {
    return listOfPolygonSplitsFromString(s).flatMap { it.toList() }.flatMap { listOf(it.x, it.y) }.toFloatArray()
}

fun listOfTriangleIndices(l: FloatArray): ShortArray {
    return (0 until l.size / 2).map { it.toShort() }.toShortArray()
}

fun Array<Vector2>.toFloatArray() = flatMap { listOf(it.x, it.y) }.toFloatArray()
