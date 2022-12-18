package com.tastyrhino

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.World
import ktx.box2d.body
import ktx.box2d.box

class FlyingObjects(world: World) : LevelObject {
    var phase = 0

    var maxBuildingHeight = 300f
    private val ceiling = 1080f / 1920f * 100f

    private var spawnTimer = 0f
    private var fighterSpeed = 0.5f


    private val fighterBody = world.body {
        box(10f, 2f)
        position.set(40f, 50f)
    }

    private val fighterSprite = Sprite(Texture("fighter.png")).apply {
        this.setSize(10f, 2.4f)
        this.setOrigin(5.0f, 1.2f)
    }

    override fun moveLevel(amount: Float) {
        fighterBody.moveForwardBy(-amount)
    }

    override fun update(delta: Float) {
        fighterBody.moveForwardBy(-fighterSpeed)

        if (spawnTimer < 0 && maxBuildingHeight < ceiling * 0.75f) {
            spawnNewFighter()
        }
        spawnTimer -= delta
    }

    private fun spawnNewFighter() {
        var height = when (phase) {
            0 -> MathUtils.random(ceiling - 5f, ceiling)
            1 -> MathUtils.random(ceiling - 5f, ceiling)
            2 -> MathUtils.random((maxBuildingHeight + ceiling) / 2f, ceiling)
            else -> MathUtils.random(maxBuildingHeight + 15f, ceiling)
        }

        if (phase > 2 && maxBuildingHeight < ceiling * 0.5f) {
            height = maxBuildingHeight + 5f
        }

        fighterSpeed = when (phase) {
            0 -> MathUtils.random(0.45f, 0.55f)
            1 -> MathUtils.random(0.4f, 0.6f)
            2 -> MathUtils.random(0.4f, 0.6f)
            else -> MathUtils.random(0.35f, 0.75f)
        }



        fighterBody.setPosY(height)
        fighterBody.setPosX(120f)

        spawnTimer += MathUtils.random(7f, 12f)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        fighterSprite.transformFromBodyAndDraw(fighterBody, batch)
    }

    override fun reset() {
        fighterBody.setTransform(-10f, 100f, 0f)
    }
}