using import glm
using import struct
using import Array

import .bottle
from bottle.graphics let Sprite

typedef+ Array
    fn fast-remove (self index)
        self @ index = ('pop self)

struct Object
    position    : vec2
    sprite      : Sprite
    quad        : vec4

    inline... __typecall
    case (cls)
        super-type.__typecall cls
            sprite = (Sprite)
    case (cls, sprite : Sprite)
        super-type.__typecall cls
            sprite = sprite
            quad = (vec4 0 0 1 1)

    fn draw (self)
        bottle.graphics.sprite self.sprite self.position (quad = self.quad)
