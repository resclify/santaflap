package com.tastyrhino

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.World
import ktx.box2d.body
import ktx.box2d.box

class FlyingObjects(world: World) : LevelObject {
    var phase = 3

    var maxBuildingHeight = 300f
    val ceiling = 1080f / 1920f * 100f

    var spawnTimer = 0f
    var fighterSpeed = 0.5f


    val fighter = world.body {
        box(10f, 5f)
        position.set(40f, 50f)
    }

    override fun moveLevel(amount: Float) {
        fighter.moveForwardBy(-amount)
    }

    override fun update(delta: Float) {

        fighter.moveForwardBy(-fighterSpeed)

        if (spawnTimer < 0 && maxBuildingHeight < ceiling * 0.75f) {


            val height = when (phase) {
                0 -> MathUtils.random(ceiling - 5f, ceiling)
                1 -> MathUtils.random(ceiling - 5f, ceiling)
                2 -> MathUtils.random((maxBuildingHeight + ceiling) / 2f, ceiling)
                else -> MathUtils.random(maxBuildingHeight + 15f, ceiling)
            }

            fighter.setPosY(height)
            fighter.setPosX(120f)

            spawnTimer += MathUtils.random(7f, 12f)
        }


        spawnTimer -= delta


    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
    }
}