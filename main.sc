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
                end = 200
                duration = 0.75
                ease = ease.linear
        'append testObj.tweens
            Tween
                property = TweenProperty.posY
                start = 0
                end = 75
                duration = (0.75 / 2)
                ease = ease.quad-out
                on-finish =
                    fn ()
                        'append testObj.tweens
                            Tween
                                property = TweenProperty.posY
                                start = 75
                                end = 0
                                duration = (0.75 / 2)
                                ease = ease.quad-in
                        ;
                        
    'update testObj dt

@@ 'on bottle.draw
fn ()
    'draw testObj

bottle.run;
