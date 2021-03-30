using import glm
import .bottle
import .Object
from bottle.graphics let Sprite

global testObj : Object

@@ 'on bottle.load
fn ()
    testObj = (Object (Sprite "images/cup.png")) 

@@ 'on bottle.update
fn (dt)
    testObj.position.x += 1

@@ 'on bottle.draw
fn ()
    'draw testObj

bottle.run;
