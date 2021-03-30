# from https://gist.github.com/gre/1650294

do
    let EaseFunc = (@ (function f32 f32))

    fn linear (t)
        t

    fn quad-in (t)
        t * t

    fn quad-out (t)
        t * (2 - t)

    locals;
