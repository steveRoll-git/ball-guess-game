using import glm
using import Array
using import Rc
import .bottle
import .Object
from bottle.graphics let Sprite
import .Tween
from Tween let Tween TweenProperty
import .ease
from (import .time) let time

import .prng
global rng : prng.random.RNG (u64 (time null))

global cup-sprite : (Rc Sprite)
global cups-index : (Array (Rc Object))
global cups-draw : (Array (Rc Object))

let num-cups = 3
global cup-y : f32
global screen-width : f32
global screen-height : f32
global cup-margin : f32
global cup-width : f32
global total-width : f32

global ball-sprite : Sprite
global ball-index : i32 = 1
global ball-visible : bool = true

let move-anim-duration = 0.40
let move-anim-y = 50.0

fn get-cup-x (index)
    screen-width / 2 - total-width / 2 + (cup-width + cup-margin) * (f32 index)

fn animate-cup (from-index to-index y duration)
    imply y f32
    cup := cups-index @ from-index
    'append cup.tweens
        Tween
            property = TweenProperty.posX
            ease = ease.linear
            start = (get-cup-x from-index)
            end = (get-cup-x to-index)
            duration = duration
    'append cup.tweens
        Tween
            property = TweenProperty.posY
            ease = ease.quad-boomerang
            start = cup-y
            end = y
            duration = duration

fn swap-cups (a b)
    imply a i32
    imply b i32
    let diff = (f32 (abs (a - b)))
    let t = 0.2
    duration := move-anim-duration * (diff * t - t + 1)
    y-diff := move-anim-y
    animate-cup a b (cup-y - y-diff) duration
    animate-cup b a (cup-y + y-diff) duration
    cups-draw @ 0 = (copy (cups-index @ b))
    cups-draw @ (num-cups - 1) = (copy (cups-index @ a))
    local i : i32 = 0
    for d in (range 1 (num-cups - 1))
        # please excuse this code... I don't know how to improve this part
        if (i == b)
            i += 1
            if (i == a)
                i += 1
        if (i == a)
            i += 1
            if (i == b)
                i += 1
        cups-draw @ d = (copy (cups-index @ i))
        i += 1

    if (ball-index == a)
        ball-index = b
    elseif (ball-index == b)
        ball-index = a

    'swap cups-index a b

@@ 'on bottle.load
fn ()
    do
        let width-i height-i = (bottle.window.size)
        screen-width = (f32 width-i)
        screen-height = (f32 height-i)

    cup-sprite = (Rc.wrap (Sprite "images/cup.png"))
    cup-width = (f32 cup-sprite.size.x)
    cup-margin = (cup-width / 4)
    cup-y = ((screen-height / 2) - (f32 cup-sprite.size.y) / 2)
    total-width = ((cup-width + cup-margin) * num-cups - cup-margin)

    for i in (range num-cups)
        local new-cup = (Rc.wrap (Object (copy cup-sprite)))
        new-cup.position = (vec2 (get-cup-x i) cup-y)
        'append cups-index (copy new-cup)
        'append cups-draw (copy new-cup)

    ball-sprite = (Sprite "images/ball.png")

@@ 'on bottle.update
fn (dt)
    if (bottle.input.pressed? 'A)
        cup-a := (i32 ((rng) % num-cups))
        cup-b := (i32 ((cup-a + (rng) % (num-cups - 1) + 1) % num-cups))
        swap-cups cup-a cup-b
    for cup in cups-index
        'update cup dt

@@ 'on bottle.draw
fn ()
    if ball-visible
        bottle.graphics.sprite ball-sprite
            vec2
                (get-cup-x ball-index) + cup-width / 2 - (f32 ball-sprite.size.x) / 2
                cup-y + 10

    for cup in cups-draw
        'draw cup

bottle.run;
