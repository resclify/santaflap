package com.tastyrhino

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.World
import ktx.box2d.body
import ktx.box2d.polygon
import ktx.collections.GdxArray

class GroundObjects(world: World) : LevelObject {

    var phase = 0

    val building1Sprite = Sprite(Texture("damaged_building1.png")).apply {
        setSize(40f * 0.64f, 40f)
        setOrigin(40f * 0.64f * 0.5f, 40f * 0.5f)
    }
    val building2Sprite = Sprite(Texture("damaged_building2.png")).apply {
        setSize(40f * 0.64f, 40f)
        setOrigin(40f * 0.64f * 0.5f, 40f * 0.5f)
    }

    val building1Bodies = GdxArray<Body>(5)
    val building2Bodies = GdxArray<Body>(5)
    val allBuildingBodies = GdxArray<Body>(10)

    init {
        for (i in 0 until 5) {
            building1Bodies.add(world.body {
                PhysicsObjectLoader.building1.map {
                    polygon(*it.scaleAndCenterX(40f * 0.64f, 40f * 0.64f, -7.2f))
                }
                //box(40f * 0.64f, 40f)
            })

            building2Bodies.add(world.body {
                PhysicsObjectLoader.building2.map {
                    polygon(*it.scaleAndCenterX(40f * 0.64f, 40f * 0.64f, -7.2f))
                }
                //box(40f * 0.64f, 40f)
            })
        }

        allBuildingBodies.addAll(building1Bodies)
        allBuildingBodies.addAll(building2Bodies)
    }

    override fun moveLevel(amount: Float) {
        allBuildingBodies.forEach {
            it.moveForwardBy(-amount)
        }
    }

    override fun update(delta: Float) {
        allBuildingBodies.forEach {
            it.moveBuilding()
        }
    }


    private fun Body.moveBuilding() {
        if (this.isOutOfBounds(40f)) {
            val newPos = getFrontBuildingPos() + MathUtils.random(40f, 100f)
            this.moveForwardBy(newPos)

            when (phase) {
                0 -> this.setPosY(MathUtils.random(-10f, 10f))
                1 -> this.setPosY(MathUtils.random(-10f, 15f))
                2 -> this.setPosY(MathUtils.random(-5f, 15f))
                else -> this.setPosY(MathUtils.random(-5f, 20f))
            }
        }
    }

    private fun getFrontBuildingPos(): Float {
        var posX = 0f

        for (i in 0 until allBuildingBodies.size) {
            if (allBuildingBodies[i].position.x > posX) posX = allBuildingBodies[i].position.x
        }
        return posX
    }

    fun getMaxHeightBuilding(range: Float): Float {
        var maxHeight = -100f

        allBuildingBodies.forEach {
            if (it.position.x < range) {
                maxHeight = kotlin.math.max(maxHeight, it.position.y + 20f)
            }
        }
        return maxHeight
    }

    override fun reset() {
        var currentPos = 230f

        for (i in 0 until building1Bodies.size) {
            building1Bodies[i].setPosX(currentPos)
            building1Bodies[i].setPosY(MathUtils.random(5f, 8f))
            currentPos += MathUtils.random(60f, 100f)

            building2Bodies[i].setPosX(currentPos)
            building2Bodies[i].setPosY(MathUtils.random(4f, 6f))
            currentPos += MathUtils.random(60f, 100f)
        }
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        building1Bodies.forEach {
            building1Sprite.transformFromBodyAndDraw(it, batch)
        }
        building2Bodies.forEach {
            building2Sprite.transformFromBodyAndDraw(it, batch)
        }
    }
}


fun Sprite.transformFromBodyAndDraw(body: Body, batch: Batch?) {
    transformFromBody(body)
    draw(batch)
}

fun Body.moveForwardBy(amount: Float) {
    setTransform(position.x + amount, position.y, angle)
}

fun Body.setPosY(posY: Float) {
    setTransform(position.x, posY, angle)
}

fun Body.setPosX(posX: Float) {
    setTransform(posX, position.y, angle)

}
