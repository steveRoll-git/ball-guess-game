using import glm
import .bottle
import .Object
from bottle.graphics let Sprite
import .Tween
from Tween let Tween TweenProperty
import .ease

global testObj : Object

@@ 'on bottle.load
fn ()
    testObj = (Object (Sprite "images/cup.png")) 

@@ 'on bottle.update
fn (dt)
    if (bottle.input.pressed? 'A)
        'append testObj.tweens
            Tween
                property = TweenProperty.posX
                start = 0
                end = 300
                duration = 1
                ease = ease.linear
    'update testObj dt

@@ 'on bottle.draw
fn ()
    'draw testObj

bottle.run;
