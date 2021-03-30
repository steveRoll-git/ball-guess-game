using import struct
using import enum

import .ease
from ease let EaseFunc

enum TweenProperty plain
    posX
    posY

struct Tween
    property    : TweenProperty
    start       : f32
    end         : f32
    duration    : f32
    time        : f32
    ease        : EaseFunc

    inline __typecall (cls ...)
        super-type.__typecall cls
            property = (va-option property ...)
            start = (va-option start ...)
            end = (va-option end ...)
            duration = (va-option duration ...)
            ease = (va-option ease ... ease.linear)
            time = 0

do
    let
        TweenProperty
        Tween
    locals;
