package com.tastyrhino

import com.badlogic.gdx.graphics.g2d.Batch

interface LevelObject {

    fun moveLevel(amount: Float)

    fun update(delta: Float)

    fun draw(batch: Batch?, parentAlpha: Float)

}