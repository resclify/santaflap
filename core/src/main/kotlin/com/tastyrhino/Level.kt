package com.tastyrhino

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Actor
import ktx.box2d.body
import ktx.box2d.box

class Level(world: World) : Actor() {
    var startingPhase = true
    val ground = Ground(world)
    val groundObjects = GroundObjects(world)
    val skyBarrier = world.body {
        box(100f, 50f)
        position.set(50f, 1080f / 1920f * 100f + 25f)
    }

    val flyingObjects = FlyingObjects(world)

    var levelTime = 0f
    var phase = 1

    fun moveLevel(amount: Float) {
        ground.moveLevel(amount)
        if (!startingPhase) {
            groundObjects.moveLevel(amount)
            flyingObjects.moveLevel(amount)
        }
    }

    fun update(delta: Float) {
        groundObjects.phase = phase
        flyingObjects.phase = phase

        ground.update(delta)
        if (!startingPhase) {
            levelTime += delta
            if (levelTime < 20f) {
                phase = 1

            } else if (levelTime < 40f) {
                phase = 2
            } else if (levelTime < 60f) {
                phase = 3
            }
            groundObjects.update(delta)
            flyingObjects.maxBuildingHeight = groundObjects.getMaxHeightBuilding(200f)
            flyingObjects.update(delta)
        }

        super.act(delta)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        ground.draw(batch, parentAlpha)
        groundObjects.draw(batch, parentAlpha)
        flyingObjects.draw(batch, parentAlpha)
        super.draw(batch, parentAlpha)
    }

    fun resetLevel() {
        startingPhase = true
        groundObjects.reset()
        flyingObjects.reset()
        levelTime = 0f
        phase = 0
    }
}


