package com.example.musicademi


class ImageArtist() {

    val imagenes: List<String>  = listOf(
        "David Bowie",
        "https://lastfm.freetls.fastly.net/i/u/ar0/7d80b8d90c1a4a7881c99e75ef4d9b4b.webp",
        "Radiohead",
        "https://lastfm.freetls.fastly.net/i/u/ar0/9b109fcab6c48f5714c8554a31ab9943.webp",
        "Coldplay",
        "https://lastfm.freetls.fastly.net/i/u/ar0/df378e4cd71c45f2c51702b0a3290547.webp",
        "Queen",
        "https://lastfm.freetls.fastly.net/i/u/ar0/58e8e895e8e788397d461f5a73187a03.webp",
        "The Rolling Stones",
        "https://lastfm.freetls.fastly.net/i/u/ar0/6e7eac3310bbf128cbae2c4c17443849.webp",
        "Muse",
        "https://lastfm.freetls.fastly.net/i/u/ar0/f4821c02ef8b4638c6bcf0e96f1682c5.webp",
        "The Beatles",
        "https://lastfm.freetls.fastly.net/i/u/ar0/55efc33bcd234fd8a1d65b3d49bf9661.webp",
        "The Cure",
        "https://lastfm.freetls.fastly.net/i/u/ar0/a126a88f45e54abfca14d6150f6d694c.webp",
        "Daft Punk",
        "https://lastfm.freetls.fastly.net/i/u/ar0/9fd718d4c80e4ac89107c6c0bb908440.webp",
        "Arctic Monkeys",
        "https://lastfm.freetls.fastly.net/i/u/ar0/7d2fec3674a2a35dc0b2ee22082a8c36.webp",
        "Red Hot Chili Peppers",
        "https://lastfm.freetls.fastly.net/i/u/ar0/366962d5733a4aee8bee4a136c239d47.webp",
        "Blur",
        "https://lastfm.freetls.fastly.net/i/u/ar0/c665c91dd43f4da5b37cba4c0f8d54ea.webp",
        "Ed Sheeran",
        "https://lastfm.freetls.fastly.net/i/u/ar0/53a8dcdb09a097a78b3c2f76301fd4eb.webp",
        "Nirvana",
        "https://lastfm.freetls.fastly.net/i/u/ar0/d5d07a2ae7e54f989f0339d5e3eea834.webp",
        "Sia",
        "https://lastfm.freetls.fastly.net/i/u/ar0/35de5f2c0f7d687fb7cb26407c629a99.webp",
        "Pink Floyd",
        "https://lastfm.freetls.fastly.net/i/u/ar0/d5053f5f2f4c454c9083be358741b7ca.webp",
        "Bob Dylan",
        "https://lastfm.freetls.fastly.net/i/u/ar0/3e8102f274934fa98035e2b2f70f9242.webp",
        "Tame Impala",
        "https://lastfm.freetls.fastly.net/i/u/ar0/84fdda88b5618c73871c287bcf8925c8.webp",
        "The Strokes",
        "https://lastfm.freetls.fastly.net/i/u/ar0/1b98ba101c374d1c6bbe5556db965766.webp",
        "U2",
        "https://lastfm.freetls.fastly.net/i/u/ar0/23d1785098d85a47ae96898e7fb47dec.webp",
        "Adele",
        "https://lastfm.freetls.fastly.net/i/u/ar0/750c3c302575469592f6f0e2e9cfb368.webp",
        "Arcade Fire",
        "https://lastfm.freetls.fastly.net/i/u/ar0/8c9dc9a6640d8528f11b6acac2f3c6da.webp",
        "Calvin Harris",
        "https://lastfm.freetls.fastly.net/i/u/ar0/f09e36500e29043b4fa2ed393ecaf441.webp",
        "The Weeknd",
        "https://lastfm.freetls.fastly.net/i/u/ar0/e83f1fa71184701bf1efe5c90564ba55.webp",
        "R.E.M.",
        "https://lastfm.freetls.fastly.net/i/u/ar0/7cfe09c7db74413cb72709f83b2f96c8.webp",
        "Michael Jackson",
        "https://lastfm.freetls.fastly.net/i/u/ar0/51ca1bce0c274d1d83fa8a0a27b83d2c.webp",
        "Depeche Mode",
        "https://lastfm.freetls.fastly.net/i/u/ar0/8c62d87f66134c779adfaf56a6b71cb4.webp",
        "Led Zeppelin",
        "https://lastfm.freetls.fastly.net/i/u/ar0/443a5c46acf64a5db828ec276c75e477.webp",
        "Lana Del Rey",
        "https://lastfm.freetls.fastly.net/i/u/ar0/ed73b3f4ebbf4055c016aa8afd0f18cb.webp",
        "The xx",
        "https://lastfm.freetls.fastly.net/i/u/ar0/2952fe3f2a2f2f2110220725c2a60333.webp",
        "The Killers",
        "https://lastfm.freetls.fastly.net/i/u/ar0/1d0e31e94a19e6de1f398bdc5e10357e.webp",
        "Major Lazer",
        "https://lastfm.freetls.fastly.net/i/u/ar0/38871bbf7333a8ec94507e7a7dc7333a.webp",
        "The Black Keys",
        "https://lastfm.freetls.fastly.net/i/u/ar0/63cfa8ee175845a1b4ea64cbb5b68edf.webp",
        "Foo Fighters",
        "https://lastfm.freetls.fastly.net/i/u/ar0/195ef60b5ce4442c938dc1af0fb83158.webp",
        "Rihanna",
        "https://lastfm.freetls.fastly.net/i/u/ar0/68eb0284a97fca41b9dfdc3b5da69af4.webp",
        "Oasis",
        "https://lastfm.freetls.fastly.net/i/u/ar0/7c6a416c75f74d32cded1c1dc37d864a.webp",
        "Imagine Dragons",
        "https://lastfm.freetls.fastly.net/i/u/ar0/1708c8c2cff9b9fbdcc4722f36ad3e73.webp",
        "The Smiths",
        "https://lastfm.freetls.fastly.net/i/u/ar0/d7525d14788b49f683dc62a770da7a58.webp",
        "The Clash",
        "https://lastfm.freetls.fastly.net/i/u/ar0/26f9a3dbb8514f0abb36417324f94b66.webp",
        "Gorillaz",
        "https://lastfm.freetls.fastly.net/i/u/ar0/b51126b92d06d711b9c6641fff9fef92.webp",
        "Florence + the Machine",
        "https://lastfm.freetls.fastly.net/i/u/ar0/54b8f02b459f4fef84ac2774cf82ef83.webp",
        "Franz Ferdinand",
        "https://lastfm.freetls.fastly.net/i/u/ar0/88664eaa695d4105add2a1eac7cf967c.webp",
        "Pixies",
        "https://lastfm.freetls.fastly.net/i/u/ar0/84c35e2e9d18436cafe6209b0c3aaa7e.webp",
        "Beck",
        "https://lastfm.freetls.fastly.net/i/u/ar0/5b7935ef55624ba5aec1bd9c79e53bc7.webp",
        "The White Stripes",
        "https://lastfm.freetls.fastly.net/i/u/ar0/ae51845f6126482194ed92395379dd32.webp",
        "David Guetta",
        "https://lastfm.freetls.fastly.net/i/u/ar0/58b0ceaf8113f05e1e2c68a140287237.webp",
        "New Order",
        "https://lastfm.freetls.fastly.net/i/u/ar0/1f2535f5dc344eaaba1020e726b3acbc.webp",
        "Vetusta Morla",
        "https://lastfm.freetls.fastly.net/i/u/ar0/4c53eda586b67c67dee99cde4fcee9ad.webp",
        "Drake",
        "https://lastfm.freetls.fastly.net/i/u/ar0/1f3367f2531ed9eaa1fccb14fdc74063.webp",
        "Foals",
        "https://lastfm.freetls.fastly.net/i/u/ar0/5fdb928de2f8398b8a2014320c513890.webp"

    )

    fun imageArtist(name: String):String{
        var indice=0
        for ((index, elem) in imagenes.withIndex()){
            indice = (if (name == elem) {
                            indice = index
                            break
                    } else 0)
        }
        return imagenes[indice+1]
    }
}