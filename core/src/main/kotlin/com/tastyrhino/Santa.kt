package com.tastyrhino

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Actor
import ktx.box2d.body
import ktx.box2d.box
import ktx.box2d.revoluteJointWith

class Santa(world: World) : Actor() {

    private val reindeer1Body = world.body(BodyDef.BodyType.DynamicBody) {
        box(6f, 4f) {
            density = 1f
        }
        position.y = 20f
        position.x = 30f
        fixedRotation = false
    }
    private val reindeer2Body = world.body(BodyDef.BodyType.DynamicBody) {
        box(6f, 4f) {
            density = 0.01f
        }
        position.y = 20f
        position.x = 20f
        fixedRotation = false
    }
    private val reindeer3Body = world.body(BodyDef.BodyType.DynamicBody) {
        box(6f, 4f) {
            density = 0.01f
        }
        position.y = 20f
        position.x = 10f
        fixedRotation = false
    }
    private val sledBody = world.body(BodyDef.BodyType.DynamicBody) {
        box(6f, 4f) {
            density = 0.01f
        }
        position.y = 20f
        position.x = 10f
        fixedRotation = false
    }

    private val reindeer1Sprite = Sprite(Texture("reindeer_prev.png")).apply {
        this.setSize(6f, 4f)
        this.setOrigin(3f, 2f)
    }
    private val reindeer2Sprite = Sprite(Texture("reindeer_prev.png")).apply {
        this.setSize(6f, 4f)
        this.setOrigin(3f, 2f)
    }
    private val reindeer3Sprite = Sprite(Texture("reindeer_prev.png")).apply {
        this.setSize(6f, 4f)
        this.setOrigin(3f, 2f)
    }

    private val sledSprite = Sprite(Texture("santa_prev.png")).apply {
        this.setSize(6f, 4f)
        this.setOrigin(3f, 2f)
    }

    init {
        val maxDeflectionAngle = 3f
        reindeer1Body.revoluteJointWith(reindeer2Body) {
            localAnchorA.set(-2f, 0f)
            localAnchorB.set(5f, 0f)
            lowerAngle = -maxDeflectionAngle * MathUtils.degreesToRadians
            upperAngle = maxDeflectionAngle * MathUtils.degreesToRadians
            enableLimit = true
        }
        reindeer2Body.revoluteJointWith(reindeer3Body) {
            localAnchorA.set(-2f, 0f)
            localAnchorB.set(5f, 0f)
            lowerAngle = -maxDeflectionAngle * MathUtils.degreesToRadians
            upperAngle = maxDeflectionAngle * MathUtils.degreesToRadians
            enableLimit = true
        }
        reindeer3Body.revoluteJointWith(sledBody) {
            localAnchorA.set(-2f, 0f)
            localAnchorB.set(5f, 0f)
            lowerAngle = -maxDeflectionAngle * MathUtils.degreesToRadians
            upperAngle = maxDeflectionAngle * MathUtils.degreesToRadians
            enableLimit = true
        }
    }

    fun update(delta: Float, difficulty: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isTouched) {
            reindeer1Body.angularVelocity = difficulty * 175.5f * MathUtils.degreesToRadians
        } else {
            reindeer1Body.angularVelocity = difficulty * -175.5f * MathUtils.degreesToRadians
        }
        reindeer1Body.setLinearVelocity(0f, 30f * MathUtils.sin(reindeer1Body.angle))
        reindeer1Body.setTransform(30f, reindeer1Body.position.y, reindeer1Body.angle)

        dampSidewaysVelocity(reindeer2Body)
        dampSidewaysVelocity(reindeer3Body)
        dampSidewaysVelocity(sledBody, 2f)
    }

    val lateralVelocity: Float
        get() {
            return MathUtils.cos(reindeer1Body.angle) * 30f
        }

    private fun dampSidewaysVelocity(body: Body, maxLateralImpulse: Float = 3.0f) {
        val lateralNormal = body.getWorldVector(Vector2.Y)
        val lateralVelocity = lateralNormal.scl(lateralNormal.dot(body.linearVelocity))
        val impulse = lateralVelocity.scl(-body.mass)
        if (impulse.len() > maxLateralImpulse) {
            impulse.scl(maxLateralImpulse / impulse.len())
        }
        body.applyLinearImpulse(impulse, body.worldCenter, true)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        reindeer1Sprite.transformFromBody(reindeer1Body)
        reindeer2Sprite.transformFromBody(reindeer2Body)
        reindeer3Sprite.transformFromBody(reindeer3Body)
        sledSprite.transformFromBody(sledBody)


        reindeer1Sprite.draw(batch)
        reindeer2Sprite.draw(batch)
        reindeer3Sprite.draw(batch)
        sledSprite.draw(batch)
        super.draw(batch, parentAlpha)
    }
}

fun Sprite.transformFromBody(body: Body) {
    setOriginBasedPosition(body.position.x, body.position.y)
    rotation = body.angle * MathUtils.radiansToDegrees
}