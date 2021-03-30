using import glm
using import Array
using import Rc
import .bottle
import .Object
from bottle.graphics let Sprite
import .Tween
from Tween let Tween TweenProperty
import .ease

global cup-sprite : (Rc Sprite)
global cups : (Array Object)

let num-cups = 3
global cup-y : f32
global screen-width : f32
global screen-height : f32
global cup-margin : f32
global cup-width : f32
global total-width : f32

fn get-cup-x (index)
    screen-width / 2 - total-width / 2 + (cup-width + cup-margin) * (f32 index)

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
        local new-cup = (Object (copy cup-sprite))
        new-cup.position = (vec2 (get-cup-x i) cup-y)
        'append cups new-cup

@@ 'on bottle.update
fn (dt)
    for cup in cups
        'update cup dt

@@ 'on bottle.draw
fn ()
    for cup in cups
        'draw cup

bottle.run;
