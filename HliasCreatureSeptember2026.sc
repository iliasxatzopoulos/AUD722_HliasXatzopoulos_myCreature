HliasCreatureSeptember2026 : Creature {

    makeSound { |rate = 1, modFreq = 0, amp = 0.7, dur = 5|

        this add: {
            var env;
            var sound;

            env = EnvGen.kr(
                Env.linen(0.1, dur - 0.2, 0.1),
                doneAction: 2
            );

            sound = PlayBuf.ar(
                1,
                this.buffer.bufnum,
                rate,
                loop: 0
            );

            if(modFreq > 0) {
                sound = sound * SinOsc.ar(modFreq);
            };

            sound * env * amp ! 2;

        }.play;
    }


    dawn {
        "Hlias Creature: dawn".postln;
        this.makeSound(
            rate: 1.0,
            amp: 0.7,
            dur: 6
        );
    }


    day {
        "Hlias Creature: day".postln;
        this.makeSound(
            rate: 1.2,
            modFreq: 180,
            amp: 0.6,
            dur: 8
        );
    }


    dusk {
        "Hlias Creature: dusk".postln;
        this.makeSound(
            rate: 0.65,
            modFreq: 80,
            amp: 0.6,
            dur: 7
        );
    }


    night {
        "Hlias Creature: night".postln;
        this.makeSound(
            rate: 0.5,
            modFreq: 320,
            amp: 0.35,
            dur: 6
        );
    }


    danger {
        "Hlias Creature: danger".postln;
        this.makeSound(
            rate: 1.4,
            modFreq: 90,
            amp: 0.8,
            dur: 8
        );
    }

}