# from https://gist.github.com/gre/1650294

do
    let EaseFunc = (@ (function f32 f32))

    fn linear (t)
        t

    fn quad-in (t)
        t * t

    fn quad-out (t)
        t * (2 - t)

    fn quad-in-out (t)
        ? (t < 0.5)
            2 * t * t
            -1 + (4 - 2 * t) * t

    locals;
