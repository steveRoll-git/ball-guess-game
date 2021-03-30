using import glm
using import struct
using import Array

import .bottle
from bottle.graphics let Sprite

import .ease
from ease let EaseFunc

import .Tween
from Tween let Tween TweenProperty

fn lerp (a b t)
    a + (b - a) * t

typedef+ Array
    fn fast-remove (self index)
        self @ index = ('pop self)

struct Object
    position    : vec2
    sprite      : Sprite
    quad        : vec4
    tweens      : (Array Tween)

    inline... __typecall
    case (cls)
        super-type.__typecall cls
            sprite = (Sprite)
    case (cls, sprite : Sprite)
        super-type.__typecall cls
            sprite = sprite
            quad = (vec4 0 0 1 1)

    fn update (self dt)
        for i in (rrange (countof self.tweens))
            let tween = (self.tweens @ i)

            tween.time = (min (tween.time + (f32 dt)) tween.duration)

            let newProp = (lerp tween.start tween.end (tween.ease (tween.time / tween.duration)))

            switch tween.property
            case TweenProperty.posX
                self.position.x = newProp
            case TweenProperty.posY
                self.position.y = newProp
            default
                ;

            if (tween.time >= tween.duration)
                'fast-remove self.tweens i

    fn draw (self)
        bottle.graphics.sprite self.sprite self.position (quad = self.quad)
