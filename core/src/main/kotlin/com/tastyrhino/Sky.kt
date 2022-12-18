package com.tastyrhino

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.physics.box2d.World
import ktx.box2d.body
import ktx.box2d.box

class Sky(world: World) : LevelObject {

    val skyBox = world.body {
        box(1000f, 10f)
        position.set(0f, 1080f / 1920f * 100f)
    }

    override fun moveLevel(amount: Float) {

    }

    override fun update(delta: Float) {
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
    }
}