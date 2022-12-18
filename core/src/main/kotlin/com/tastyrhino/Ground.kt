package com.tastyrhino

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.World
import ktx.box2d.body
import ktx.box2d.polygon
import ktx.collections.GdxArray

class Ground(world: World) : LevelObject {

    private val bodies = GdxArray<Body>(5)
    private val elementSize = 50f
    private val elements = 5

    init {
        for (i in 0 until elements) {
            bodies.add(world.body {
                //box(elementSize, 5f)
                PhysicsObjectLoader.ground.map {
                    polygon(*it.scaleAndCenterX(50f, 100f, 50f - 2.5f))
                }
                position.set(elementSize / 2f + i * elementSize, 2.5f)
            })
        }

    }

    private val groundSprite = Sprite(Texture("ground2.png")).apply {
        setSize(elementSize, 5f)
        setOrigin(elementSize / 2f, 5f / 2f)

    }

    override fun moveLevel(amount: Float) {
        bodies.forEach {
            it.setTransform(it.position.x - amount, it.position.y, it.angle)
        }
    }

    override fun update(delta: Float) {
        bodies.forEach {
            if (it.isOutOfBounds(50f)) {
                it.setTransform(it.position.x + elementSize * elements, it.position.y, it.angle)

            }
        }

    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        bodies.forEach {
            groundSprite.transformFromBodyAndDraw(it, batch)
        }
    }

    override fun reset() {
    }
}

fun Body.isOutOfBounds(sizeX: Float): Boolean {
    return this.position.x + sizeX / 2f < 0
}